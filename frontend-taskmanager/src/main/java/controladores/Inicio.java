package controladores;

import servicios.LoginImplementacion;
import servicios.LoginInterfaz;

public class Inicio {

    static LoginInterfaz loginServicio = new LoginImplementacion();

    public static void main(String[] args) {

        System.out.println(loginServicio.autenticarUsuario("hi@cabron.site", "123456").split(":")[1].replace("\"", "")
                .replace("}", ""));

    }

}
