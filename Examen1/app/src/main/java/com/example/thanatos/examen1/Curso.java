package com.example.thanatos.examen1;


public class Curso {
    private int idCurso;
    private String nombreCurso;

    public Curso(){}
    public Curso(int idCurso, String nombreCurso) {
        this.idCurso=idCurso;
        this.nombreCurso=nombreCurso;
    }

    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    public String getNombreCurso() {
        return nombreCurso;
    }

    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }
}
