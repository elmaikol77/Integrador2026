package com.centrodeportivo.view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.centrodeportivo.model.data.Entrenador;
import com.centrodeportivo.model.db.EntrenadorDAO;

public class PConsultarEntrenadores extends JPanel {

    private JTable tblEntrenadores;
    private DefaultTableModel dtmEntrenadores;

    public PConsultarEntrenadores() {

        setLayout(null);

        JLabel lblTitulo = new JLabel("CONSULTA DE ENTRENADORES");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblTitulo.setBounds(30, 25, 350, 30);
        add(lblTitulo);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(30, 80, 800, 350);
        add(scrollPane);

        tblEntrenadores = new JTable();

        dtmEntrenadores = new DefaultTableModel();
        dtmEntrenadores.addColumn("ID");
        dtmEntrenadores.addColumn("Nombre");
        dtmEntrenadores.addColumn("Apellidos");
        dtmEntrenadores.addColumn("Teléfono");
        dtmEntrenadores.addColumn("Email");
        dtmEntrenadores.addColumn("Especialidad");
        dtmEntrenadores.addColumn("Salario");

        tblEntrenadores.setModel(dtmEntrenadores);
        scrollPane.setViewportView(tblEntrenadores);

        cargarEntrenadores();
    }

    private void cargarEntrenadores() {

        Entrenador[] entrenadores = EntrenadorDAO.obtenerEntrenadores();

        for (int i = 0; i < entrenadores.length; i++) {

            if (entrenadores[i] != null) {

                dtmEntrenadores.addRow(new Object[] {
                        entrenadores[i].getIdEntrenador(),
                        entrenadores[i].getNombre(),
                        entrenadores[i].getApellidos(),
                        entrenadores[i].getTelefono(),
                        entrenadores[i].getEmail(),
                        entrenadores[i].getEspecialidad(),
                        entrenadores[i].getSalario()
                });
            }
        }
    }
}