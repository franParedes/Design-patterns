package db;

import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class ConexionDB {
    private static final String URL = "jdbc:mysql://localhost:3306/sistema_singleton";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    public static void guardarComercial(String nombre, String direccion, String email) {
        String sql = "INSERT INTO registros_comercial (nombre, direccion, email) VALUES (?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            pstmt.setString(2, direccion);
            pstmt.setString(3, email);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error en BD: " + e.getMessage());
        }
    }

    public static DefaultTableModel obtenerComerciales() {
        DefaultTableModel modelo = new DefaultTableModel(new String[]{"ID", "Nombre", "Dirección", "Email"}, 0);
        String sql = "SELECT * FROM registros_comercial";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                modelo.addRow(new Object[]{rs.getInt("id"), rs.getString("nombre"),
                        rs.getString("direccion"), rs.getString("email")});
            }
        } catch (SQLException e) {
            System.err.println("Error al leer BD: " + e.getMessage());
        }
        return modelo;
    }
}