package db;

import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class ConexionDB {
    private static final String URL = "jdbc:mysql://localhost:3307/sistema_composite";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    public static void guardarEmpresa(String nombre, String tipo, int vehiculos, double coste) {
        String sql = "INSERT INTO registros_empresas (nombre, tipo, vehiculos, coste_mantenimiento) VALUES (?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            pstmt.setString(2, tipo);
            pstmt.setInt(3, vehiculos);
            pstmt.setDouble(4, coste);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error en BD: " + e.getMessage());
        }
    }

    public static DefaultTableModel obtenerEmpresas() {
        DefaultTableModel modelo = new DefaultTableModel(new String[]{"ID", "Nombre", "Tipo", "Vehículos", "Coste Mantenimiento"}, 0);
        String sql = "SELECT * FROM registros_empresas";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                modelo.addRow(new Object[]{rs.getInt("id"), rs.getString("nombre"),
                        rs.getString("tipo"), rs.getInt("vehiculos"), rs.getDouble("coste_mantenimiento")});
            }
        } catch (SQLException e) {
            System.err.println("Error al leer BD: " + e.getMessage());
        }
        return modelo;
    }
}