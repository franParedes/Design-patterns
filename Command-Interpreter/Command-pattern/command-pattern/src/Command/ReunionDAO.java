package Command;

import java.sql.SQLException;
import java.util.List;

public interface ReunionDAO {
    void insertar(Reunion r) throws SQLException;   // asigna el id generado a r
    void actualizar(Reunion r) throws SQLException;
    Reunion buscarPorId(int id) throws SQLException;
    List<Reunion> listarTodas() throws SQLException;
}