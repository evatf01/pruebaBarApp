package com.example.barmanagement.models;

import java.io.Serializable;

public class User implements Serializable {
    String type;
    String name;
    String password;
    String dni;
    String email;
    String phone;
    String codigo;

    public User() { }

    public User(String type, String name, String password, String dni, String email, String phone, String codigo) {
        this.type = type;
        this.name = name;
        this.password = password;
        this.dni = dni;
        this.email = email;
        this.phone = phone;
        this.codigo = codigo;
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }


    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "User{" +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", dni='" + dni + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
