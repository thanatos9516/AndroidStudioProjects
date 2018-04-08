package model;

/**
 * Created by Johan on 20/04/2017.
 */

public class compra {
    private String id;
    private String descripcion;
    private double total;

    public compra() {
    }

    public compra(String id, String descripcion, double total) {
        this.id = id;
        this.descripcion = descripcion;
        this.total = total;
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

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
