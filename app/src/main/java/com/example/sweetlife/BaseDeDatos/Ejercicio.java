package com.example.sweetlife.BaseDeDatos;

import java.io.Serializable;

public class Ejercicio implements Serializable {

    private String Nombre;
    private String Descripcion;
    private String Material;
    private String Url;

    private String Filtros;

    public Ejercicio(String nombre, String descripcion, String material, String url, String filtros) {
        Nombre = nombre;
        Descripcion = descripcion;
        Material = material;
        Url = url;
        Filtros = filtros;
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

    public String getFiltros() {
        return Filtros;
    }

    public void setFiltros(String filtros) {
        Filtros = filtros;
    }

}
