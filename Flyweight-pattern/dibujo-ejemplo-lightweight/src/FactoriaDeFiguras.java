import java.util.*;
import java.awt.*;

public class FactoriaDeFiguras {
    // Almacena color
    private static final HashMap circulosPorColor = new HashMap();
    private static final HashMap cuadradosPorColor = new HashMap();
    private static final HashMap triangulosPorColor = new HashMap(); // Nuevo mapa para triángulos

    public static final int CUADRADO = 0;
    public static final int CIRCULO = 1;
    public static final int TRIANGULO = 3; // Nueva constante

    Figura fig;

    // Obtiene el peso ligero
    public Figura getFigura(int tipo, Color color) {
        if (tipo == CUADRADO) {
            fig = (Cuadrado) cuadradosPorColor.get(color);
            if (fig == null) {
                fig = new Cuadrado(color);
                cuadradosPorColor.put(color, fig);
                System.out.println("Creamos un cuadrado de color: " + color);
            }
            return fig;
        } else if (tipo == CIRCULO) {
            fig = (Circulo) circulosPorColor.get(color);
            if (fig == null) {
                fig = new Circulo(color);
                circulosPorColor.put(color, fig);
                System.out.println("Creamos un circulo de color: " + color);
            }
            return fig;
        } else if (tipo == TRIANGULO) {
            fig = (Triangulo) triangulosPorColor.get(color);
            if (fig == null) {
                fig = new Triangulo(color);
                triangulosPorColor.put(color, fig);
                System.out.println("Creamos un triangulo de color: " + color);
            }
            return fig;
        }
        return null;
    }
}