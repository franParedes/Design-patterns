package db;

import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class ConexionDB {
    private static final String URL = "jdbc:mysql://localhost:3306/sistema_concesionario";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    public static void guardarDocumentacion(String cliente, String formato, String contenido) {
        String sql = "INSERT INTO registros_documentacion (nombre_cliente, formato, contenido_generado) VALUES (?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, cliente);
            pstmt.setString(2, formato);
            pstmt.setString(3, contenido);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error en BD: " + e.getMessage());
        }
    }

    public static DefaultTableModel obtenerRegistros() {
        DefaultTableModel modelo = new DefaultTableModel(new String[]{"ID", "Cliente", "Formato", "Contenido"}, 0);
        String sql = "SELECT * FROM registros_documentacion";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                modelo.addRow(new Object[]{rs.getInt("id"), rs.getString("nombre_cliente"),
                        rs.getString("formato"), rs.getString("contenido_generado")});
            }
        } catch (SQLException e) {
            System.err.println("Error al leer BD: " + e.getMessage());
        }
        return modelo;
    }
}