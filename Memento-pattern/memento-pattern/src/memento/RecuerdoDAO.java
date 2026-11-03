package memento;

import java.sql.SQLException;
import java.util.List;

public interface RecuerdoDAO {
    void insertar(Recuerdo r) throws SQLException;   // asigna el id generado a r
    List<Recuerdo> listarTodos() throws SQLException;
    void eliminar(int id) throws SQLException;
}