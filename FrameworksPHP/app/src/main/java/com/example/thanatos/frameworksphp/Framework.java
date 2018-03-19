package com.example.thanatos.frameworksphp;

import android.app.FragmentManager;

/**
 * Created by Thanatos on 5/3/2018.
 */

public class Framework {
    private String nombre;
    private int valoracion;
    private int imagen;

    public Framework(){}

    public Framework(String nombre, int valoracion, int imagen) {
        this.nombre = nombre;
        this.valoracion = valoracion;
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getValoracion() {
        return valoracion;
    }

    public void setValoracion(int valoracion) {
        this.valoracion = valoracion;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }
}


