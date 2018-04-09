package com.example.sarah.josechaves;

/**
 * Created by Sarah on 13/04/2016.
 */
public class Estudiante {
    private int usuario;
    private String Nombre;
    private String clave;

    public Estudiante(int usuario, String nombre, String clave) {
        this.usuario=usuario;
        this.Nombre=nombre;
        this.clave=clave;
    }

    public int getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    @Override
    public String toString() {
        return "Usuario: " + usuario +
                "\nNombre: " + Nombre ;
    }
}
