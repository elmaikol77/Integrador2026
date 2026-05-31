package com.centrodeportivo.view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.centrodeportivo.model.data.Sala;
import com.centrodeportivo.model.db.SalaDAO;

public class PConsultarSalas extends JPanel {

    private JTable tblSalas;
    private DefaultTableModel dtmSalas;

    public PConsultarSalas() {

        setLayout(null);

        JLabel lblTitulo = new JLabel("CONSULTA DE SALAS");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblTitulo.setBounds(30, 25, 300, 30);
        add(lblTitulo);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(30, 80, 800, 350);
        add(scrollPane);

        tblSalas = new JTable();

        dtmSalas = new DefaultTableModel();
        dtmSalas.addColumn("ID");
        dtmSalas.addColumn("Nombre");
        dtmSalas.addColumn("Capacidad");
        dtmSalas.addColumn("Tipo");
        dtmSalas.addColumn("Planta");

        tblSalas.setModel(dtmSalas);
        scrollPane.setViewportView(tblSalas);

        cargarSalas();
    }

    private void cargarSalas() {

        Sala[] salas = SalaDAO.obtenerSalas();

        for (int i = 0; i < salas.length; i++) {

            if (salas[i] != null) {

                dtmSalas.addRow(new Object[] {
                        salas[i].getIdSala(),
                        salas[i].getNombre(),
                        salas[i].getCapacidad(),
                        salas[i].getTipo(),
                        salas[i].getPlanta()
                });
            }
        }
    }
}