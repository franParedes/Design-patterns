import ui.VentanaRegistro;
import javax.swing.SwingUtilities;

public class Usuario {
    public static void main(String[] args) {
        // En lugar de crear los objetos directamente por consola,
        // le cedemos el control a la Interfaz Gráfica.
        SwingUtilities.invokeLater(() -> {
            new VentanaRegistro().setVisible(true);
        });
    }
}