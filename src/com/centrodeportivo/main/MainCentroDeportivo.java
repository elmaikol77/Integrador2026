package com.centrodeportivo.main;

import java.awt.EventQueue;

import com.centrodeportivo.control.CentroDeportivoListener;
import com.centrodeportivo.view.VPCentroDeportivo;

public class MainCentroDeportivo {

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {

                VPCentroDeportivo ventana = new VPCentroDeportivo();
                CentroDeportivoListener listener = new CentroDeportivoListener(ventana);

                ventana.setListener(listener);
                ventana.hacerVisible();
            }
        });
    }
}