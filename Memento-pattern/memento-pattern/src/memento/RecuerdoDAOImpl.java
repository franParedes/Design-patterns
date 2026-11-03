package memento;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RecuerdoDAOImpl implements RecuerdoDAO {

    @Override
    public void insertar(Recuerdo r) throws SQLException {
        Partida p = r.getPartida();
        FechaHora f = p.getFechaHora();
        String sql = "INSERT INTO recuerdo (nombre, fase, fecha) VALUES (?,?,?)";
        try (Connection c = ConexionBD.getConexion();
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getFase());
            ps.setTimestamp(3, Timestamp.valueOf(LocalDateTime.of(
                    f.getAño(), f.getMes(), f.getDia(), f.getHora(), f.getMin())));
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) r.setId(rs.getInt(1));
            }
        }
    }

    @Override
    public List<Recuerdo> listarTodos() throws SQLException {
        List<Recuerdo> lista = new ArrayList<>();
        try (Connection c = ConexionBD.getConexion();
             Statement st = c.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM recuerdo ORDER BY id")) {
            while (rs.next()) {
                LocalDateTime t = rs.getTimestamp("fecha").toLocalDateTime();
                FechaHora fh = new FechaHora(t.getDayOfMonth(), t.getMonthValue(),
                        t.getYear(), t.getHour(), t.getMinute());
                Recuerdo r = new Recuerdo(new Partida(rs.getString("nombre"), rs.getString("fase"), fh));
                r.setId(rs.getInt("id"));
                lista.add(r);
            }
        }
        return lista;
    }

    @Override
    public void eliminar(int id) throws SQLException {
        try (Connection c = ConexionBD.getConexion();
             PreparedStatement ps = c.prepareStatement("DELETE FROM recuerdo WHERE id=?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}