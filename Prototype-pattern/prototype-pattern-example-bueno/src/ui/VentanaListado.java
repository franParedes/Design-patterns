package ui;
import db.ConexionDB;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class VentanaListado extends JFrame {
    public VentanaListado() {
        setTitle("Documentos Clonados en Base de Datos");
        setSize(700, 450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(new EmptyBorder(15, 15, 15, 15));

        JTable tabla = new JTable(ConexionDB.obtenerDocumentos());
        tabla.setRowHeight(25);
        panelPrincipal.add(new JScrollPane(tabla), BorderLayout.CENTER);

        JButton btnCerrar = new JButton("Cerrar Historial");
        btnCerrar.setFont(new Font("Arial", Font.BOLD, 14));
        btnCerrar.addActionListener(e -> dispose());
        panelPrincipal.add(btnCerrar, BorderLayout.SOUTH);

        add(panelPrincipal);
    }
}