package com.centrodeportivo.view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.centrodeportivo.model.data.Material;
import com.centrodeportivo.model.db.MaterialDAO;

public class PConsultarMaterial extends JPanel {

    private JTable tblMaterial;
    private DefaultTableModel dtmMaterial;

    public PConsultarMaterial() {

        setLayout(null);

        JLabel lblTitulo = new JLabel("CONSULTA DE MATERIAL");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblTitulo.setBounds(30, 25, 300, 30);
        add(lblTitulo);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(30, 80, 800, 350);
        add(scrollPane);

        tblMaterial = new JTable();

        dtmMaterial = new DefaultTableModel();
        dtmMaterial.addColumn("ID");
        dtmMaterial.addColumn("Nombre");
        dtmMaterial.addColumn("Tipo");
        dtmMaterial.addColumn("Cantidad");
        dtmMaterial.addColumn("Estado");
        dtmMaterial.addColumn("ID Sala");

        tblMaterial.setModel(dtmMaterial);
        scrollPane.setViewportView(tblMaterial);

        cargarMaterial();
    }

    private void cargarMaterial() {

        Material[] materiales = MaterialDAO.obtenerMaterial();

        for (int i = 0; i < materiales.length; i++) {

            if (materiales[i] != null) {

                dtmMaterial.addRow(new Object[] {
                        materiales[i].getIdMaterial(),
                        materiales[i].getNombre(),
                        materiales[i].getTipo(),
                        materiales[i].getCantidad(),
                        materiales[i].getEstado(),
                        materiales[i].getIdSala()
                });
            }
        }
    }
}