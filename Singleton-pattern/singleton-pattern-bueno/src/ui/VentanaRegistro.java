package ui;

import modelos.Comercial;
import db.ConexionDB;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class VentanaRegistro extends JFrame {
    private JTextField txtNombre, txtDireccion, txtEmail;

    public VentanaRegistro() {
        setTitle("Configuración de Comercial (Singleton Pattern)");
        setSize(450, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panelPrincipal = new JPanel(new GridLayout(5, 1, 10, 15));
        panelPrincipal.setBorder(new EmptyBorder(20, 30, 20, 30));

        txtNombre = crearCampo(panelPrincipal, "Nombre del Comercial:");
        txtDireccion = crearCampo(panelPrincipal, "Dirección:");
        txtEmail = crearCampo(panelPrincipal, "Correo Electrónico:");

        // Cargar los datos iniciales de la única instancia
        cargarDatosInstancia();

        JPanel panelBotones = new JPanel(new GridLayout(1, 2, 10, 0));
        JButton btnGuardar = new JButton("Actualizar y Guardar");
        btnGuardar.setFont(new Font("Arial", Font.BOLD, 13));
        JButton btnVerDatos = new JButton("Ver Historial BD");
        btnVerDatos.setFont(new Font("Arial", Font.BOLD, 13));

        panelBotones.add(btnGuardar);
        panelBotones.add(btnVerDatos);
        panelPrincipal.add(panelBotones);

        add(panelPrincipal);

        btnVerDatos.addActionListener(e -> new VentanaListado().setVisible(true));
        btnGuardar.addActionListener(e -> actualizarSingleton());
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

    private void cargarDatosInstancia() {
        Comercial elComercial = Comercial.Instance();
        if (elComercial.getNombre() != null) {
            txtNombre.setText(elComercial.getNombre());
            txtDireccion.setText(elComercial.getDireccion());
            txtEmail.setText(elComercial.getEmail());
        }
    }

    private void actualizarSingleton() {
        String nombre = txtNombre.getText().trim();
        String direccion = txtDireccion.getText().trim();
        String email = txtEmail.getText().trim();

        if (nombre.isEmpty() || direccion.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.");
            return;
        }

        // Modificamos la instancia global
        Comercial elComercial = Comercial.Instance();
        elComercial.setNombre(nombre);
        elComercial.setDireccion(direccion);
        elComercial.setEmail(email);

        // Guardamos el estado en BD
        ConexionDB.guardarComercial(nombre, direccion, email);

        JOptionPane.showMessageDialog(this, "Instancia Singleton actualizada y guardada en BD.");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaRegistro().setVisible(true));
    }
}