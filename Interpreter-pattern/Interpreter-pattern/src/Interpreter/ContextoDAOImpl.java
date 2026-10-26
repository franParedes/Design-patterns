package Interpreter;

import java.sql.*;

public class ContextoDAOImpl implements ContextoDAO {

    @Override
    public void insertarEmpresa(String nombre) throws SQLException {
        insertarNombre("INSERT INTO empresa (nombre) VALUES (?)", nombre);
    }

    @Override
    public void insertarEmpleado(String nombre) throws SQLException {
        insertarNombre("INSERT INTO empleado (nombre) VALUES (?)", nombre);
    }

    private void insertarNombre(String sql, String nombre) throws SQLException {
        try (Connection c = ConexionBD.getConexion();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, nombre);
            ps.executeUpdate();
        }
    }

    @Override
    public void insertarRelacion(String empresa, String empleado) throws SQLException {
        String sql = "INSERT INTO empresa_empleado (empresa_id, empleado_id) "
                + "SELECT e.id, t.id FROM empresa e, empleado t WHERE e.nombre=? AND t.nombre=?";
        try (Connection c = ConexionBD.getConexion();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, empresa);
            ps.setString(2, empleado);
            if (ps.executeUpdate() == 0) {
                throw new SQLException("La empresa o el empleado no existen.");
            }
        }
    }

    @Override
    public Contexto cargarContexto() throws SQLException {
        Contexto contexto = new Contexto();
        try (Connection c = ConexionBD.getConexion();
             Statement st = c.createStatement()) {

            try (ResultSet rs = st.executeQuery("SELECT nombre FROM empresa ORDER BY id")) {
                while (rs.next()) contexto.anadirEmpresa(rs.getString("nombre"));
            }
            try (ResultSet rs = st.executeQuery("SELECT nombre FROM empleado ORDER BY id")) {
                while (rs.next()) contexto.anadirEmpleado(rs.getString("nombre"));
            }
            String sql = "SELECT e.nombre AS empresa, t.nombre AS empleado "
                    + "FROM empresa_empleado ee "
                    + "JOIN empresa e ON e.id = ee.empresa_id "
                    + "JOIN empleado t ON t.id = ee.empleado_id "
                    + "ORDER BY e.id, t.id";
            try (ResultSet rs = st.executeQuery(sql)) {
                while (rs.next()) {
                    contexto.anadirEmpresayEmpleado(
                            new EmpresayEmpleado(rs.getString("empresa"), rs.getString("empleado")));
                }
            }
        }
        return contexto;
    }
}