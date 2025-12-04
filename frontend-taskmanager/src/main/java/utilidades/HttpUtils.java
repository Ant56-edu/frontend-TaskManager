package utilidades;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class HttpUtils {

    // Configura un cliente HTTP estándar y seguro
    private static final HttpClient CLIENT = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();

    /**
     * Envía una petición HTTP con el cuerpo y método indicados.
     * * @param url URL completa del endpoint de la API
     * 
     * @param body   Cuerpo de la petición (JSON)
     * @param metodo Método HTTP (GET, POST, PUT, DELETE)
     * @return Respuesta de la API en formato String (usualmente JSON)
     * @throws Exception
     */
    public static String sendBody(String url, String body, String metodo) throws Exception {

        HttpRequest.Builder builder = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .timeout(Duration.ofSeconds(15));

        metodo = metodo.toUpperCase();

        switch (metodo) {
            case "GET" -> builder.GET();
            case "POST" -> {
                String finalBody = (body == null || body.isEmpty()) ? "" : body;
                builder.header("Content-Type", "application/json")
                        .POST(HttpRequest.BodyPublishers.ofString(finalBody));
            }
            case "PUT" -> {
                String finalBody = (body == null || body.isEmpty()) ? "" : body;
                builder.header("Content-Type", "application/json")
                        .PUT(HttpRequest.BodyPublishers.ofString(finalBody));
            }
            case "DELETE" -> {
                if (body != null && !body.isEmpty()) {
                    // Permite DELETE con cuerpo, aunque no es estándar, se mantiene para
                    // compatibilidad
                    builder.method("DELETE", HttpRequest.BodyPublishers.ofString(body));
                } else {
                    builder.DELETE();
                }
            }
            default -> System.err.print("Method not supported: " + metodo);
        }

        HttpRequest request = builder.build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());

        // CORREGIDO: Lanza una excepción si la API devuelve un error HTTP para que el
        // Servlet pueda manejarlo.
        if (response.statusCode() >= 400) {
            throw new Exception("API Error (" + response.statusCode() + "): " + response.body());
        }

        return response.body();
    }
}