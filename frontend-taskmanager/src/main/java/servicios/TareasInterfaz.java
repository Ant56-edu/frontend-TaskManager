package servicios;

import dto.Tarea;

public interface TareasInterfaz {
    void crearTarea(Tarea tarea);

    void eliminarTarea(long id);

    void editarTarea(long id, Tarea tarea);

    String listarTareas();

    String buscarTarea(long id);
}
