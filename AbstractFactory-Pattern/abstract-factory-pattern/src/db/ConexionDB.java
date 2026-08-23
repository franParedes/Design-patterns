package db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;

public class ConexionDB {
    // Ajusta tu usuario y contraseña aquí
    private static final String URL = "jdbc:mysql://localhost:3306/fabrica_vehiculos";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    public static void guardarVehiculo(String tipo, String motor, String modelo, String color, int potencia, double espacio) {
        String sql = "INSERT INTO vehiculos (tipo, motor, modelo, color, potencia, espacio) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, tipo);
            pstmt.setString(2, motor);
            pstmt.setString(3, modelo);
            pstmt.setString(4, color);
            pstmt.setInt(5, potencia);
            pstmt.setDouble(6, espacio);

            pstmt.executeUpdate();
            System.out.println("Vehículo guardado en la base de datos exitosamente.");

        } catch (SQLException e) {
            System.err.println("Error al guardar en BD: " + e.getMessage());
        }
    }

    public static DefaultTableModel obtenerVehiculos() {
        DefaultTableModel modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("ID");
        modeloTabla.addColumn("Tipo");
        modeloTabla.addColumn("Motor");
        modeloTabla.addColumn("Modelo");
        modeloTabla.addColumn("Color");
        modeloTabla.addColumn("Potencia");
        modeloTabla.addColumn("Espacio");

        String sql = "SELECT * FROM vehiculos";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Object[] fila = new Object[7];
                fila[0] = rs.getInt("id");
                fila[1] = rs.getString("tipo");
                fila[2] = rs.getString("motor");
                fila[3] = rs.getString("modelo");
                fila[4] = rs.getString("color");
                fila[5] = rs.getInt("potencia");
                fila[6] = rs.getDouble("espacio");
                modeloTabla.addRow(fila);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener datos de BD: " + e.getMessage());
        }

        return modeloTabla;
    }
}
