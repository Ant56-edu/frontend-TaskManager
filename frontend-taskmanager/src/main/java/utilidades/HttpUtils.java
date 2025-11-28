package utilidades;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.time.Duration;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import java.security.cert.X509Certificate;

public class HttpUtils {

    // Configures a Client that trusts all certificates (Dev Only)
    private static final HttpClient CLIENT = createInsecureClient();

    private static HttpClient createInsecureClient() {
        try {
            TrustManager[] trustAllCerts = new TrustManager[] {
                    new X509TrustManager() {
                        @Override
                        public X509Certificate[] getAcceptedIssuers() {
                            return null;
                        }

                        @Override
                        public void checkClientTrusted(X509Certificate[] certs, String authType) {
                        }

                        @Override
                        public void checkServerTrusted(X509Certificate[] certs, String authType) {
                        }
                    }
            };

            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

            return HttpClient.newBuilder()
                    .connectTimeout(Duration.ofSeconds(15))
                    .sslContext(sslContext) // Inject the insecure context
                    .build();
        } catch (KeyManagementException | NoSuchAlgorithmException e) {
            throw new RuntimeException("Failed to create insecure HttpClient", e);
        }
    }

    public static String sendBody(String url, String body, String metodo) throws Exception {

        HttpRequest.Builder builder = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .timeout(Duration.ofSeconds(15));

        metodo = metodo.toUpperCase();

        switch (metodo) {
            case "GET" -> builder.GET();
            case "POST" -> {
                // Ensure we don't send null body
                String finalBody = (body == null) ? "" : body;
                builder.header("Content-Type", "application/json")
                        .POST(HttpRequest.BodyPublishers.ofString(finalBody));
            }
            case "PUT" -> {
                String finalBody = (body == null) ? "" : body;
                builder.header("Content-Type", "application/json")
                        .PUT(HttpRequest.BodyPublishers.ofString(finalBody));
            }
            case "DELETE" -> {
                if (body != null && !body.isEmpty()) {
                    builder.method("DELETE", HttpRequest.BodyPublishers.ofString(body));
                } else {
                    builder.DELETE();
                }
            }
            default -> System.err.print("Method not supported: " + metodo);
        }

        HttpRequest request = builder.build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }
}