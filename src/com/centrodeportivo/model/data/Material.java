package com.centrodeportivo.model.data;

public class Material {

    private int idMaterial;
    private String nombre;
    private String tipo;
    private int cantidad;
    private String estado;
    private int idSala;

    public Material(int idMaterial, String nombre, String tipo, int cantidad, String estado, int idSala) {
        this.idMaterial = idMaterial;
        this.nombre = nombre;
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.estado = estado;
        this.idSala = idSala;
    }

    public int getIdMaterial() {
        return idMaterial;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public String getEstado() {
        return estado;
    }

    public int getIdSala() {
        return idSala;
    }
}