package com.example.sweetlife.BaseDeDatos;

import java.io.Serializable;

public class Ejercicio implements Serializable {

    private String Nombre;
    private String Descripcion;
    private String Material;
    private String Url;

    public Ejercicio(String nombre, String descripcion, String material, String url) {
        Nombre = nombre;
        Descripcion = descripcion;
        Material = material;
        Url = url;
    }

    public Ejercicio() {
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public String getMaterial() {
        return Material;
    }

    public void setMaterial(String material) {
        Material = material;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }
}
