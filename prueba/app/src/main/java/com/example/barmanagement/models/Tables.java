package com.example.barmanagement.models;


public class Tables {
    private String num;
    private String zona;
    private String NumComensales;
    private String reservado;
    private String img;

    public Tables(){}

    public Tables(String num, String zona, String numComensales, String reservado, String img) {
        this.num = num;
        this.zona = zona;
        NumComensales = numComensales;
        this.reservado = reservado;
        this.img = img;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public String getNumComensales() {
        return NumComensales;
    }

    public void setNumComensales(String numComensales) {
        NumComensales = numComensales;
    }

    public String getReservado() {
        return reservado;
    }

    public void setReservado(String reservado) {
        this.reservado = reservado;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
