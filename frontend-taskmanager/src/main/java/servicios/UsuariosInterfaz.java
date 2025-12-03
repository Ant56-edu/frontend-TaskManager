package servicios;

public interface UsuariosInterfaz {

    void crearUsuario();

    void eliminarusuario(long id);

    void editarUsuario(long id);

    void listarUsuarios();

    void buscarUsuario(long id);
}
