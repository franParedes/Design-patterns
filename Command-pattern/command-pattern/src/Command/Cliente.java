package Command;

import javax.swing.SwingUtilities;

public class Cliente {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaReuniones().setVisible(true));
    }
}