package com.centrodeportivo.model.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.centrodeportivo.model.data.Material;

public class MaterialDAO {

    public static Material[] obtenerMaterial() {

        Material[] materiales = new Material[50];
        int contador = 0;

        String sql = "SELECT id_material, nombre, tipo, cantidad, estado, id_sala FROM material";

        try {
            Connection conexion = ConexionDB.getConexion();
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {

                Material material = new Material(
                        rs.getInt("id_material"),
                        rs.getString("nombre"),
                        rs.getString("tipo"),
                        rs.getInt("cantidad"),
                        rs.getString("estado"),
                        rs.getInt("id_sala")
                );

                materiales[contador] = material;
                contador++;
            }

            rs.close();
            st.close();
            conexion.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return materiales;
    }

    public static boolean insertarMaterial(Material material) {

        String sql = "INSERT INTO material (nombre, tipo, cantidad, estado, id_sala) VALUES (?, ?, ?, ?, ?)";

        try {
            Connection conexion = ConexionDB.getConexion();
            PreparedStatement ps = conexion.prepareStatement(sql);

            ps.setString(1, material.getNombre());
            ps.setString(2, material.getTipo());
            ps.setInt(3, material.getCantidad());
            ps.setString(4, material.getEstado());
            ps.setInt(5, material.getIdSala());

            int filas = ps.executeUpdate();
            ps.close();
            conexion.close();

            return filas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean modificarMaterial(Material material) {

        String sql = "UPDATE material SET nombre=?, tipo=?, cantidad=?, estado=?, id_sala=? WHERE id_material=?";

        try {
            Connection conexion = ConexionDB.getConexion();
            PreparedStatement ps = conexion.prepareStatement(sql);

            ps.setString(1, material.getNombre());
            ps.setString(2, material.getTipo());
            ps.setInt(3, material.getCantidad());
            ps.setString(4, material.getEstado());
            ps.setInt(5, material.getIdSala());
            ps.setInt(6, material.getIdMaterial());

            int filas = ps.executeUpdate();
            ps.close();
            conexion.close();

            return filas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean eliminarMaterial(int idMaterial) {

        String sql = "DELETE FROM material WHERE id_material=?";

        try {
            Connection conexion = ConexionDB.getConexion();
            PreparedStatement ps = conexion.prepareStatement(sql);

            ps.setInt(1, idMaterial);

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
