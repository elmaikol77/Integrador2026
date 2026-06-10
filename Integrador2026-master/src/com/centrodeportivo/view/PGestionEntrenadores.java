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

import com.centrodeportivo.model.data.Entrenador;
import com.centrodeportivo.model.db.EntrenadorDAO;

public class PGestionEntrenadores extends JPanel {

    private JTable tblEntrenadores;
    private DefaultTableModel dtmEntrenadores;

    private JTextField txtNombre;
    private JTextField txtApellidos;
    private JTextField txtTelefono;
    private JTextField txtEmail;
    private JTextField txtEspecialidad;
    private JTextField txtSalario;

    private JButton btnGuardar;
    private JButton btnModificar;
    private JButton btnEliminar;
    private JButton btnLimpiar;

    private int idSeleccionado = -1;

    public PGestionEntrenadores() {

        setLayout(null);

        JLabel lblTitulo = new JLabel("GESTIÓN DE ENTRENADORES");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblTitulo.setBounds(30, 15, 350, 30);
        add(lblTitulo);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(30, 55, 820, 200);
        add(scrollPane);

        tblEntrenadores = new JTable();
        tblEntrenadores.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        dtmEntrenadores = new DefaultTableModel() {
            public boolean isCellEditable(int row, int col) { return false; }
        };
        dtmEntrenadores.addColumn("ID");
        dtmEntrenadores.addColumn("Nombre");
        dtmEntrenadores.addColumn("Apellidos");
        dtmEntrenadores.addColumn("Teléfono");
        dtmEntrenadores.addColumn("Email");
        dtmEntrenadores.addColumn("Especialidad");
        dtmEntrenadores.addColumn("Salario");

        tblEntrenadores.setModel(dtmEntrenadores);
        scrollPane.setViewportView(tblEntrenadores);

        tblEntrenadores.getSelectionModel().addListSelectionListener(e -> {
            int fila = tblEntrenadores.getSelectedRow();
            if (fila >= 0) {
                idSeleccionado = (int) dtmEntrenadores.getValueAt(fila, 0);
                txtNombre.setText(dtmEntrenadores.getValueAt(fila, 1).toString());
                txtApellidos.setText(dtmEntrenadores.getValueAt(fila, 2).toString());
                txtTelefono.setText(dtmEntrenadores.getValueAt(fila, 3).toString());
                txtEmail.setText(dtmEntrenadores.getValueAt(fila, 4).toString());
                txtEspecialidad.setText(dtmEntrenadores.getValueAt(fila, 5).toString());
                txtSalario.setText(dtmEntrenadores.getValueAt(fila, 6).toString());
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

        JLabel lblEspecialidad = new JLabel("Especialidad:");
        lblEspecialidad.setBounds(430, startY + rowH, 120, 25);
        add(lblEspecialidad);
        txtEspecialidad = new JTextField();
        txtEspecialidad.setBounds(560, startY + rowH, fieldW, 25);
        add(txtEspecialidad);

        JLabel lblSalario = new JLabel("Salario:");
        lblSalario.setBounds(430, startY + rowH * 2, 120, 25);
        add(lblSalario);
        txtSalario = new JTextField();
        txtSalario.setBounds(510, startY + rowH * 2, 150, 25);
        add(txtSalario);


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
            Entrenador en = new Entrenador(0, txtNombre.getText(), txtApellidos.getText(),
                    txtTelefono.getText(), txtEmail.getText(), txtEspecialidad.getText(),
                    Double.parseDouble(txtSalario.getText()));
            if (EntrenadorDAO.insertarEntrenador(en)) {
                JOptionPane.showMessageDialog(this, "Entrenador registrado correctamente.");
                recargarTabla();
                limpiarFormulario();
            } else {
                JOptionPane.showMessageDialog(this, "Error al registrar el entrenador.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnModificar.addActionListener(e -> {
            if (idSeleccionado == -1) {
                JOptionPane.showMessageDialog(this, "Selecciona un entrenador de la tabla.");
                return;
            }
            if (!validarCampos()) return;
            Entrenador en = new Entrenador(idSeleccionado, txtNombre.getText(), txtApellidos.getText(),
                    txtTelefono.getText(), txtEmail.getText(), txtEspecialidad.getText(),
                    Double.parseDouble(txtSalario.getText()));
            if (EntrenadorDAO.modificarEntrenador(en)) {
                JOptionPane.showMessageDialog(this, "Entrenador modificado correctamente.");
                recargarTabla();
                limpiarFormulario();
            } else {
                JOptionPane.showMessageDialog(this, "Error al modificar el entrenador.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnEliminar.addActionListener(e -> {
            if (idSeleccionado == -1) {
                JOptionPane.showMessageDialog(this, "Selecciona un entrenador de la tabla.");
                return;
            }
            int confirm = JOptionPane.showConfirmDialog(this,
                    "¿Seguro que quieres eliminar el entrenador con ID " + idSeleccionado + "?",
                    "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                if (EntrenadorDAO.eliminarEntrenador(idSeleccionado)) {
                    JOptionPane.showMessageDialog(this, "Entrenador eliminado correctamente.");
                    recargarTabla();
                    limpiarFormulario();
                } else {
                    JOptionPane.showMessageDialog(this, "Error al eliminar el entrenador.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnLimpiar.addActionListener(e -> limpiarFormulario());

        cargarEntrenadores();
    }

    private void cargarEntrenadores() {

        dtmEntrenadores.setRowCount(0);
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

    private void recargarTabla() {
        idSeleccionado = -1;
        cargarEntrenadores();
    }

    private void limpiarFormulario() {
        idSeleccionado = -1;
        txtNombre.setText("");
        txtApellidos.setText("");
        txtTelefono.setText("");
        txtEmail.setText("");
        txtEspecialidad.setText("");
        txtSalario.setText("");
        tblEntrenadores.clearSelection();
        btnGuardar.setEnabled(true);
        btnModificar.setEnabled(false);
        btnEliminar.setEnabled(false);
    }

    private boolean validarCampos() {
        if (txtNombre.getText().trim().isEmpty() ||
                txtApellidos.getText().trim().isEmpty() ||
                txtTelefono.getText().trim().isEmpty() ||
                txtEmail.getText().trim().isEmpty() ||
                txtEspecialidad.getText().trim().isEmpty() ||
                txtSalario.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.");
            return false;
        }
        try {
            Double.parseDouble(txtSalario.getText().trim());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "El salario debe ser un número.");
            return false;
        }
        return true;
    }
}
