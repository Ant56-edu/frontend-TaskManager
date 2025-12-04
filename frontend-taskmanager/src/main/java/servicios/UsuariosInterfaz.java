package servicios;

import dto.Usuario;

public interface UsuariosInterfaz {

    void crearUsuario(Usuario usuario);

    void eliminarusuario(long id);

    void editarUsuario(long id, Usuario usuario);

    void listarUsuarios();

    void buscarUsuario(long id);
}
