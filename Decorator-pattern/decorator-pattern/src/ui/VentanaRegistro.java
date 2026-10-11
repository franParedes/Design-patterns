package ui;

import modelos.*;
import db.ConexionDB;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class VentanaRegistro extends JFrame {
    private JTextField txtVehiculo;
    private JCheckBox chkInfoTecnica;
    private JCheckBox chkLogoMarca;

    public VentanaRegistro() {
        setTitle("Personalizador de Vehículos (Decorator Pattern)");
        setSize(500, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panelPrincipal = new JPanel(new GridLayout(5, 1, 10, 10));
        panelPrincipal.setBorder(new EmptyBorder(20, 30, 20, 30));

        // Campo de entrada
        JPanel panelEntrada = new JPanel(new BorderLayout());
        JLabel lblVehiculo = new JLabel("Nombre del Vehículo Base:");
        lblVehiculo.setFont(new Font("Arial", Font.BOLD, 14));
        txtVehiculo = new JTextField();
        txtVehiculo.setFont(new Font("Arial", Font.PLAIN, 14));
        panelEntrada.add(lblVehiculo, BorderLayout.NORTH);
        panelEntrada.add(txtVehiculo, BorderLayout.CENTER);
        panelPrincipal.add(panelEntrada);

        // Opciones de Decoración
        JLabel lblOpciones = new JLabel("Seleccione los decoradores:");
        lblOpciones.setFont(new Font("Arial", Font.BOLD, 14));
        panelPrincipal.add(lblOpciones);

        chkInfoTecnica = new JCheckBox("Agregar Información Técnica");
        chkInfoTecnica.setFont(new Font("Arial", Font.PLAIN, 14));
        panelPrincipal.add(chkInfoTecnica);

        chkLogoMarca = new JCheckBox("Agregar Logotipo de la Marca");
        chkLogoMarca.setFont(new Font("Arial", Font.PLAIN, 14));
        panelPrincipal.add(chkLogoMarca);

        // Botones
        JPanel panelBotones = new JPanel(new GridLayout(1, 2, 10, 0));
        JButton btnGuardar = new JButton("Visualizar y Guardar");
        btnGuardar.setFont(new Font("Arial", Font.BOLD, 12));
        JButton btnVerDatos = new JButton("Ver Registros en BD");
        btnVerDatos.setFont(new Font("Arial", Font.BOLD, 12));

        panelBotones.add(btnGuardar);
        panelBotones.add(btnVerDatos);
        panelPrincipal.add(panelBotones);

        add(panelPrincipal);

        btnVerDatos.addActionListener(e -> new VentanaListado().setVisible(true));
        btnGuardar.addActionListener(e -> procesarDecoracion());
    }

    private void procesarDecoracion() {
        String nombre = txtVehiculo.getText().trim();
        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe ingresar el nombre del vehículo.");
            return;
        }

        // 1. Instanciamos el Componente Base
        ComponenteGraficoVehiculo vehiculoFinal = new VistaVehiculo(nombre);

        // 2. Apilamos los Decoradores dinámicamente según la selección
        if (chkInfoTecnica.isSelected()) {
            vehiculoFinal = new ModeloDecorador(vehiculoFinal);
        }

        if (chkLogoMarca.isSelected()) {
            vehiculoFinal = new MarcaDecorador(vehiculoFinal);
        }

        // 3. Obtenemos el resultado final procesado a través de las capas
        String resultadoVisual = vehiculoFinal.visualiza();

        // 4. Guardamos en Base de Datos
        ConexionDB.guardarVehiculo(nombre, resultadoVisual);

        JOptionPane.showMessageDialog(this, "Éxito. Cadena generada:\n" + resultadoVisual);

        txtVehiculo.setText("");
        chkInfoTecnica.setSelected(false);
        chkLogoMarca.setSelected(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaRegistro().setVisible(true));
    }
}