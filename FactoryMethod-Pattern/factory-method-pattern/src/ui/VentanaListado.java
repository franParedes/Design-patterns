package ui;

import db.ConexionDB;
import javax.swing.*;
import java.awt.*;

public class VentanaListado extends JFrame {
    public VentanaListado() {
        setTitle("Listado de Pedidos Registrados");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JTable tabla = new JTable(ConexionDB.obtenerPedidos());
        add(new JScrollPane(tabla), BorderLayout.CENTER);

        JButton btnCerrar = new JButton("Cerrar Listado");
        btnCerrar.addActionListener(e -> dispose());
        add(btnCerrar, BorderLayout.SOUTH);
    }
}