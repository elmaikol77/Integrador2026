package com.centrodeportivo.model.data;

public class Sala {

    private int idSala;
    private String nombre;
    private int capacidad;
    private String tipo;
    private int planta;

    public Sala(int idSala, String nombre, int capacidad, String tipo, int planta) {
        this.idSala = idSala;
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.tipo = tipo;
        this.planta = planta;
    }

    public int getIdSala() {
        return idSala;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public String getTipo() {
        return tipo;
    }

    public int getPlanta() {
        return planta;
    }
}