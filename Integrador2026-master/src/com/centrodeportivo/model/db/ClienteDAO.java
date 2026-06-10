package com.centrodeportivo.model.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
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

    public static boolean insertarCliente(Cliente cliente) {

        String sql = "INSERT INTO clientes (nombre, apellidos, telefono, email, cuota_mensual) VALUES (?, ?, ?, ?, ?)";

        try {
            Connection conexion = ConexionDB.getConexion();
            PreparedStatement ps = conexion.prepareStatement(sql);

            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellidos());
            ps.setString(3, cliente.getTelefono());
            ps.setString(4, cliente.getEmail());
            ps.setDouble(5, cliente.getCuotaMensual());

            int filas = ps.executeUpdate();
            ps.close();
            conexion.close();

            return filas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean modificarCliente(Cliente cliente) {

        String sql = "UPDATE clientes SET nombre=?, apellidos=?, telefono=?, email=?, cuota_mensual=? WHERE id_cliente=?";

        try {
            Connection conexion = ConexionDB.getConexion();
            PreparedStatement ps = conexion.prepareStatement(sql);

            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellidos());
            ps.setString(3, cliente.getTelefono());
            ps.setString(4, cliente.getEmail());
            ps.setDouble(5, cliente.getCuotaMensual());
            ps.setInt(6, cliente.getIdCliente());

            int filas = ps.executeUpdate();
            ps.close();
            conexion.close();

            return filas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean eliminarCliente(int idCliente) {

        String sql = "DELETE FROM clientes WHERE id_cliente=?";

        try {
            Connection conexion = ConexionDB.getConexion();
            PreparedStatement ps = conexion.prepareStatement(sql);

            ps.setInt(1, idCliente);

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
