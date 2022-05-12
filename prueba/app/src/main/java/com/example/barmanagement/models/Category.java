package com.example.barmanagement.models;

public class Category {
    private String nombre;
    private String precio;
    private String cantidad;
    private String familia;
    private String img;
    private String extra;


    public Category(){}
    public Category(String nombre, String precio, String cantidad, String familia, String img, String extra) {
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
        this.familia = familia;
        this.img = img;
        this.extra = extra;
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

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getFamilia() {
        return familia;
    }

    public void setFamilia(String familia) {
        this.familia = familia;
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
}

