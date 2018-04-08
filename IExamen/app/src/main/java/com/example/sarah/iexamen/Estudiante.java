package com.example.sarah.iexamen;

/**
 * Created by Sarah on 16/04/2016.
 */
public class Estudiante {
    private int IdEstudiante;
    private String Nombre;
    public Estudiante(){}

    public Estudiante(int id, String nombre) {
        this.IdEstudiante=id;
        this.Nombre=nombre;

    }

    public int getIdEstudiante() {
        return IdEstudiante;
    }

    public void setIdEstudiante(int idEstudiante) {
        IdEstudiante = idEstudiante;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    @Override
    public String toString() {
        return  "Ced: =" + IdEstudiante +
                ", Nombre=" + Nombre ;
    }
}
