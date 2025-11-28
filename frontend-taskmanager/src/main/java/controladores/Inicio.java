package controladores;

import dto.CredsSesion;
import servicios.LoginImplementacion;
import servicios.LoginInterfaz;

public class Inicio {

    static LoginInterfaz loginServicio = new LoginImplementacion();

    public static void main(String[] args) {
        CredsSesion creds = new CredsSesion();
        creds.setToken(loginServicio.autenticarUsuario("hi@cabron.site", "123456").split(":")[1].replace("\"", "")
                .replace("}", ""));
        System.out.print(creds.getToken());
    }

}
