package Interpreter;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class VentanaInterprete extends JFrame {
    private final ContextoDAO dao = new ContextoDAOImpl();
    private Contexto contexto = new Contexto();

    private final JTextField txtEmpresa = new JTextField(14);
    private final JTextField txtEmpleado = new JTextField(14);
    private final JComboBox<String> cmbEmpresa = new JComboBox<>();
    private final JComboBox<String> cmbEmpleado = new JComboBox<>();
    private final JTextField txtExpresion = new JTextField("muestra empleados por empresas", 28);
    private final JTextArea txtSalida = new JTextArea(12, 40);

    private final JButton btnAgregarEmpresa = new JButton("Agregar empresa");
    private final JButton btnAgregarEmpleado = new JButton("Agregar empleado");
    private final JButton btnRelacionar = new JButton("Relacionar");
    private final JButton btnInterpretar = new JButton("Interpretar");

    public VentanaInterprete() {
        super("Patrón Interpreter - Empresas y Empleados");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(8, 8));

        // ---- Datos ----
        JPanel datos = new JPanel(new GridLayout(3, 1, 4, 4));
        datos.setBorder(BorderFactory.createTitledBorder("Datos"));

        JPanel f1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        f1.add(new JLabel("Empresa:")); f1.add(txtEmpresa); f1.add(btnAgregarEmpresa);
        JPanel f2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        f2.add(new JLabel("Empleado:")); f2.add(txtEmpleado); f2.add(btnAgregarEmpleado);
        JPanel f3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        f3.add(new JLabel("Relacionar:")); f3.add(cmbEmpresa); f3.add(cmbEmpleado); f3.add(btnRelacionar);
        datos.add(f1); datos.add(f2); datos.add(f3);
        add(datos, BorderLayout.NORTH);

        // ---- Intérprete ----
        JPanel interprete = new JPanel(new BorderLayout(4, 4));
        interprete.setBorder(BorderFactory.createTitledBorder(
                "Expresión (ej: muestra empleados por empresas)"));
        JPanel linea = new JPanel(new FlowLayout(FlowLayout.LEFT));
        linea.add(new JLabel("Expresión:")); linea.add(txtExpresion); linea.add(btnInterpretar);
        interprete.add(linea, BorderLayout.NORTH);

        txtSalida.setEditable(false);
        txtSalida.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));
        interprete.add(new JScrollPane(txtSalida), BorderLayout.CENTER);
        add(interprete, BorderLayout.CENTER);

        // ---- Eventos ----
        btnAgregarEmpresa.addActionListener(e -> accion(this::agregarEmpresa));
        btnAgregarEmpleado.addActionListener(e -> accion(this::agregarEmpleado));
        btnRelacionar.addActionListener(e -> accion(this::relacionar));
        btnInterpretar.addActionListener(e -> accion(this::interpretar));
        txtExpresion.addActionListener(e -> accion(this::interpretar));

        accion(() -> {});   // carga inicial
        pack();
        setMinimumSize(new Dimension(700, 500));
        setLocationRelativeTo(null);
    }

    // ---- Acciones ----

    private void agregarEmpresa() throws Exception {
        String nombre = txtEmpresa.getText().trim();
        if (nombre.isEmpty()) throw new IllegalArgumentException("Escribe el nombre de la empresa.");
        dao.insertarEmpresa(nombre);
        txtEmpresa.setText("");
    }

    private void agregarEmpleado() throws Exception {
        String nombre = txtEmpleado.getText().trim();
        if (nombre.isEmpty()) throw new IllegalArgumentException("Escribe el nombre del empleado.");
        dao.insertarEmpleado(nombre);
        txtEmpleado.setText("");
    }

    private void relacionar() throws Exception {
        String empresa = (String) cmbEmpresa.getSelectedItem();
        String empleado = (String) cmbEmpleado.getSelectedItem();
        if (empresa == null || empleado == null) {
            throw new IllegalArgumentException("Agrega al menos una empresa y un empleado.");
        }
        dao.insertarRelacion(empresa, empleado);
    }

    private void interpretar() {
        InterpreteCliente cliente = new InterpreteCliente(contexto);
        txtSalida.setText(cliente.interpreta(txtExpresion.getText().trim()));
        txtSalida.setCaretPosition(0);
    }

    // ---- Utilidades ----

    @FunctionalInterface
    private interface Accion { void run() throws Exception; }

    private void accion(Accion a) {
        try {
            a.run();
            recargar();
        } catch (Exception ex) {
            String msg = ex.getMessage() != null ? ex.getMessage() : ex.toString();
            JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // vuelve a leer la BD y actualiza los combos
    private void recargar() throws Exception {
        contexto = dao.cargarContexto();
        llenarCombo(cmbEmpresa, contexto.getEmpresas());
        llenarCombo(cmbEmpleado, contexto.getEmpleados());
    }

    private void llenarCombo(JComboBox<String> combo, List<String> items) {
        Object seleccionado = combo.getSelectedItem();
        combo.removeAllItems();
        for (String s : items) combo.addItem(s);
        if (seleccionado != null) combo.setSelectedItem(seleccionado);
    }
}