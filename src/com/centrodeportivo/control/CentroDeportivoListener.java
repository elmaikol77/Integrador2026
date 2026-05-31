package com.centrodeportivo.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.centrodeportivo.view.PConsultarClientes;
import com.centrodeportivo.view.PConsultarEntrenadores;
import com.centrodeportivo.view.PConsultarMaterial;
import com.centrodeportivo.view.PConsultarSalas;
import com.centrodeportivo.view.PInicio;
import com.centrodeportivo.view.VPCentroDeportivo;

public class CentroDeportivoListener implements ActionListener {

    private VPCentroDeportivo ventana;

    public CentroDeportivoListener(VPCentroDeportivo ventana) {
        this.ventana = ventana;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Object source = e.getSource();

        if (source == ventana.getMntmInicio()) {
            ventana.cargarPanel(new PInicio());
        }

        else if (source == ventana.getMntmClientes()) {
            ventana.cargarPanel(new PConsultarClientes());
        }

        else if (source == ventana.getMntmEntrenadores()) {
            ventana.cargarPanel(new PConsultarEntrenadores());
        }

        else if (source == ventana.getMntmSalas()) {
            ventana.cargarPanel(new PConsultarSalas());
        }

        else if (source == ventana.getMntmMaterial()) {
            ventana.cargarPanel(new PConsultarMaterial());
        }

        else if (source == ventana.getMntmSalir()) {
            System.exit(0);
        }
    }
}