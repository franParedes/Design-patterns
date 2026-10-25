package Command;

import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Deque;

public class Invocador {
    private ComandoDeshacer comando;
    private final Deque<ComandoDeshacer> pilaDeshacer = new ArrayDeque<>();
    private final Deque<ComandoDeshacer> pilaRehacer = new ArrayDeque<>();
    private final ReunionDAO dao;

    public Invocador(ReunionDAO dao) {
        this.dao = dao;
    }

    public void setComando(ComandoDeshacer comando) {
        this.comando = comando;
    }

    public void ejecutaComando(String param) throws SQLException {
        comando.ejecutar(param);
        pilaDeshacer.push(comando);
        pilaRehacer.clear();               // una nueva acción invalida el rehacer
        dao.actualizar(comando.getReunion());
    }

    public void deshacerComando() throws SQLException {
        if (pilaDeshacer.isEmpty()) return;
        ComandoDeshacer c = pilaDeshacer.pop();
        c.deshacer();
        pilaRehacer.push(c);
        dao.actualizar(c.getReunion());
    }

    public void rehacerComando() throws SQLException {
        if (pilaRehacer.isEmpty()) return;
        ComandoDeshacer c = pilaRehacer.pop();
        c.rehacer();
        pilaDeshacer.push(c);
        dao.actualizar(c.getReunion());
    }

    public boolean hayDeshacer() { return !pilaDeshacer.isEmpty(); }
    public boolean hayRehacer() { return !pilaRehacer.isEmpty(); }
}