package db;

import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConexionDB {
    private static final String URL = "jdbc:mysql://localhost:3306/sistema_pedidos";
    private static final String USER = "root";
    private static final String PASSWORD = "1234"; // Cambia esto

    public static void guardarPedido(String tipoCliente, double importe, String estadoValidacion) {
        String sql = "INSERT INTO registros_pedidos (tipo_cliente, importe, estado_validacion) VALUES (?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, tipoCliente);
            pstmt.setDouble(2, importe);
            pstmt.setString(3, estadoValidacion);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error en BD: " + e.getMessage());
        }
    }

    public static DefaultTableModel obtenerPedidos() {
        DefaultTableModel modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("ID");
        modeloTabla.addColumn("Tipo Cliente");
        modeloTabla.addColumn("Importe");
        modeloTabla.addColumn("Estado");

        String sql = "SELECT * FROM registros_pedidos";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                modeloTabla.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getString("tipo_cliente"),
                        rs.getDouble("importe"),
                        rs.getString("estado_validacion")
                });
            }
        } catch (SQLException e) {
            System.err.println("Error al leer BD: " + e.getMessage());
        }
        return modeloTabla;
    }
}