package com.example.sweetlife.BaseDeDatos;

import java.io.Serializable;

public class Informacion  implements Serializable {

    private String nombre;
    private String apellidos;
    private String fechaNacimiento;
    private float altura;
    private float peso;
    private boolean fumar;
    private boolean problemasCorazon;
    private boolean asma;
    private boolean presionBaja;
    private boolean presionAlta;
    private boolean artritis;
    private boolean acidoUrico;
    private boolean diabetes;


    public Informacion(String nombre, String apellidos, String fechaNacimiento, float peso, float altura, boolean fumar, boolean probelamasCorazon, boolean asma, boolean presionBajaa, boolean presionAlta, boolean artritis, boolean acidoUrico, boolean diabetes) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.peso = peso;
        this.altura = altura;
        this.fumar = fumar;
        this.problemasCorazon = probelamasCorazon;
        this.asma = asma;
        this.presionBaja = presionBajaa;
        this.presionAlta = presionAlta;
        this.artritis = artritis;
        this.acidoUrico = acidoUrico;
        this.diabetes = diabetes;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public boolean isFumar() {
        return fumar;
    }

    public void setFumar(boolean fumar) {
        this.fumar = fumar;
    }

    public boolean isProblemasCorazon() {
        return problemasCorazon;
    }

    public void setProblemasCorazon(boolean problemasCorazon) {
        this.problemasCorazon = problemasCorazon;
    }

    public boolean isAsma() {
        return asma;
    }

    public void setAsma(boolean asma) {
        this.asma = asma;
    }

    public boolean isPresionBaja() {
        return presionBaja;
    }

    public void setPresionBaja(boolean presionBaja) {
        this.presionBaja = presionBaja;
    }

    public boolean isPresionAlta() {
        return presionAlta;
    }

    public void setPresionAlta(boolean presionAlta) {
        this.presionAlta = presionAlta;
    }

    public boolean isArtritis() {
        return artritis;
    }

    public void setArtritis(boolean artritis) {
        this.artritis = artritis;
    }

    public boolean isAcidoUrico() {
        return acidoUrico;
    }

    public void setAcidoUrico(boolean acidoUrico) {
        this.acidoUrico = acidoUrico;
    }

    public boolean isDiabetes() {
        return diabetes;
    }

    public void setDiabetes(boolean diabetes) {
        this.diabetes = diabetes;
    }

    public float getAltura() {
        return altura;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }

    public Informacion() {

    }
}
