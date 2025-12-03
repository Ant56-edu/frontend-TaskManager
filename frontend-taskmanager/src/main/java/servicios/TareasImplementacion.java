package servicios;

import dto.Usuario;
import utilidades.HttpUtils;

public class TareasImplementacion implements TareasInterfaz {

    private static final String BASE_API = "http://localhost:9090/api/tareas/";

    @Override
    public void crearUsuario() {
        Usuario usuario = new Usuario();

        try {
            HttpUtils.sendBody(BASE_API + "nueva", usuario.toString(), "POST");
        } catch (Exception e) {
            System.err.println("Ocurrió un error al crear una nueva tarea: " + e.getMessage());
        }
    }

    @Override
    public void eliminarusuario(long id) {
        try {
            HttpUtils.sendBody(BASE_API + "{" + id + "}", "", "DELETE");
        } catch (Exception e) {
            System.err.println("Ocurrió un error al borrar la tarea con ID " + id + ": " + e.getMessage());
        }
    }

    @Override
    public void editarUsuario(long id) {
        Usuario usuario = new Usuario();

        try {
            HttpUtils.sendBody(BASE_API + "{" + id + "}", usuario.toString(), "PUT");
        } catch (Exception e) {
            System.err.println("Ocurrió un error al editar la tarea con ID " + id + ": " + e.getMessage());
        }
    }

    @Override
    public void listarUsuarios() {
        try {
            HttpUtils.sendBody(BASE_API + "listado", "", "GET");
        } catch (Exception e) {
            System.err.println("Ocurrió un error al listar las tareas: " + e.getMessage());
        }
    }

    @Override
    public void buscarUsuario(long id) {
        try {
            HttpUtils.sendBody(BASE_API + "{" + id + "}", "", "GET");
        } catch (Exception e) {
            System.err.println("Ocurrió un error al borrar la tarea con ID " + id + ": " + e.getMessage());
        }
    }

}
