package ui;

import builders.*;
import director.Vendedor;
import modelos.Documentacion;
import db.ConexionDB;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class VentanaRegistro extends JFrame {
    private JTextField txtCliente;
    private JComboBox<String> comboFormato;

    public VentanaRegistro() {
        setTitle("Generador de Documentación (Builder Pattern)");
        setSize(450, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Añadiendo padding (márgenes) para que se vea más estético
        JPanel panelPrincipal = new JPanel(new GridLayout(4, 1, 10, 15));
        panelPrincipal.setBorder(new EmptyBorder(20, 30, 20, 30));

        // Campos de texto con mejor fuente
        JPanel panelCliente = new JPanel(new BorderLayout());
        JLabel lblCliente = new JLabel("Nombre del Cliente:");
        lblCliente.setFont(new Font("Arial", Font.BOLD, 14));
        txtCliente = new JTextField();
        txtCliente.setFont(new Font("Arial", Font.PLAIN, 14));
        panelCliente.add(lblCliente, BorderLayout.NORTH);
        panelCliente.add(txtCliente, BorderLayout.CENTER);

        JPanel panelFormato = new JPanel(new BorderLayout());
        JLabel lblFormato = new JLabel("Formato de Salida:");
        lblFormato.setFont(new Font("Arial", Font.BOLD, 14));
        comboFormato = new JComboBox<>(new String[]{"HTML", "PDF"});
        comboFormato.setFont(new Font("Arial", Font.PLAIN, 14));
        panelFormato.add(lblFormato, BorderLayout.NORTH);
        panelFormato.add(comboFormato, BorderLayout.CENTER);

        // Botones más grandes
        JButton btnGenerar = new JButton("Construir y Guardar Documentos");
        btnGenerar.setFont(new Font("Arial", Font.BOLD, 13));

        JButton btnVerDatos = new JButton("Ver Historial de Documentos");
        btnVerDatos.setFont(new Font("Arial", Font.BOLD, 13));

        panelPrincipal.add(panelCliente);
        panelPrincipal.add(panelFormato);
        panelPrincipal.add(btnGenerar);
        panelPrincipal.add(btnVerDatos);

        add(panelPrincipal);

        // Eventos
        btnVerDatos.addActionListener(e -> new VentanaListado().setVisible(true));
        btnGenerar.addActionListener(e -> generarDocumentacion());
    }

    private void generarDocumentacion() {
        String cliente = txtCliente.getText().trim();
        String formato = comboFormato.getSelectedItem().toString();

        if (cliente.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El nombre del cliente no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 1. Instanciamos el Builder según la UI
        ConstructorDocumentacionVehiculo builder;
        if (formato.equals("HTML")) {
            builder = new ConstructorDocumentacionVehiculoHtml();
        } else {
            builder = new ConstructorDocumentacionVehiculoPdf();
        }

        // 2. El Director orquesta la creación
        Vendedor vendedor = new Vendedor(builder);
        Documentacion doc = vendedor.construye(cliente);

        // 3. Guardamos en Base de Datos
        ConexionDB.guardarDocumentacion(cliente, formato, doc.obtenerContenidoCompleto());

        JOptionPane.showMessageDialog(this, "Documentación " + formato + " construida y guardada con éxito.");
        txtCliente.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaRegistro().setVisible(true));
    }
}