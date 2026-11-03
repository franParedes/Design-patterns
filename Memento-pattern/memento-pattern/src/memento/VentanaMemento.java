package memento;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class VentanaMemento extends JFrame {
    private final RecuerdoDAO dao = new RecuerdoDAOImpl();
    private final Originador originador = new Originador();
    private final Conserje conserje = new Conserje(dao);

    private final JTextField txtNombre = new JTextField(18);
    private final JTextField txtFase = new JTextField(18);
    private final JLabel lblActual = new JLabel("Partida actual: ninguna");

    private final DefaultTableModel modelo = new DefaultTableModel(
            new String[]{"#", "ID", "Nombre", "Fase", "Fecha"}, 0) {
        @Override public boolean isCellEditable(int r, int c) { return false; }
    };
    private final JTable tabla = new JTable(modelo);

    private final JButton btnGuardar = new JButton("Guardar recuerdo");
    private final JButton btnRestaurar = new JButton("Restaurar seleccionado");
    private final JButton btnEliminar = new JButton("Eliminar seleccionado");

    public VentanaMemento() {
        super("Patrón Memento - Partidas");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(8, 8));

        // ---- Partida actual (Originador) ----
        JPanel norte = new JPanel(new BorderLayout(4, 4));
        JPanel form = new JPanel(new GridLayout(2, 2, 6, 6));
        form.setBorder(BorderFactory.createTitledBorder("Partida actual"));
        form.add(new JLabel("Nombre:")); form.add(txtNombre);
        form.add(new JLabel("Fase:"));   form.add(txtFase);
        norte.add(form, BorderLayout.CENTER);
        lblActual.setBorder(BorderFactory.createEmptyBorder(0, 8, 4, 8));
        norte.add(lblActual, BorderLayout.SOUTH);
        add(norte, BorderLayout.NORTH);

        // ---- Recuerdos guardados (Conserje) ----
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBorder(BorderFactory.createTitledBorder("Recuerdos guardados"));
        add(scroll, BorderLayout.CENTER);

        JPanel botones = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 8));
        botones.add(btnGuardar); botones.add(btnRestaurar); botones.add(btnEliminar);
        add(botones, BorderLayout.SOUTH);

        btnGuardar.addActionListener(e -> accion(this::guardarRecuerdo));
        btnRestaurar.addActionListener(e -> accion(this::restaurar));
        btnEliminar.addActionListener(e -> accion(this::eliminar));

        accion(() -> {});   // carga inicial
        pack();
        setMinimumSize(new Dimension(640, 420));
        setLocationRelativeTo(null);
    }

    // ---- Acciones ----

    private void guardarRecuerdo() throws Exception {
        String nombre = txtNombre.getText().trim();
        String fase = txtFase.getText().trim();
        if (nombre.isEmpty() || fase.isEmpty()) {
            throw new IllegalArgumentException("Escribe el nombre y la fase de la partida.");
        }
        originador.setPartida(new Partida(nombre, fase));
        conserje.setRecuerdo(originador.crearRecuerdo());
    }

    private void restaurar() throws Exception {
        Recuerdo r = recuerdoSeleccionado();
        originador.setRecuerdo(r);
        Partida p = originador.getPartida();
        txtNombre.setText(p.getNombre());
        txtFase.setText(p.getFase());
    }

    private void eliminar() throws Exception {
        Recuerdo r = recuerdoSeleccionado();
        conserje.eliminarRecuerdo(r.getId());
    }

    private Recuerdo recuerdoSeleccionado() throws Exception {
        int fila = tabla.getSelectedRow();
        if (fila < 0) throw new IllegalArgumentException("Selecciona un recuerdo de la tabla.");
        Recuerdo r = conserje.getRecuerdo(fila);
        if (r == null) throw new IllegalArgumentException("El recuerdo ya no existe.");
        return r;
    }

    // ---- Utilidades ----

    @FunctionalInterface
    private interface Accion { void run() throws Exception; }

    private void accion(Accion a) {
        try {
            a.run();
            refrescarTabla();
            actualizarEtiqueta();
        } catch (Exception ex) {
            String msg = ex.getMessage() != null ? ex.getMessage() : ex.toString();
            JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void refrescarTabla() throws Exception {
        modelo.setRowCount(0);
        List<Recuerdo> lista = conserje.getRecuerdos();
        int i = 1;
        for (Recuerdo r : lista) {
            Partida p = r.getPartida();
            modelo.addRow(new Object[]{i++, r.getId(), p.getNombre(), p.getFase(), p.getFecha()});
        }
    }

    private void actualizarEtiqueta() {
        Partida p = originador.getPartida();
        lblActual.setText(p == null
                ? "Partida actual: ninguna"
                : "Partida actual: " + p.getNombre() + " # " + p.getFase() + " # " + p.getFecha());
    }
}