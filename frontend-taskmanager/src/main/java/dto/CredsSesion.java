package dto;

/**
 * Clase DTO encargada del definir, establecer y proveer las propiedades de las
 * credenciales de sesión del cliente
 * 
 * @author acarrez - 01/12/2025 (DD/MM/YYYY)
 */

public class CredsSesion {
    String email;
    String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
