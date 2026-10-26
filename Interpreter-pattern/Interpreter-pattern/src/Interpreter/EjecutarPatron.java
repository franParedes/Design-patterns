package Interpreter;

import javax.swing.SwingUtilities;

public class EjecutarPatron {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaInterprete().setVisible(true));
    }
}