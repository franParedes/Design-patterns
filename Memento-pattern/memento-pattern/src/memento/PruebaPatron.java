package memento;

import javax.swing.SwingUtilities;

public class PruebaPatron {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaMemento().setVisible(true));
    }
}