package model;

/**
 * Created by Johan on 20/04/2017.
 */

public class detalle {
    private String id;
    private String idCompra;
    private String descripcion;
    private double precio;

    public detalle() {
    }

    public detalle(String id, String idCompra, String descripcion, double precio) {
        this.id = id;
        this.idCompra = idCompra;
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

    public String getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(String idCompra) {
        this.idCompra = idCompra;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
