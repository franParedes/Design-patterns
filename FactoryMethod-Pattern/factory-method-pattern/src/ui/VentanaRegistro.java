package ui;

import creadores.*;
import db.ConexionDB;
import javax.swing.*;
import java.awt.*;

public class VentanaRegistro extends JFrame {

    private JComboBox<String> comboTipoCliente;
    private JTextField txtImporte;

    public VentanaRegistro() {
        setTitle("Factory Method - Registro de Pedidos");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 2, 10, 10));

        add(new JLabel("Tipo de Cliente:"));
        comboTipoCliente = new JComboBox<>(new String[]{"Contado", "Crédito"});
        add(comboTipoCliente);

        add(new JLabel("Importe del Pedido:"));
        txtImporte = new JTextField();
        add(txtImporte);

        JButton btnProcesar = new JButton("Procesar y Guardar");
        JButton btnVerListado = new JButton("Ver Historial BD");

        add(btnProcesar);
        add(btnVerListado);

        btnProcesar.addActionListener(e -> procesarPedido());
        btnVerListado.addActionListener(e -> new VentanaListado().setVisible(true));
    }

    private void procesarPedido() {
        try {
            String tipo = comboTipoCliente.getSelectedItem().toString();
            double importe = Double.parseDouble(txtImporte.getText());

            // 1. Aplicamos el Factory Method
            Cliente cliente;
            if (tipo.equals("Crédito")) {
                cliente = new ClienteCredito();
            } else {
                cliente = new ClienteContado();
            }

            // 2. Ejecutamos la lógica de negocio
            boolean fueValido = cliente.nuevoPedido(importe);

            // 3. Evaluamos: SOLO guardamos si superó la validación
            if (fueValido) {
                ConexionDB.guardarPedido(tipo, importe, "Aprobado y Pagado");
                JOptionPane.showMessageDialog(this, "Éxito: El pedido se ha realizado y guardado en la BD.");
                txtImporte.setText(""); // Limpiamos la caja de texto
            } else {
                // Si es crédito y no está entre 1000 y 5000, cae aquí y NO guarda nada
                JOptionPane.showMessageDialog(this,
                        "Pedido Rechazado por políticas (Créditos solo entre 1000 y 5000).\nNO se registró en la base de datos.",
                        "Validación fallida",
                        JOptionPane.WARNING_MESSAGE);
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingrese un importe numérico válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}