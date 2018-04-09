package com.example.thanatos.examen1;

public class Calificacion {
    private int idCurso;
    private String nombreCurso;
    private int idUsuario;
    private String nombreUsuario;
    private double calificacion;

    public int getIdCurso() { return idCurso; }
    public String getNombreCurso() { return nombreCurso; }

    public void setIdCurso(int idCurso) { this.idCurso = idCurso; }
    public void setNombreCurso(String string, String nombreCurso) { this.nombreCurso = nombreCurso; }

     public int getIdUsuario() { return idUsuario; }
    public String getNombreUsuario(String nombreUsuario) { return nombreUsuario;}

    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }
    public void setNombreUsuario(String string, String nombreUsuario) { this.nombreUsuario = nombreUsuario; }


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
                '.';
    }
}
