package com.centrodeportivo.model.db;

import java.sql.Connection;
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
}