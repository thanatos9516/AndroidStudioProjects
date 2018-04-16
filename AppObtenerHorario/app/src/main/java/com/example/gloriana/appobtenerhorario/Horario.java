package com.example.gloriana.appobtenerhorario;

/**
 * Created by maste on 25/02/2018.
 */

public class Horario {

    private String codigo;
    private String curso;
    private String grupo;
    private String horario;
    private String profesor;

    public Horario() {
    }

    public Horario(String codigo, String curso, String grupo, String horario, String profesor) {
        this.codigo = codigo;
        this.curso = curso;
        this.grupo = grupo;
        this.horario = horario;
        this.profesor = profesor;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getProfesor() {
        return profesor;
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }

    @Override
    public String toString() {
        return "Horario{" +
                "codigo='" + codigo + '\'' +
                ", curso='" + curso + '\'' +
                ", grupo='" + grupo + '\'' +
                ", horario='" + horario + '\'' +
                ", profesor='" + profesor + '\'' +
                '}';
    }
}
