package servicios;

import dto.Tarea;
import utilidades.HttpUtils;

public class TareasImplementacion implements TareasInterfaz {

    private static final String BASE_API = "http://localhost:9090/api/tareas/";

    @Override
    public void crearTarea(Tarea tarea) {
        try {
            HttpUtils.sendBody(BASE_API + "nueva", tarea.toString(), "POST");
        } catch (Exception e) {
            System.err.println("Ocurrió un error al crear una nueva tarea: " + e.getMessage());
        }
    }

    @Override
    public void eliminarTarea(long id) {
        try {
            HttpUtils.sendBody(BASE_API + "{" + id + "}", "", "DELETE");
        } catch (Exception e) {
            System.err.println("Ocurrió un error al borrar la tarea con ID " + id + ": " + e.getMessage());
        }
    }

    @Override
    public void editarTarea(long id, Tarea tarea) {
        try {
            HttpUtils.sendBody(BASE_API + "{" + id + "}", tarea.toString(), "PUT");
        } catch (Exception e) {
            System.err.println("Ocurrió un error al editar la tarea con ID " + id + ": " + e.getMessage());
        }
    }

    @Override
    public String listarTareas() {
        try {
            return HttpUtils.sendBody(BASE_API + "listado", "", "GET");
        } catch (Exception e) {
            System.err.println("Ocurrió un error al listar las tareas: " + e.getMessage());
            return null;
        }
    }

    @Override
    public String buscarTarea(long id) {
        // ... (lógica de búsqueda)
        return null; // Placeholder
    }
}