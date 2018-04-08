package model;

/**
 * Created by Johan on 19/03/2017.
 */

public class curso {

    private String id;
    private String nombre;
    private String descripcion;
    private double precio;

    public curso() {
    }

    public curso(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public curso(String id, String nombre, String descripcion, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
