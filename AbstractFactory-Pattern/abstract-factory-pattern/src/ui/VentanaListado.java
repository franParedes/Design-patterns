package ui;

import db.ConexionDB;
import javax.swing.*;
import java.awt.*;

public class VentanaListado extends JFrame {
    public VentanaListado() {
        setTitle("Vehículos Registrados");
        // Hacemos esta ventana bastante grande
        setSize(800, 500);
        // DISPOSE_ON_CLOSE asegura que al cerrar esta ventana no se cierre todo el programa
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // Centra la ventana en la pantalla

        // Creamos la tabla y le inyectamos los datos directamente desde la base de datos
        JTable tabla = new JTable(ConexionDB.obtenerVehiculos());

        // El ScrollPane permite que podamos bajar si hay muchos registros
        JScrollPane scrollPane = new JScrollPane(tabla);
        add(scrollPane, BorderLayout.CENTER);

        // Botón para cerrar solo esta ventana
        JButton btnCerrar = new JButton("Volver al Registro");
        btnCerrar.addActionListener(e -> dispose());
        add(btnCerrar, BorderLayout.SOUTH);
    }
}
