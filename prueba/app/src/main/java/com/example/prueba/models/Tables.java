package com.example.prueba.models;

import android.media.Image;

public class Tables {
    int img;
    String nombre;

    public Tables(int img, String nombre) {
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
