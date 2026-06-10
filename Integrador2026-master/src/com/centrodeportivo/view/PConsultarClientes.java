package com.centrodeportivo.view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.centrodeportivo.model.data.Cliente;
import com.centrodeportivo.model.db.ClienteDAO;

public class PConsultarClientes extends JPanel {

    private JTable tblClientes;
    private DefaultTableModel dtmClientes;

    public PConsultarClientes() {

        setLayout(null);

        JLabel lblTitulo = new JLabel("CONSULTA DE CLIENTES");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblTitulo.setBounds(30, 25, 300, 30);
        add(lblTitulo);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(30, 80, 800, 350);
        add(scrollPane);

        tblClientes = new JTable();

        dtmClientes = new DefaultTableModel();
        dtmClientes.addColumn("ID");
        dtmClientes.addColumn("Nombre");
        dtmClientes.addColumn("Apellidos");
        dtmClientes.addColumn("Teléfono");
        dtmClientes.addColumn("Email");
        dtmClientes.addColumn("Cuota");

        tblClientes.setModel(dtmClientes);
        scrollPane.setViewportView(tblClientes);

        cargarClientes();
    }

    private void cargarClientes() {

        Cliente[] clientes = ClienteDAO.obtenerClientes();

        for (int i = 0; i < clientes.length; i++) {

            if (clientes[i] != null) {

                dtmClientes.addRow(new Object[] {
                        clientes[i].getIdCliente(),
                        clientes[i].getNombre(),
                        clientes[i].getApellidos(),
                        clientes[i].getTelefono(),
                        clientes[i].getEmail(),
                        clientes[i].getCuotaMensual()
                });
            }
        }
    }
}