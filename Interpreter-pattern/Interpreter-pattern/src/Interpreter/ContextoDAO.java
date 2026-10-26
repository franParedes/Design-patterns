package Interpreter;

import java.sql.SQLException;

public interface ContextoDAO {
    void insertarEmpresa(String nombre) throws SQLException;
    void insertarEmpleado(String nombre) throws SQLException;
    void insertarRelacion(String empresa, String empleado) throws SQLException;
    Contexto cargarContexto() throws SQLException;
}