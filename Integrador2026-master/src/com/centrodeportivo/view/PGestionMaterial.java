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

import com.centrodeportivo.model.data.Material;
import com.centrodeportivo.model.db.MaterialDAO;

public class PGestionMaterial extends JPanel {

    private JTable tblMaterial;
    private DefaultTableModel dtmMaterial;

    private JTextField txtNombre;
    private JTextField txtTipo;
    private JTextField txtCantidad;
    private JTextField txtEstado;
    private JTextField txtIdSala;

    private JButton btnGuardar;
    private JButton btnModificar;
    private JButton btnEliminar;
    private JButton btnLimpiar;

    private int idSeleccionado = -1;

    public PGestionMaterial() {

        setLayout(null);

        JLabel lblTitulo = new JLabel("GESTIÓN DE MATERIAL");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblTitulo.setBounds(30, 15, 300, 30);
        add(lblTitulo);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(30, 55, 820, 200);
        add(scrollPane);

        tblMaterial = new JTable();
        tblMaterial.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        dtmMaterial = new DefaultTableModel() {
            public boolean isCellEditable(int row, int col) { return false; }
        };
        dtmMaterial.addColumn("ID");
        dtmMaterial.addColumn("Nombre");
        dtmMaterial.addColumn("Tipo");
        dtmMaterial.addColumn("Cantidad");
        dtmMaterial.addColumn("Estado");
        dtmMaterial.addColumn("ID Sala");

        tblMaterial.setModel(dtmMaterial);
        scrollPane.setViewportView(tblMaterial);

        tblMaterial.getSelectionModel().addListSelectionListener(e -> {
            int fila = tblMaterial.getSelectedRow();
            if (fila >= 0) {
                idSeleccionado = (int) dtmMaterial.getValueAt(fila, 0);
                txtNombre.setText(dtmMaterial.getValueAt(fila, 1).toString());
                txtTipo.setText(dtmMaterial.getValueAt(fila, 2).toString());
                txtCantidad.setText(dtmMaterial.getValueAt(fila, 3).toString());
                txtEstado.setText(dtmMaterial.getValueAt(fila, 4).toString());
                txtIdSala.setText(dtmMaterial.getValueAt(fila, 5).toString());
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

        JLabel lblTipo = new JLabel("Tipo:");
        lblTipo.setBounds(labelX, startY + rowH, 120, 25);
        add(lblTipo);
        txtTipo = new JTextField();
        txtTipo.setBounds(fieldX, startY + rowH, fieldW, 25);
        add(txtTipo);

        JLabel lblCantidad = new JLabel("Cantidad:");
        lblCantidad.setBounds(labelX, startY + rowH * 2, 120, 25);
        add(lblCantidad);
        txtCantidad = new JTextField();
        txtCantidad.setBounds(fieldX, startY + rowH * 2, fieldW, 25);
        add(txtCantidad);

        JLabel lblEstado = new JLabel("Estado:");
        lblEstado.setBounds(430, startY, 120, 25);
        add(lblEstado);
        txtEstado = new JTextField();
        txtEstado.setBounds(510, startY, fieldW, 25);
        add(txtEstado);

        JLabel lblIdSala = new JLabel("ID Sala:");
        lblIdSala.setBounds(430, startY + rowH, 120, 25);
        add(lblIdSala);
        txtIdSala = new JTextField();
        txtIdSala.setBounds(510, startY + rowH, 150, 25);
        add(txtIdSala);


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

        btnGuardar.addActionListener(e -> {
            if (!validarCampos()) return;
            Material m = new Material(0, txtNombre.getText(), txtTipo.getText(),
                    Integer.parseInt(txtCantidad.getText()), txtEstado.getText(),
                    Integer.parseInt(txtIdSala.getText()));
            if (MaterialDAO.insertarMaterial(m)) {
                JOptionPane.showMessageDialog(this, "Material registrado correctamente.");
                recargarTabla();
                limpiarFormulario();
            } else {
                JOptionPane.showMessageDialog(this, "Error al registrar el material.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnModificar.addActionListener(e -> {
            if (idSeleccionado == -1) {
                JOptionPane.showMessageDialog(this, "Selecciona un material de la tabla.");
                return;
            }
            if (!validarCampos()) return;
            Material m = new Material(idSeleccionado, txtNombre.getText(), txtTipo.getText(),
                    Integer.parseInt(txtCantidad.getText()), txtEstado.getText(),
                    Integer.parseInt(txtIdSala.getText()));
            if (MaterialDAO.modificarMaterial(m)) {
                JOptionPane.showMessageDialog(this, "Material modificado correctamente.");
                recargarTabla();
                limpiarFormulario();
            } else {
                JOptionPane.showMessageDialog(this, "Error al modificar el material.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnEliminar.addActionListener(e -> {
            if (idSeleccionado == -1) {
                JOptionPane.showMessageDialog(this, "Selecciona un material de la tabla.");
                return;
            }
            int confirm = JOptionPane.showConfirmDialog(this,
                    "¿Seguro que quieres eliminar el material con ID " + idSeleccionado + "?",
                    "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                if (MaterialDAO.eliminarMaterial(idSeleccionado)) {
                    JOptionPane.showMessageDialog(this, "Material eliminado correctamente.");
                    recargarTabla();
                    limpiarFormulario();
                } else {
                    JOptionPane.showMessageDialog(this, "Error al eliminar el material.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnLimpiar.addActionListener(e -> limpiarFormulario());

        cargarMaterial();
    }

    private void cargarMaterial() {

        dtmMaterial.setRowCount(0);
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

    private void recargarTabla() {
        idSeleccionado = -1;
        cargarMaterial();
    }

    private void limpiarFormulario() {
        idSeleccionado = -1;
        txtNombre.setText("");
        txtTipo.setText("");
        txtCantidad.setText("");
        txtEstado.setText("");
        txtIdSala.setText("");
        tblMaterial.clearSelection();
        btnGuardar.setEnabled(true);
        btnModificar.setEnabled(false);
        btnEliminar.setEnabled(false);
    }

    private boolean validarCampos() {
        if (txtNombre.getText().trim().isEmpty() ||
                txtTipo.getText().trim().isEmpty() ||
                txtCantidad.getText().trim().isEmpty() ||
                txtEstado.getText().trim().isEmpty() ||
                txtIdSala.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.");
            return false;
        }
        try {
            Integer.parseInt(txtCantidad.getText().trim());
            Integer.parseInt(txtIdSala.getText().trim());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Cantidad e ID Sala deben ser números enteros.");
            return false;
        }
        return true;
    }
}
