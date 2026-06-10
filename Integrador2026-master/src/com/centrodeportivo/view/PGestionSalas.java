package com.centrodeportivo.view;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import com.centrodeportivo.model.data.Sala;
import com.centrodeportivo.model.db.SalaDAO;

public class PGestionSalas extends JPanel {

    private JTable tblSalas;
    private DefaultTableModel dtmSalas;

    private JTextField txtNombre;
    private JTextField txtCapacidad;
    private JTextField txtTipo;
    private JTextField txtPlanta;

    private JButton btnGuardar;
    private JButton btnModificar;
    private JButton btnEliminar;
    private JButton btnLimpiar;

    private int idSeleccionado = -1;

    public PGestionSalas() {

        setLayout(null);

        JLabel lblTitulo = new JLabel("GESTIÓN DE SALAS");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblTitulo.setBounds(30, 15, 300, 30);
        add(lblTitulo);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(30, 55, 820, 200);
        add(scrollPane);

        tblSalas = new JTable();
        tblSalas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        dtmSalas = new DefaultTableModel() {
            public boolean isCellEditable(int row, int col) { return false; }
        };
        dtmSalas.addColumn("ID");
        dtmSalas.addColumn("Nombre");
        dtmSalas.addColumn("Capacidad");
        dtmSalas.addColumn("Tipo");
        dtmSalas.addColumn("Planta");

        tblSalas.setModel(dtmSalas);
        scrollPane.setViewportView(tblSalas);

        tblSalas.getSelectionModel().addListSelectionListener(e -> {
            int fila = tblSalas.getSelectedRow();
            if (fila >= 0) {
                idSeleccionado = (int) dtmSalas.getValueAt(fila, 0);
                txtNombre.setText(dtmSalas.getValueAt(fila, 1).toString());
                txtCapacidad.setText(dtmSalas.getValueAt(fila, 2).toString());
                txtTipo.setText(dtmSalas.getValueAt(fila, 3).toString());
                txtPlanta.setText(dtmSalas.getValueAt(fila, 4).toString());
                btnGuardar.setEnabled(false);
                btnModificar.setEnabled(true);
                btnEliminar.setEnabled(true);
            }
        });

        int labelX = 30, fieldX = 160, fieldW = 200, rowH = 35, startY = 275;

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(labelX, startY, 120, 25);
        add(lblNombre);
        txtNombre = new JTextField();
        txtNombre.setBounds(fieldX, startY, fieldW, 25);
        add(txtNombre);

        JLabel lblCapacidad = new JLabel("Capacidad:");
        lblCapacidad.setBounds(labelX, startY + rowH, 120, 25);
        add(lblCapacidad);
        txtCapacidad = new JTextField();
        txtCapacidad.setBounds(fieldX, startY + rowH, fieldW, 25);
        add(txtCapacidad);

        JLabel lblTipo = new JLabel("Tipo:");
        lblTipo.setBounds(430, startY, 120, 25);
        add(lblTipo);
        txtTipo = new JTextField();
        txtTipo.setBounds(510, startY, fieldW, 25);
        add(txtTipo);

        JLabel lblPlanta = new JLabel("Planta:");
        lblPlanta.setBounds(430, startY + rowH, 120, 25);
        add(lblPlanta);
        txtPlanta = new JTextField();
        txtPlanta.setBounds(510, startY + rowH, 150, 25);
        add(txtPlanta);


        btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(155, 400, 110, 30);
        add(btnGuardar);

        btnModificar = new JButton("Modificar");
        btnModificar.setBounds(280, 400, 110, 30);
        add(btnModificar);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(405, 400, 110, 30);
        add(btnEliminar);

        btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setBounds(530, 400, 110, 30);
        add(btnLimpiar);

        btnGuardar.addActionListener(e -> {
            if (!validarCampos()) return;
            Sala s = new Sala(0, txtNombre.getText(), Integer.parseInt(txtCapacidad.getText()),
                    txtTipo.getText(), Integer.parseInt(txtPlanta.getText()));
            if (SalaDAO.insertarSala(s)) {
                JOptionPane.showMessageDialog(this, "Sala registrada correctamente.");
                recargarTabla();
                limpiarFormulario();
            } else {
                JOptionPane.showMessageDialog(this, "Error al registrar la sala.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnModificar.addActionListener(e -> {
            if (idSeleccionado == -1) {
                JOptionPane.showMessageDialog(this, "Selecciona una sala de la tabla.");
                return;
            }
            if (!validarCampos()) return;
            Sala s = new Sala(idSeleccionado, txtNombre.getText(), Integer.parseInt(txtCapacidad.getText()),
                    txtTipo.getText(), Integer.parseInt(txtPlanta.getText()));
            if (SalaDAO.modificarSala(s)) {
                JOptionPane.showMessageDialog(this, "Sala modificada correctamente.");
                recargarTabla();
                limpiarFormulario();
            } else {
                JOptionPane.showMessageDialog(this, "Error al modificar la sala.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnEliminar.addActionListener(e -> {
            if (idSeleccionado == -1) {
                JOptionPane.showMessageDialog(this, "Selecciona una sala de la tabla.");
                return;
            }
            int confirm = JOptionPane.showConfirmDialog(this,
                    "¿Seguro que quieres eliminar la sala con ID " + idSeleccionado + "?",
                    "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                if (SalaDAO.eliminarSala(idSeleccionado)) {
                    JOptionPane.showMessageDialog(this, "Sala eliminada correctamente.");
                    recargarTabla();
                    limpiarFormulario();
                } else {
                    JOptionPane.showMessageDialog(this, "Error al eliminar la sala.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnLimpiar.addActionListener(e -> limpiarFormulario());

        cargarSalas();
    }

    private void cargarSalas() {

        dtmSalas.setRowCount(0);
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

    private void recargarTabla() {
        idSeleccionado = -1;
        cargarSalas();
    }

    private void limpiarFormulario() {
        idSeleccionado = -1;
        txtNombre.setText("");
        txtCapacidad.setText("");
        txtTipo.setText("");
        txtPlanta.setText("");
        tblSalas.clearSelection();
        btnGuardar.setEnabled(true);
        btnModificar.setEnabled(false);
        btnEliminar.setEnabled(false);
    }

    private boolean validarCampos() {
        if (txtNombre.getText().trim().isEmpty() ||
                txtCapacidad.getText().trim().isEmpty() ||
                txtTipo.getText().trim().isEmpty() ||
                txtPlanta.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.");
            return false;
        }
        try {
            Integer.parseInt(txtCapacidad.getText().trim());
            Integer.parseInt(txtPlanta.getText().trim());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Capacidad y planta deben ser números enteros.");
            return false;
        }
        return true;
    }
}
