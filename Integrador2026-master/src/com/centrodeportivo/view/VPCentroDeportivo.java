package com.centrodeportivo.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;

import com.centrodeportivo.control.CentroDeportivoListener;

public class VPCentroDeportivo extends JFrame {

    public static final int ANCHO = 900;
    public static final int ALTO = 600;

    private JScrollPane scrpContenedor;

    private JMenuItem mntmInicio;

    // Consulta
    private JMenuItem mntmClientes;
    private JMenuItem mntmEntrenadores;
    private JMenuItem mntmSalas;
    private JMenuItem mntmMaterial;

    // Gestión
    private JMenuItem mntmGestionClientes;
    private JMenuItem mntmGestionEntrenadores;
    private JMenuItem mntmGestionSalas;
    private JMenuItem mntmGestionMaterial;

    private JMenuItem mntmSalir;

    public VPCentroDeportivo() {

        super("*** GESTIÓN CENTRO DEPORTIVO ***");

        crearMenu();
        configurarVentana();

        cargarPanel(new PInicio());
    }

    private void crearMenu() {

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu mnArchivo = new JMenu("Archivo");
        menuBar.add(mnArchivo);

        mntmInicio = new JMenuItem("Inicio");
        mnArchivo.add(mntmInicio);

        mntmSalir = new JMenuItem("Salir");
        mnArchivo.add(mntmSalir);

        JMenu mnConsulta = new JMenu("Consulta");
        menuBar.add(mnConsulta);

        mntmClientes = new JMenuItem("Clientes");
        mnConsulta.add(mntmClientes);

        mntmEntrenadores = new JMenuItem("Entrenadores");
        mnConsulta.add(mntmEntrenadores);

        mntmSalas = new JMenuItem("Salas");
        mnConsulta.add(mntmSalas);

        mntmMaterial = new JMenuItem("Material");
        mnConsulta.add(mntmMaterial);

        JMenu mnGestion = new JMenu("Gestión");
        menuBar.add(mnGestion);

        mntmGestionClientes = new JMenuItem("Clientes");
        mnGestion.add(mntmGestionClientes);

        mntmGestionEntrenadores = new JMenuItem("Entrenadores");
        mnGestion.add(mntmGestionEntrenadores);

        mntmGestionSalas = new JMenuItem("Salas");
        mnGestion.add(mntmGestionSalas);

        mntmGestionMaterial = new JMenuItem("Material");
        mnGestion.add(mntmGestionMaterial);
    }

    private void configurarVentana() {

        setSize(ANCHO, ALTO);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        scrpContenedor = new JScrollPane();
        getContentPane().add(scrpContenedor, BorderLayout.CENTER);

        centrarVentana();
    }

    private void centrarVentana() {

        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();

        setLocation(
                (pantalla.width - ANCHO) / 2,
                (pantalla.height - ALTO) / 2);
    }

    public void cargarPanel(JPanel panel) {
        scrpContenedor.setViewportView(panel);
    }

    public void hacerVisible() {
        setVisible(true);
    }

    public void setListener(CentroDeportivoListener listener) {

        mntmInicio.addActionListener(listener);

        mntmClientes.addActionListener(listener);
        mntmEntrenadores.addActionListener(listener);
        mntmSalas.addActionListener(listener);
        mntmMaterial.addActionListener(listener);

        mntmGestionClientes.addActionListener(listener);
        mntmGestionEntrenadores.addActionListener(listener);
        mntmGestionSalas.addActionListener(listener);
        mntmGestionMaterial.addActionListener(listener);

        mntmSalir.addActionListener(listener);
    }

    // Getters consulta (retrocompatibilidad)
    public JMenuItem getMntmInicio() { return mntmInicio; }
    public JMenuItem getMntmClientes() { return mntmClientes; }
    public JMenuItem getMntmEntrenadores() { return mntmEntrenadores; }
    public JMenuItem getMntmSalas() { return mntmSalas; }
    public JMenuItem getMntmMaterial() { return mntmMaterial; }
    public JMenuItem getMntmSalir() { return mntmSalir; }

    // Getters gestión
    public JMenuItem getMntmGestionClientes() { return mntmGestionClientes; }
    public JMenuItem getMntmGestionEntrenadores() { return mntmGestionEntrenadores; }
    public JMenuItem getMntmGestionSalas() { return mntmGestionSalas; }
    public JMenuItem getMntmGestionMaterial() { return mntmGestionMaterial; }
}
