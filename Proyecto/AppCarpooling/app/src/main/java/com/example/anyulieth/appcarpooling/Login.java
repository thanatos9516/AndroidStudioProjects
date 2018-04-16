package com.example.anyulieth.appcarpooling;

/**
 * Created by Anyulieth on 15/04/2018.
 */

public class Login {
    private String correo;
    private String clave;

    public Login(){}

    public  Login(String correo, String clave){
        this.correo = correo;
        this.clave = clave;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCorreo() {
        return correo;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getClave() {
        return clave;
    }

    @Override
    public String toString() {
        return "Login{" +
                "correo='" + correo + '\'' +
                ", clave='" + clave + '\'' +
                '}';
    }
}
