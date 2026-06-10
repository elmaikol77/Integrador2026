package com.centrodeportivo.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import com.centrodeportivo.model.data.Cliente;
import com.centrodeportivo.model.db.ClienteDAO;

public class PGestionClientes extends JPanel {

    private JTable tblClientes;
    private DefaultTableModel dtmClientes;

    private JTextField txtNombre;
    private JTextField txtApellidos;
    private JTextField txtTelefono;
    private JTextField txtEmail;
    private JTextField txtCuota;

    private JButton btnGuardar;
    private JButton btnModificar;
    private JButton btnEliminar;
    private JButton btnLimpiar;

    private int idSeleccionado = -1;

    public PGestionClientes() {

        setLayout(null);

        // Título
        JLabel lblTitulo = new JLabel("GESTIÓN DE CLIENTES");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblTitulo.setBounds(30, 15, 300, 30);
        add(lblTitulo);

        // Tabla
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(30, 55, 820, 200);
        add(scrollPane);

        tblClientes = new JTable();
        tblClientes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        dtmClientes = new DefaultTableModel() {
            public boolean isCellEditable(int row, int col) { return false; }
        };
        dtmClientes.addColumn("ID");
        dtmClientes.addColumn("Nombre");
        dtmClientes.addColumn("Apellidos");
        dtmClientes.addColumn("Teléfono");
        dtmClientes.addColumn("Email");
        dtmClientes.addColumn("Cuota");

        tblClientes.setModel(dtmClientes);
        scrollPane.setViewportView(tblClientes);

        // Selección de fila
        tblClientes.getSelectionModel().addListSelectionListener(e -> {
            int fila = tblClientes.getSelectedRow();
            if (fila >= 0) {
                idSeleccionado = (int) dtmClientes.getValueAt(fila, 0);
                txtNombre.setText(dtmClientes.getValueAt(fila, 1).toString());
                txtApellidos.setText(dtmClientes.getValueAt(fila, 2).toString());
                txtTelefono.setText(dtmClientes.getValueAt(fila, 3).toString());
                txtEmail.setText(dtmClientes.getValueAt(fila, 4).toString());
                txtCuota.setText(dtmClientes.getValueAt(fila, 5).toString());
                btnGuardar.setEnabled(false);
                btnModificar.setEnabled(true);
                btnEliminar.setEnabled(true);
            }
        });

        // Etiquetas y campos del formulario
        int labelX = 30, fieldX = 160, fieldW = 200, rowH = 35, startY = 275;

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(labelX, startY, 120, 25);
        add(lblNombre);
        txtNombre = new JTextField();
        txtNombre.setBounds(fieldX, startY, fieldW, 25);
        add(txtNombre);

        JLabel lblApellidos = new JLabel("Apellidos:");
        lblApellidos.setBounds(labelX, startY + rowH, 120, 25);
        add(lblApellidos);
        txtApellidos = new JTextField();
        txtApellidos.setBounds(fieldX, startY + rowH, fieldW, 25);
        add(txtApellidos);

        JLabel lblTelefono = new JLabel("Teléfono:");
        lblTelefono.setBounds(labelX, startY + rowH * 2, 120, 25);
        add(lblTelefono);
        txtTelefono = new JTextField();
        txtTelefono.setBounds(fieldX, startY + rowH * 2, fieldW, 25);
        add(txtTelefono);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setBounds(430, startY, 120, 25);
        add(lblEmail);
        txtEmail = new JTextField();
        txtEmail.setBounds(510, startY, fieldW, 25);
        add(txtEmail);

        JLabel lblCuota = new JLabel("Cuota mensual:");
        lblCuota.setBounds(430, startY + rowH, 120, 25);
        add(lblCuota);
        txtCuota = new JTextField();
        txtCuota.setBounds(560, startY + rowH, 150, 25);
        add(txtCuota);

        // Botones

        btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(155, 460, 110, 30);
        add(btnGuardar);

        btnModificar = new JButton("Modificar");
        btnModificar.setBounds(280, 460, 110, 30);
        add(btnModificar);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(405, 460, 110, 30);
        add(btnEliminar);

        btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setBounds(530, 460, 110, 30);
        add(btnLimpiar);

        // Listeners
        btnGuardar.addActionListener(e -> {
            if (!validarCampos()) return;
            Cliente c = new Cliente(0, txtNombre.getText(), txtApellidos.getText(),
                    txtTelefono.getText(), txtEmail.getText(), Double.parseDouble(txtCuota.getText()));
            if (ClienteDAO.insertarCliente(c)) {
                JOptionPane.showMessageDialog(this, "Cliente registrado correctamente.");
                recargarTabla();
                limpiarFormulario();
            } else {
                JOptionPane.showMessageDialog(this, "Error al registrar el cliente.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnModificar.addActionListener(e -> {
            if (idSeleccionado == -1) {
                JOptionPane.showMessageDialog(this, "Selecciona un cliente de la tabla.");
                return;
            }
            if (!validarCampos()) return;
            Cliente c = new Cliente(idSeleccionado, txtNombre.getText(), txtApellidos.getText(),
                    txtTelefono.getText(), txtEmail.getText(), Double.parseDouble(txtCuota.getText()));
            if (ClienteDAO.modificarCliente(c)) {
                JOptionPane.showMessageDialog(this, "Cliente modificado correctamente.");
                recargarTabla();
                limpiarFormulario();
            } else {
                JOptionPane.showMessageDialog(this, "Error al modificar el cliente.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnEliminar.addActionListener(e -> {
            if (idSeleccionado == -1) {
                JOptionPane.showMessageDialog(this, "Selecciona un cliente de la tabla.");
                return;
            }
            int confirm = JOptionPane.showConfirmDialog(this,
                    "¿Seguro que quieres eliminar el cliente con ID " + idSeleccionado + "?",
                    "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                if (ClienteDAO.eliminarCliente(idSeleccionado)) {
                    JOptionPane.showMessageDialog(this, "Cliente eliminado correctamente.");
                    recargarTabla();
                    limpiarFormulario();
                } else {
                    JOptionPane.showMessageDialog(this, "Error al eliminar el cliente.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnLimpiar.addActionListener(e -> limpiarFormulario());

        cargarClientes();
    }

    private void cargarClientes() {

        dtmClientes.setRowCount(0);
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

    private void recargarTabla() {
        idSeleccionado = -1;
        cargarClientes();
    }

    private void limpiarFormulario() {
        idSeleccionado = -1;
        txtNombre.setText("");
        txtApellidos.setText("");
        txtTelefono.setText("");
        txtEmail.setText("");
        txtCuota.setText("");
        tblClientes.clearSelection();
        btnGuardar.setEnabled(true);
        btnModificar.setEnabled(false);
        btnEliminar.setEnabled(false);
    }

    private boolean validarCampos() {
        if (txtNombre.getText().trim().isEmpty() ||
                txtApellidos.getText().trim().isEmpty() ||
                txtTelefono.getText().trim().isEmpty() ||
                txtEmail.getText().trim().isEmpty() ||
                txtCuota.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.");
            return false;
        }
        try {
            Double.parseDouble(txtCuota.getText().trim());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "La cuota mensual debe ser un número.");
            return false;
        }
        return true;
    }
}
