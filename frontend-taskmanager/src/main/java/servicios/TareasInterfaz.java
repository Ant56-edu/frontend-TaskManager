package servicios;

public interface TareasInterfaz {
    void crearUsuario();

    void eliminarusuario(long id);

    void editarUsuario(long id);

    void listarUsuarios();

    void buscarUsuario(long id);
}
