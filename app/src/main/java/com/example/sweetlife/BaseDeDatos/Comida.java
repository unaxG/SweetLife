package com.example.sweetlife.BaseDeDatos;

import java.io.Serializable;
import java.util.ArrayList;

public class Comida implements Serializable {

    private String nombre;
    private String descripcion;
    private String ingredientes;

    public Comida(String nombre, String descripcion, String ingredientes) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.ingredientes = ingredientes;
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
}
