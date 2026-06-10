package com.centrodeportivo.model.data;

public class Entrenador {

    private int idEntrenador;
    private String nombre;
    private String apellidos;
    private String telefono;
    private String email;
    private String especialidad;
    private double salario;

    public Entrenador(int idEntrenador, String nombre, String apellidos, String telefono, String email, String especialidad, double salario) {
        this.idEntrenador = idEntrenador;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.email = email;
        this.especialidad = especialidad;
        this.salario = salario;
    }

    public int getIdEntrenador() {
        return idEntrenador;
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

    public String getEspecialidad() {
        return especialidad;
    }

    public double getSalario() {
        return salario;
    }
}