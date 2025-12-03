package servicios;

import dto.Usuario;
import utilidades.HttpUtils;

public class UsuariosImplementacion implements UsuariosInterfaz {

    private static final String BASE_API = "http://localhost:9090/api/usuarios/";

    @Override
    public void crearUsuario() {
        Usuario usuario = new Usuario();

        try {
            HttpUtils.sendBody(BASE_API + "nueva", usuario.toString(), "POST");
        } catch (Exception e) {
            System.err.println("Ocurrió un error al crear un nuevo usuario: " + e.getMessage());
        }
    }

    @Override
    public void eliminarusuario(long id) {
        try {
            HttpUtils.sendBody(BASE_API + "{" + id + "}", "", "DELETE");
        } catch (Exception e) {
            System.err.println("Ocurrió un error al borrar al usuario con ID " + id + ": " + e.getMessage());
        }
    }

    @Override
    public void editarUsuario(long id) {
        Usuario usuario = new Usuario();

        try {
            HttpUtils.sendBody(BASE_API + "{" + id + "}", usuario.toString(), "PUT");
        } catch (Exception e) {
            System.err.println("Ocurrió un error al editar al usuario con ID " + id + ": " + e.getMessage());
        }
    }

    @Override
    public void listarUsuarios() {
        try {
            HttpUtils.sendBody(BASE_API + "listado", "", "GET");
        } catch (Exception e) {
            System.err.println("Ocurrió un error al listar a los usuarios: " + e.getMessage());
        }
    }

    @Override
    public void buscarUsuario(long id) {
        try {
            HttpUtils.sendBody(BASE_API + "{" + id + "}", "", "GET");
        } catch (Exception e) {
            System.err.println("Ocurrió un error al borrar al usuario con ID " + id + ": " + e.getMessage());
        }
    }

}
