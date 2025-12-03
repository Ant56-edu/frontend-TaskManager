package servicios;

import com.google.gson.JsonObject;

import utilidades.HttpUtils;

public class LoginImplementacion implements LoginInterfaz {

    /**
     * Autentica un usuario llamando a la API externa mediante POST.
     *
     * @param usuario     Nombre del usuario
     * @param contrasenia Contraseña del usuario
     * @return true si la API confirma autenticación, false si no.
     * @throws Exception si ocurre un error de red o si la API devuelve datos
     *                   inválidos.
     */
    private static final String BASE_API = "http://localhost:9090/api/auth/";

    @Override
    public String autenticarUsuario(String email, String password) {
        try {

            // JSON para la API
            JsonObject bodyJson = new JsonObject();
            bodyJson.addProperty("email", email);
            bodyJson.addProperty("password", password);

            // Llamada a la API
            String respuesta = HttpUtils.sendBody(
                    BASE_API + "login",
                    bodyJson.toString(),
                    "POST");

            // Comprobación de respuesta. Si se recibe un token, se asume éxito.
            if (respuesta != null && !respuesta.contains("error")) {
                return respuesta;
            } else {
                return "Ha ocurrido un error al verificar el inicio de sesión o las credenciales son inválidas";
            }

        } catch (Exception e) {
            return e.getMessage();
        }
    }

}
