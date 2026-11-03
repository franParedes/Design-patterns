package memento;

import java.sql.SQLException;
import java.util.List;

// Guarda los recuerdos en la base de datos. No conoce el contenido de la partida.
public class Conserje {
    private final RecuerdoDAO dao;

    public Conserje(RecuerdoDAO dao) {
        this.dao = dao;
    }

    public void setRecuerdo(Recuerdo recuerdo) throws SQLException {
        dao.insertar(recuerdo);
    }

    public Recuerdo getRecuerdo(int indice) throws SQLException {
        List<Recuerdo> recuerdos = dao.listarTodos();
        if (indice >= 0 && indice < recuerdos.size()) {
            return recuerdos.get(indice);
        }
        return null;
    }

    public List<Recuerdo> getRecuerdos() throws SQLException {
        return dao.listarTodos();
    }

    public void eliminarRecuerdo(int id) throws SQLException {
        dao.eliminar(id);
    }
}