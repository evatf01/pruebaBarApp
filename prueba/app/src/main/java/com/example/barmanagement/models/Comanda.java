package com.example.barmanagement.models;

public class Comanda {
    String nombre;
    String cantidad;

    public Comanda() { }

    public Comanda(String nombre, String cantidad) {
        this.nombre = nombre;
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "Comanda{" +
                "nombre='" + nombre + '\'' +
                ", cantidad='" + cantidad + '\'' +
                '}';
    }


}
