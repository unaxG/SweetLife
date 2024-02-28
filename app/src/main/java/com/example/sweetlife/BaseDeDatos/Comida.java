package com.example.sweetlife.BaseDeDatos;

import java.io.Serializable;
import java.util.ArrayList;

public class Comida implements Serializable {

    private String nombre;
    private String descripcion;
    private String ingredientes;

    private String url;

    public Comida(String nombre, String descripcion, String ingredientes, String url) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.ingredientes = ingredientes;
        this.url = url;
    }

    public Comida() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
