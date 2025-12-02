package controladores;

import java.io.IOException;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utilidades.HttpUtils;

/**
 * Clase servlet encargada del manejo de peticiones y respuestas HTTP entre
 * servidor y cliente
 * 
 * @author acarrez - 01/12/2025 (DD/MM/YYYY)
 * @param req  - Petición HTTP
 * @param resp - Respuesta HTTP
 */

@WebServlet("/Servlet")
public class Servlet extends HttpServlet {

    private static final String BASE_API = "https://localhost:8080/api/";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String data = req.getReader().lines().reduce("", String::concat);

        JsonObject json = JsonParser.parseString(data).getAsJsonObject();

        String metodo = json.get("metodo").getAsString();
        String endpoint = json.get("endpoint").getAsString();
        String body = json.get("body").isJsonNull() ? null : json.get("body").getAsString();

        try {
            String respuesta = HttpUtils.sendBody(
                    BASE_API + endpoint,
                    metodo,
                    body);

            resp.getWriter().write(respuesta);

        } catch (Exception e) {
            resp.getWriter().write("Error llamando al API: " + e.getMessage());
        }

        switch (endpoint) {
            case "/auth/login" -> resp.sendRedirect("/frontend-taskmanager/tareas.html");
            case "/usuarios/nueva" -> resp.sendRedirect("/frontend-taskmanager/usuarios.html");
            case "/usuarios/listado" -> resp.sendRedirect("/frontend-taskmanager/usuarios.html");
            case "localhost:8080/api/auth/recover-password" ->
                resp.sendRedirect("/frontend-taskmanager/verification.html");
            default -> System.err.println("Esta parte de la web no esta disponible.");
        }
    }
}