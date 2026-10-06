package ui;

import modelos.*;
import db.ConexionDB;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class VentanaRegistro extends JFrame {
    private JTextField txtNombre, txtVehiculos;
    private JComboBox<String> comboTipo;
    private JLabel lblCosteGlobal;

    // El objeto compuesto principal (El árbol)
    private EmpresaMadre grupoMadre = new EmpresaMadre("Grupo Corporativo Central");

    public VentanaRegistro() {
        setTitle("Gestor Corporativo (Composite Pattern)");
        setSize(480, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panelPrincipal = new JPanel(new GridLayout(6, 1, 10, 10));
        panelPrincipal.setBorder(new EmptyBorder(20, 30, 20, 30));

        txtNombre = crearCampo(panelPrincipal, "Nombre de la Empresa / Filial:");

        JPanel panelTipo = new JPanel(new BorderLayout());
        JLabel lblTipo = new JLabel("Tipo de Estructura:");
        lblTipo.setFont(new Font("Arial", Font.BOLD, 14));
        comboTipo = new JComboBox<>(new String[]{"Empresa Sin Filial (Hoja)", "Empresa Madre (Nodo)"});
        comboTipo.setFont(new Font("Arial", Font.PLAIN, 14));
        panelTipo.add(lblTipo, BorderLayout.NORTH);
        panelTipo.add(comboTipo, BorderLayout.CENTER);
        panelPrincipal.add(panelTipo);

        txtVehiculos = crearCampo(panelPrincipal, "Cantidad de Vehículos Propios:");

        JPanel panelBotones = new JPanel(new GridLayout(1, 2, 10, 0));
        JButton btnGuardar = new JButton("Registrar y Unir al Grupo");
        btnGuardar.setFont(new Font("Arial", Font.BOLD, 12));
        JButton btnVerDatos = new JButton("Ver Registros en BD");
        btnVerDatos.setFont(new Font("Arial", Font.BOLD, 12));
        panelBotones.add(btnGuardar);
        panelBotones.add(btnVerDatos);
        panelPrincipal.add(panelBotones);

        // Etiqueta para demostrar el cálculo dinámico del patrón Composite
        lblCosteGlobal = new JLabel("Coste de mantenimiento del Grupo: $0.0");
        lblCosteGlobal.setFont(new Font("Arial", Font.BOLD, 15));
        lblCosteGlobal.setForeground(new Color(0, 102, 51));
        panelPrincipal.add(lblCosteGlobal);

        add(panelPrincipal);

        btnVerDatos.addActionListener(e -> new VentanaListado().setVisible(true));
        btnGuardar.addActionListener(e -> procesarEmpresa());
    }

    private JTextField crearCampo(JPanel contenedor, String etiqueta) {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel lbl = new JLabel(etiqueta);
        lbl.setFont(new Font("Arial", Font.BOLD, 14));
        JTextField txt = new JTextField();
        txt.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(lbl, BorderLayout.NORTH);
        panel.add(txt, BorderLayout.CENTER);
        contenedor.add(panel);
        return txt;
    }

    private void procesarEmpresa() {
        try {
            String nombre = txtNombre.getText().trim();
            String tipoSeleccionado = comboTipo.getSelectedItem().toString();
            int vehiculos = Integer.parseInt(txtVehiculos.getText().trim());

            if (nombre.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe ingresar el nombre de la empresa.");
                return;
            }

            Empresa nuevaEmpresa;
            String tipoBD;

            // 1. Instanciamos el componente adecuado (Hoja o Compuesto)
            if (tipoSeleccionado.contains("Sin Filial")) {
                nuevaEmpresa = new EmpresaSinFilial(nombre);
                tipoBD = "Sin Filial";
            } else {
                nuevaEmpresa = new EmpresaMadre(nombre);
                tipoBD = "Madre";
            }

            nuevaEmpresa.agregaVehiculo(vehiculos);
            double costeIndividual = nuevaEmpresa.calculaCosteMantenimiento();

            // 2. Comprobación del Composite: Añadimos la nueva empresa al grupo general
            grupoMadre.agregaFilial(nuevaEmpresa);

            // 3. Guardar en Base de Datos
            ConexionDB.guardarEmpresa(nombre, tipoBD, vehiculos, costeIndividual);

            // 4. Actualizar la interfaz aprovechando el polimorfismo del Composite
            lblCosteGlobal.setText("Coste de mantenimiento del Grupo: $" + grupoMadre.calculaCosteMantenimiento());

            JOptionPane.showMessageDialog(this, "Empresa registrada exitosamente en BD y añadida al Grupo Corporativo.");
            txtNombre.setText("");
            txtVehiculos.setText("");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingrese un número válido para los vehículos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaRegistro().setVisible(true));
    }
}