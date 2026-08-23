package ui;

import fabricas.FabricaVehiculo;
import fabricas.FabricaVehiculoElectricidad;
import fabricas.FabricaVehiculoGasolina;
import modelos.Automovil;
import modelos.Scooter;
import db.ConexionDB;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaRegistro extends JFrame {
    private JComboBox<String> comboTipo, comboMotor;
    private JTextField txtModelo, txtColor, txtPotencia, txtEspacio;

    public VentanaRegistro() {
        setTitle("Registro de Vehículos - Abstract Factory");

        // 1. LA HACEMOS MÁS GRANDE Y CENTRADA
        setSize(500, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Ajustamos la cuadrícula (7 filas, 2 columnas)
        setLayout(new GridLayout(7, 2, 10, 10));

        add(new JLabel("Tipo de Vehículo:"));
        comboTipo = new JComboBox<>(new String[]{"Automóvil", "Scooter"});
        add(comboTipo);

        add(new JLabel("Tipo de Motor:"));
        comboMotor = new JComboBox<>(new String[]{"Eléctrico", "Gasolina"});
        add(comboMotor);

        add(new JLabel("Modelo:"));
        txtModelo = new JTextField();
        add(txtModelo);

        add(new JLabel("Color:"));
        txtColor = new JTextField();
        add(txtColor);

        add(new JLabel("Potencia:"));
        txtPotencia = new JTextField();
        add(txtPotencia);

        add(new JLabel("Espacio (0 para Scooter):"));
        txtEspacio = new JTextField();
        add(txtEspacio);

        // 2. BOTONES JUNTOS EN LA ÚLTIMA FILA
        JButton btnGuardar = new JButton("Registrar y Guardar");
        JButton btnVerListado = new JButton("Ver Vehículos Registrados");

        add(btnGuardar);
        add(btnVerListado);

        // Lógica de Guardar (Se mantiene igual que antes)
        btnGuardar.addActionListener(e -> registrarVehiculo());

        // Lógica del NUEVO BOTÓN
        btnVerListado.addActionListener(e -> {
            // Abre la nueva ventana grande de listado
            new VentanaListado().setVisible(true);
        });
    }

    private void registrarVehiculo() {
        try {
            // Leer datos de la ventana
            String tipo = comboTipo.getSelectedItem().toString();
            String motor = comboMotor.getSelectedItem().toString();
            String modelo = txtModelo.getText();
            String color = txtColor.getText();
            int potencia = Integer.parseInt(txtPotencia.getText());
            double espacio = txtEspacio.getText().isEmpty() ? 0 : Double.parseDouble(txtEspacio.getText());

            // Aplicar Abstract Factory
            FabricaVehiculo fabrica;
            if (motor.equals("Eléctrico")) {
                fabrica = new FabricaVehiculoElectricidad();
            } else {
                fabrica = new FabricaVehiculoGasolina();
            }

            if (tipo.equals("Automóvil")) {
                Automovil auto = fabrica.creaAutomovil(modelo, color, potencia, espacio);
                auto.mostrarCaracteristicas(); // Muestra en consola el objeto creado
            } else {
                Scooter scooter = fabrica.creaScooter(modelo, color, potencia);
                scooter.mostrarCaracteristicas(); // Muestra en consola el objeto creado
            }

            // Guardar en MySQL
            ConexionDB.guardarVehiculo(tipo, motor, modelo, color, potencia, espacio);

            // Mensaje de éxito en la ventana
            JOptionPane.showMessageDialog(this, "Vehículo registrado y guardado en BD.");

            // Limpiar campos
            txtModelo.setText("");
            txtColor.setText("");
            txtPotencia.setText("");
            txtEspacio.setText("");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese números válidos en Potencia y Espacio.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Nuevo punto de entrada de la aplicación
    public static void main(String[] args) {
        // Ejecutar la ventana
        SwingUtilities.invokeLater(() -> {
            new VentanaRegistro().setVisible(true);
        });
    }
}
