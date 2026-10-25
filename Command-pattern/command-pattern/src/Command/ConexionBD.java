package Command;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    private static final String URL = "jdbc:mysql://localhost:3307/patrones?serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = "1234";

    private ConexionBD() {}

    public static Connection getConexion() throws SQLException {
        try {
            // Carga explícita: si el JAR no está en el classpath, el error será claro
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("No se encontró el driver MySQL en el classpath del módulo.", e);
        }
        return DriverManager.getConnection(URL, USER, PASS);
    }
}