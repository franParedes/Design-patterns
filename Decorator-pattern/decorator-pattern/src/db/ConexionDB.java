package db;

import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class ConexionDB {
    private static final String URL = "jdbc:mysql://localhost:3307/sistema_decorator";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    public static void guardarVehiculo(String nombre, String resultado) {
        String sql = "INSERT INTO vehiculos_decorados (nombre_base, resultado_visualizacion) VALUES (?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            pstmt.setString(2, resultado);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error en BD: " + e.getMessage());
        }
    }

    public static DefaultTableModel obtenerVehiculos() {
        DefaultTableModel modelo = new DefaultTableModel(new String[]{"ID", "Vehículo Base", "Resultado Visualización"}, 0);
        String sql = "SELECT * FROM vehiculos_decorados";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                modelo.addRow(new Object[]{rs.getInt("id"), rs.getString("nombre_base"), rs.getString("resultado_visualizacion")});
            }
        } catch (SQLException e) {
            System.err.println("Error al leer BD: " + e.getMessage());
        }
        return modelo;
    }
}