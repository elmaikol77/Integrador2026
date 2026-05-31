package com.centrodeportivo.model.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.centrodeportivo.model.data.Sala;

public class SalaDAO {

    public static Sala[] obtenerSalas() {

        Sala[] salas = new Sala[50];
        int contador = 0;

        String sql = "SELECT id_sala, nombre, capacidad, tipo, planta FROM salas";

        try {
            Connection conexion = ConexionDB.getConexion();
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {

                Sala sala = new Sala(
                        rs.getInt("id_sala"),
                        rs.getString("nombre"),
                        rs.getInt("capacidad"),
                        rs.getString("tipo"),
                        rs.getInt("planta")
                );

                salas[contador] = sala;
                contador++;
            }

            rs.close();
            st.close();
            conexion.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return salas;
    }
}