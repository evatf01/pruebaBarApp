package com.example.barmanagement.models;

public class Category {
    private String nombre;
    private String precio;
    private String cantidad;
    private String img;
    private String extra;
    private String familia;
    private String stock;


    public Category(){}

    public Category(String nombre, String precio, String cantidad, String img, String extra, String familia, String stock) {
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
        this.img = img;
        this.extra = extra;
        this.familia = familia;
        this.stock = stock;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrecio() {
        return  "Precio: "+precio +" €";
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getCantidad() {
        return cantidad;
    }

    public Category setCantidad(String cantidad) {
        this.cantidad = cantidad;
        return null;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public String getFamilia() {
        return familia;
    }

    public void setFamilia(String familia) {
        this.familia = familia;
    }

    @Override
    public String toString() {
        return "Category{" +
                "nombre='" + nombre + '\'' +
                ", precio='" + precio + '\'' +
                ", cantidad='" + cantidad + '\'' +
                ", familia='" + familia + '\'' +
                ", img='" + img + '\'' +
                ", extra='" + extra + '\'' +
                '}';
    }
}

