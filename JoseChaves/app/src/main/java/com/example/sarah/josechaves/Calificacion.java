package com.example.sarah.josechaves;

/**
 * Created by Sarah on 14/04/2016.
 */
public class Calificacion {
    private int idCurso;
    private int idUsuario;
    private double calificacion;

    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Double calificacion) {
        this.calificacion = calificacion;
    }

    @Override
    public String toString() {
        return "Calificacion: " +
                "Curso: " + idCurso +
                ", Usuario: " + idUsuario +
                ", Calificacion: " + calificacion +
                '}';
    }
}
