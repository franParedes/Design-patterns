package Command;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReunionDAOImpl implements ReunionDAO {

    private Timestamp aTimestamp(FechaHora f) {
        return Timestamp.valueOf(LocalDateTime.of(
                f.getAnyo(), f.getMes(), f.getDia(), f.getHora(), f.getMin()));
    }

    @Override
    public void insertar(Reunion r) throws SQLException {
        String sql = "INSERT INTO reunion (motivo, participantes, localizacion, fecha) VALUES (?,?,?,?)";
        try (Connection c = ConexionBD.getConexion();
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, r.getMotivo());
            ps.setString(2, r.getParticipantes());
            ps.setString(3, r.getLocalizacion());
            ps.setTimestamp(4, aTimestamp(r.getFecha()));
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) r.setId(rs.getInt(1));
            }
        }
    }

    @Override
    public void actualizar(Reunion r) throws SQLException {
        String sql = "UPDATE reunion SET motivo=?, participantes=?, localizacion=?, fecha=? WHERE id=?";
        try (Connection c = ConexionBD.getConexion();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, r.getMotivo());
            ps.setString(2, r.getParticipantes());
            ps.setString(3, r.getLocalizacion());
            ps.setTimestamp(4, aTimestamp(r.getFecha()));
            ps.setInt(5, r.getId());
            ps.executeUpdate();
        }
    }

    private Reunion mapear(ResultSet rs) throws SQLException {
        LocalDateTime t = rs.getTimestamp("fecha").toLocalDateTime();
        FechaHora fh = new FechaHora(t.getDayOfMonth(), t.getMonthValue(),
                t.getYear(), t.getHour(), t.getMinute());
        Reunion r = new Reunion(rs.getString("motivo"), rs.getString("participantes"),
                rs.getString("localizacion"), fh);
        r.setId(rs.getInt("id"));
        return r;
    }

    @Override
    public Reunion buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM reunion WHERE id=?";
        try (Connection c = ConexionBD.getConexion();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? mapear(rs) : null;
            }
        }
    }

    @Override
    public List<Reunion> listarTodas() throws SQLException {
        List<Reunion> lista = new ArrayList<>();
        try (Connection c = ConexionBD.getConexion();
             Statement st = c.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM reunion ORDER BY id")) {
            while (rs.next()) lista.add(mapear(rs));
        }
        return lista;
    }
}