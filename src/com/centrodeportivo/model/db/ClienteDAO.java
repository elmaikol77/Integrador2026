package com.centrodeportivo.model.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.centrodeportivo.model.data.Cliente;

public class ClienteDAO {

    public static Cliente[] obtenerClientes() {

        Cliente[] clientes = new Cliente[50];
        int contador = 0;

        String sql = "SELECT id_cliente, nombre, apellidos, telefono, email, cuota_mensual FROM clientes";

        try {
            Connection conexion = ConexionDB.getConexion();
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {

                Cliente cliente = new Cliente(
                        rs.getInt("id_cliente"),
                        rs.getString("nombre"),
                        rs.getString("apellidos"),
                        rs.getString("telefono"),
                        rs.getString("email"),
                        rs.getDouble("cuota_mensual")
                );

                clientes[contador] = cliente;
                contador++;
            }

            rs.close();
            st.close();
            conexion.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clientes;
    }
}