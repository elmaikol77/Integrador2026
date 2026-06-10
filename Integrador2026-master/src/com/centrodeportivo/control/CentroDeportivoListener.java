package com.centrodeportivo.control;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;
import java.awt.event.ActionListener;

import com.centrodeportivo.view.PConsultarClientes;
import com.centrodeportivo.view.PConsultarEntrenadores;
import com.centrodeportivo.view.PConsultarMaterial;
import com.centrodeportivo.view.PConsultarSalas;
import com.centrodeportivo.view.PGestionClientes;
import com.centrodeportivo.view.PGestionEntrenadores;
import com.centrodeportivo.view.PGestionMaterial;
import com.centrodeportivo.view.PGestionSalas;
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

        // Consulta
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

        // Gestión (registro, modificación, eliminación)
        else if (source == ventana.getMntmGestionClientes()) {
            ventana.cargarPanel(new PGestionClientes());
        }
        else if (source == ventana.getMntmGestionEntrenadores()) {
            ventana.cargarPanel(new PGestionEntrenadores());
        }
        else if (source == ventana.getMntmGestionSalas()) {
            ventana.cargarPanel(new PGestionSalas());
        }
        else if (source == ventana.getMntmGestionMaterial()) {
            ventana.cargarPanel(new PGestionMaterial());
        }

        else if (source == ventana.getMntmSalir()) {
            int opcion = JOptionPane.showConfirmDialog(
                ventana,
                "¿Estás seguro de que quieres salir de la aplicación?",
                "Confirmar salida",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
            );
            if (opcion == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        }
    }
}
