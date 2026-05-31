package com.centrodeportivo.view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class PInicio extends JPanel {

    public PInicio() {

        setLayout(null);

        JLabel lblTitulo = new JLabel("APLICACIÓN DE GESTIÓN DE CENTRO DEPORTIVO");
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 22));
        lblTitulo.setBounds(80, 80, 700, 40);
        add(lblTitulo);

        JLabel lblTexto = new JLabel("Selecciona una opción del menú superior para comenzar.");
        lblTexto.setHorizontalAlignment(SwingConstants.CENTER);
        lblTexto.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblTexto.setBounds(150, 160, 550, 30);
        add(lblTexto);
    }
}