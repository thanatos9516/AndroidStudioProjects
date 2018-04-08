package com.example.thanatos.examen1;

/**
 * Created by Thanatos on 8/4/2018.
 */

public class Usuario {
    private int usuario;
    private String pass;

    public Usuario(int usuario, String pass) {
        this.usuario=usuario;
        this.pass=pass;
    }

    public int getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public String toString() {
        return "Usuario: " + usuario ;
    }
}
