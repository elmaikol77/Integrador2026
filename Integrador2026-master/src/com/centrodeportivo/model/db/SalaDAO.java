package com.centrodeportivo.model.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
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

    public static boolean insertarSala(Sala sala) {

        String sql = "INSERT INTO salas (nombre, capacidad, tipo, planta) VALUES (?, ?, ?, ?)";

        try {
            Connection conexion = ConexionDB.getConexion();
            PreparedStatement ps = conexion.prepareStatement(sql);

            ps.setString(1, sala.getNombre());
            ps.setInt(2, sala.getCapacidad());
            ps.setString(3, sala.getTipo());
            ps.setInt(4, sala.getPlanta());

            int filas = ps.executeUpdate();
            ps.close();
            conexion.close();

            return filas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean modificarSala(Sala sala) {

        String sql = "UPDATE salas SET nombre=?, capacidad=?, tipo=?, planta=? WHERE id_sala=?";

        try {
            Connection conexion = ConexionDB.getConexion();
            PreparedStatement ps = conexion.prepareStatement(sql);

            ps.setString(1, sala.getNombre());
            ps.setInt(2, sala.getCapacidad());
            ps.setString(3, sala.getTipo());
            ps.setInt(4, sala.getPlanta());
            ps.setInt(5, sala.getIdSala());

            int filas = ps.executeUpdate();
            ps.close();
            conexion.close();

            return filas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean eliminarSala(int idSala) {

        String sql = "DELETE FROM salas WHERE id_sala=?";

        try {
            Connection conexion = ConexionDB.getConexion();
            PreparedStatement ps = conexion.prepareStatement(sql);

            ps.setInt(1, idSala);

            int filas = ps.executeUpdate();
            ps.close();
            conexion.close();

            return filas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
