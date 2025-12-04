package controladores;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utilidades.HttpUtils;

@WebServlet("/Servlet")
public class Servlet extends HttpServlet {

    private static final String BASE_API = "http://localhost:9090/api/";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");

        String data = req.getReader().lines().reduce("", String::concat);

        JsonObject json = JsonParser.parseString(data).getAsJsonObject();

        String metodo = json.get("metodo").getAsString();
        String endpoint = json.get("endpoint").getAsString();
        String body = json.get("body").isJsonNull() ? null : json.get("body").getAsString();

        if (body != null) {
            String originalBody = body;

            // --- REQUERIMIENTO: Asegurar que el JSON del body (credenciales) esté en
            // minúsculas ---
            // El body de la API es un String que representa un JSON: '{"email":"...",
            // "password":"..."}'
            try {
                // Intenta parsear el body como JSON
                JsonObject apiBodyJson = JsonParser.parseString(originalBody).getAsJsonObject();
                JsonObject lowerCaseBodyJson = new JsonObject();

                // Itera sobre las claves y las convierte a minúsculas
                for (String key : apiBodyJson.keySet()) {
                    lowerCaseBodyJson.add(key.toLowerCase(), apiBodyJson.get(key));
                }
                body = lowerCaseBodyJson.toString();
            } catch (JsonSyntaxException e) {
                // Si el body no es un JSON válido (ej. en peticiones GET o DELETE sin body),
                // usa el original como está.
                body = originalBody;
            }
            // -------------------------------------------------------------------------------------
        }

        // CORRECCIÓN DE ROBUSTEZ: Manejo seguro de la clave "body"
        // Aseguramos que la clave exista y no sea JsonNull antes de intentar obtenerla
        // como String.
        if (json.has("body")) {
            JsonElement bodyElement = json.get("body");
            if (!bodyElement.isJsonNull()) {
                // El cuerpo de la petición interna viene como un String
                body = bodyElement.getAsString();
            }
        }

        // ---------------------------------------------------------------------
        // LÓGICA PARA AÑADIR fechaCreacion (SOLUCIÓN AL NOT-NULL)
        // Usando formato ISO estándar (yyyy-MM-dd) para asegurar la deserialización
        // correcta del tipo LocalDate en Spring Boot.
        // ---------------------------------------------------------------------
        if (metodo.equalsIgnoreCase("POST") && endpoint.equalsIgnoreCase("/tarea/nueva") && body != null) {
            try {
                // 1. Parsear el cuerpo de la tarea enviado por el frontend
                JsonObject taskBody = JsonParser.parseString(body).getAsJsonObject();

                // 2. Generar solo la fecha actual en formato ISO yyyy-MM-dd
                String formattedDate = LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE);

                // 3. Añadir el campo fechaCreacion
                taskBody.addProperty("fechaCreacion", formattedDate);

                // 4. Actualizar el cuerpo de la petición que se enviará a la API
                body = taskBody.toString();
                System.out.println("DEBUG: Body de Tarea modificado con fechaCreacion (ISO): " + body);

            } catch (JsonSyntaxException e) {
                // Si el body de la tarea no es JSON válido
                System.err
                        .println("Error al parsear el cuerpo de la tarea para añadir fechaCreacion: " + e.getMessage());
            }
        }
        // ---------------------------------------------------------------------

        try {
            String respuesta = HttpUtils.sendBody(
                    BASE_API + endpoint,
                    body,
                    metodo);

            resp.getWriter().write(respuesta);

        } catch (Exception e) {
            // Maneja el error de la API (e.g., 401 Unauthorized, 404 Not Found)
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); // Código 500 para errores internos del proxy
            resp.getWriter().write("{ \"error\": true, \"message\": \"" + e.getMessage() + "\"}");
        }
    }
}