package com.example.prueba.models;

public class Category {
    private int img;
    private String nombre;

    public Category(int img, String nombre) {
        this.img = img;
        this.nombre = nombre;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
