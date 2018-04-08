package com.example.thanatos.examen1;

/**
 * Created by Thanatos on 8/4/2018.
 */

public class Nota {
    private int idCurso;
    private int idUsuario;
    private double calificacion;


    public double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(double calificacion) {
        this.calificacion = calificacion;
    }

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

    @Override
    public String toString() {
        return "Nota{" +
                "calificacion=" + calificacion +
                ", idCurso=" + idCurso +
                ", idUsuario=" + idUsuario +
                '}';
    }
}

