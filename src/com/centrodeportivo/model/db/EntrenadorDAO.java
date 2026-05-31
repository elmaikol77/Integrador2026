package com.centrodeportivo.model.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.centrodeportivo.model.data.Entrenador;

public class EntrenadorDAO {

    public static Entrenador[] obtenerEntrenadores() {

        Entrenador[] entrenadores = new Entrenador[50];
        int contador = 0;

        String sql = "SELECT id_entrenador, nombre, apellidos, telefono, email, especialidad, salario FROM entrenadores";

        try {
            Connection conexion = ConexionDB.getConexion();
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {

                Entrenador entrenador = new Entrenador(
                        rs.getInt("id_entrenador"),
                        rs.getString("nombre"),
                        rs.getString("apellidos"),
                        rs.getString("telefono"),
                        rs.getString("email"),
                        rs.getString("especialidad"),
                        rs.getDouble("salario")
                );

                entrenadores[contador] = entrenador;
                contador++;
            }

            rs.close();
            st.close();
            conexion.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return entrenadores;
    }
}