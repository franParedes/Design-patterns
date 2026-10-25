package Command;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class VentanaReuniones extends JFrame {
    private final ReunionDAO dao = new ReunionDAOImpl();
    private final Invocador inv = new Invocador(dao);
    private final Map<Integer, Reunion> cache = new HashMap<>(); // misma instancia por id
    private Reunion actual;
    private boolean cargando = false;

    private final JTextField txtMotivo = new JTextField(18);
    private final JTextField txtParticipantes = new JTextField(18);
    private final JTextField txtLocalizacion = new JTextField(18);
    private final JTextField txtFecha = new JTextField("1/10/2026/10:30", 18);

    private final DefaultTableModel modelo = new DefaultTableModel(
            new String[]{"ID", "Motivo", "Participantes", "Localización", "Fecha"}, 0) {
        @Override public boolean isCellEditable(int r, int c) { return false; }
    };
    private final JTable tabla = new JTable(modelo);

    private final JButton btnGuardar = new JButton("Guardar nueva");
    private final JButton btnLugar = new JButton("Cambiar localización");
    private final JButton btnFechaCmd = new JButton("Cambiar fecha");
    private final JButton btnDeshacer = new JButton("Deshacer");
    private final JButton btnRehacer = new JButton("Rehacer");

    public VentanaReuniones() {
        super("Patrón Command - Reuniones");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(8, 8));

        JPanel form = new JPanel(new GridLayout(4, 2, 6, 6));
        form.setBorder(BorderFactory.createTitledBorder("Datos (fecha: d/m/aaaa/h:mm)"));
        form.add(new JLabel("Motivo:"));         form.add(txtMotivo);
        form.add(new JLabel("Participantes:"));  form.add(txtParticipantes);
        form.add(new JLabel("Localización:"));   form.add(txtLocalizacion);
        form.add(new JLabel("Fecha:"));          form.add(txtFecha);
        add(form, BorderLayout.NORTH);

        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        add(new JScrollPane(tabla), BorderLayout.CENTER);

        JPanel botones = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 8));
        botones.add(btnGuardar); botones.add(btnLugar); botones.add(btnFechaCmd);
        botones.add(btnDeshacer); botones.add(btnRehacer);
        add(botones, BorderLayout.SOUTH);

        btnGuardar.addActionListener(e -> accion(this::guardarNueva));
        btnLugar.addActionListener(e -> accion(this::cambiarLocalizacion));
        btnFechaCmd.addActionListener(e -> accion(this::cambiarFecha));
        btnDeshacer.addActionListener(e -> accion(inv::deshacerComando));
        btnRehacer.addActionListener(e -> accion(inv::rehacerComando));

        tabla.getSelectionModel().addListSelectionListener(e -> {
            if (e.getValueIsAdjusting() || cargando) return;
            int fila = tabla.getSelectedRow();
            if (fila >= 0) {
                actual = cache.get((Integer) modelo.getValueAt(fila, 0));
                llenarCampos();
            }
        });

        accion(() -> {});   // carga inicial de la tabla
        pack();
        setMinimumSize(new Dimension(700, 450));
        setLocationRelativeTo(null);
    }

    // ---- Acciones ----

    private void guardarNueva() throws Exception {
        Reunion r = new Reunion(txtMotivo.getText().trim(), txtParticipantes.getText().trim(),
                txtLocalizacion.getText().trim(), new FechaHora(txtFecha.getText().trim()));
        dao.insertar(r);
        cache.put(r.getId(), r);
        actual = r;
    }

    private void cambiarLocalizacion() throws Exception {
        verificarSeleccion();
        ComandoDeshacer c = new ComandoCambiarLocalizacion();
        c.setReunion(actual);
        inv.setComando(c);
        inv.ejecutaComando(txtLocalizacion.getText().trim());
    }

    private void cambiarFecha() throws Exception {
        verificarSeleccion();
        ComandoDeshacer c = new ComandoCambiarFecha();
        c.setReunion(actual);
        inv.setComando(c);
        inv.ejecutaComando(txtFecha.getText().trim());
    }

    private void verificarSeleccion() {
        if (actual == null) throw new IllegalStateException("Selecciona o guarda una reunión primero.");
    }

    // ---- Utilidades ----

    @FunctionalInterface
    private interface Accion { void run() throws Exception; }

    private void accion(Accion a) {
        try {
            a.run();
            refrescarTabla();
            llenarCampos();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.toString(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void refrescarTabla() throws Exception {
        cargando = true;
        try {
            modelo.setRowCount(0);
            int sel = -1, i = 0;
            for (Reunion db : dao.listarTodas()) {
                Reunion r = cache.computeIfAbsent(db.getId(), k -> db);
                modelo.addRow(new Object[]{r.getId(), r.getMotivo(), r.getParticipantes(),
                        r.getLocalizacion(), r.getFecha()});
                if (actual != null && r.getId() == actual.getId()) sel = i;
                i++;
            }
            if (sel >= 0) tabla.setRowSelectionInterval(sel, sel);
        } finally {
            cargando = false;
        }
        btnDeshacer.setEnabled(inv.hayDeshacer());
        btnRehacer.setEnabled(inv.hayRehacer());
    }

    private void llenarCampos() {
        if (actual == null) return;
        txtMotivo.setText(actual.getMotivo());
        txtParticipantes.setText(actual.getParticipantes());
        txtLocalizacion.setText(actual.getLocalizacion());
        txtFecha.setText(actual.getFecha().toString());
    }
}