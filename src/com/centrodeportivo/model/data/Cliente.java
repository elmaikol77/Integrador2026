package com.centrodeportivo.model.data;

public class Cliente {

    private int idCliente;
    private String nombre;
    private String apellidos;
    private String telefono;
    private String email;
    private double cuotaMensual;

    public Cliente(int idCliente, String nombre, String apellidos, String telefono, String email, double cuotaMensual) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.email = email;
        this.cuotaMensual = cuotaMensual;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getEmail() {
        return email;
    }

    public double getCuotaMensual() {
        return cuotaMensual;
    }
}