package com.centrodeportivo.model.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
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

    public static boolean insertarEntrenador(Entrenador entrenador) {

        String sql = "INSERT INTO entrenadores (nombre, apellidos, telefono, email, especialidad, salario) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            Connection conexion = ConexionDB.getConexion();
            PreparedStatement ps = conexion.prepareStatement(sql);

            ps.setString(1, entrenador.getNombre());
            ps.setString(2, entrenador.getApellidos());
            ps.setString(3, entrenador.getTelefono());
            ps.setString(4, entrenador.getEmail());
            ps.setString(5, entrenador.getEspecialidad());
            ps.setDouble(6, entrenador.getSalario());

            int filas = ps.executeUpdate();
            ps.close();
            conexion.close();

            return filas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean modificarEntrenador(Entrenador entrenador) {

        String sql = "UPDATE entrenadores SET nombre=?, apellidos=?, telefono=?, email=?, especialidad=?, salario=? WHERE id_entrenador=?";

        try {
            Connection conexion = ConexionDB.getConexion();
            PreparedStatement ps = conexion.prepareStatement(sql);

            ps.setString(1, entrenador.getNombre());
            ps.setString(2, entrenador.getApellidos());
            ps.setString(3, entrenador.getTelefono());
            ps.setString(4, entrenador.getEmail());
            ps.setString(5, entrenador.getEspecialidad());
            ps.setDouble(6, entrenador.getSalario());
            ps.setInt(7, entrenador.getIdEntrenador());

            int filas = ps.executeUpdate();
            ps.close();
            conexion.close();

            return filas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean eliminarEntrenador(int idEntrenador) {

        String sql = "DELETE FROM entrenadores WHERE id_entrenador=?";

        try {
            Connection conexion = ConexionDB.getConexion();
            PreparedStatement ps = conexion.prepareStatement(sql);

            ps.setInt(1, idEntrenador);

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
