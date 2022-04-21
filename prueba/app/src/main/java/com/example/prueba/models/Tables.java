package com.example.prueba.models;

import android.media.Image;

public class Tables {
    int img;
    String zona;
    String num;

    public Tables(int img, String num) {
        this.img = img;
        this.num = num;
    }

    public Tables(int img, String zona, String num) {
        this.img = img;
        this.zona = zona;
        this.num = num;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }
}
