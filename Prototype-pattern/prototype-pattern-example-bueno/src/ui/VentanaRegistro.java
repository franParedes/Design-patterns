package ui;
import gestores.DocumentacionCliente;
import gestores.DocumentacionEnBlanco;
import modelos.*;
import db.ConexionDB;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class VentanaRegistro extends JFrame {
    private JTextField txtCliente;

    public VentanaRegistro() {
        // Inicializamos los prototipos en blanco una sola vez (Singleton)
        DocumentacionEnBlanco base = DocumentacionEnBlanco.Instance();
        if (base.getDocumentos().isEmpty()) {
            base.incluye(new OrdenDePedido());
            base.incluye(new CertificadoCesion());
            base.incluye(new SolicitudMatriculacion());
        }

        setTitle("Generador de Documentos (Prototype Pattern)");
        setSize(450, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panelPrincipal = new JPanel(new GridLayout(3, 1, 10, 15));
        panelPrincipal.setBorder(new EmptyBorder(20, 30, 20, 30));

        JPanel panelCliente = new JPanel(new BorderLayout());
        JLabel lblCliente = new JLabel("Nombre de la Información / Cliente:");
        lblCliente.setFont(new Font("Arial", Font.BOLD, 14));
        txtCliente = new JTextField();
        txtCliente.setFont(new Font("Arial", Font.PLAIN, 14));
        panelCliente.add(lblCliente, BorderLayout.NORTH);
        panelCliente.add(txtCliente, BorderLayout.CENTER);

        JButton btnGenerar = new JButton("Clonar y Guardar Documentos");
        btnGenerar.setFont(new Font("Arial", Font.BOLD, 13));

        JButton btnVerDatos = new JButton("Ver Historial en BD");
        btnVerDatos.setFont(new Font("Arial", Font.BOLD, 13));

        panelPrincipal.add(panelCliente);
        panelPrincipal.add(btnGenerar);
        panelPrincipal.add(btnVerDatos);

        add(panelPrincipal);

        btnVerDatos.addActionListener(e -> new VentanaListado().setVisible(true));
        btnGenerar.addActionListener(e -> procesarClonacion());
    }

    private void procesarClonacion() {
        String cliente = txtCliente.getText().trim();
        if (cliente.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe ingresar una información a rellenar.");
            return;
        }

        // Aplicamos el patrón Prototype
        DocumentacionCliente docCliente = new DocumentacionCliente(cliente);

        // Extraemos las copias y las guardamos
        for (Documento doc : docCliente.getDocumentos()) {
            String tipo = doc.getClass().getSimpleName(); // Extrae el nombre de la clase
            ConexionDB.guardarDocumento(cliente, tipo, doc.getContenido());
        }

        JOptionPane.showMessageDialog(this, "Se han clonado " + docCliente.getDocumentos().size() + " documentos con éxito.");
        txtCliente.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaRegistro().setVisible(true));
    }
}