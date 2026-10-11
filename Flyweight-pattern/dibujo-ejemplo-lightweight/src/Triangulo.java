import java.awt.*;

public class Triangulo extends Figura {
    public Triangulo(Color color) {
        super(color);
    }

    public void dibujar(Graphics g, int x, int y, int lado) {
        g.setColor(color);
        // Calculamos los 3 puntos del triángulo
        int[] xPoints = {x + lado / 2, x, x + lado};
        int[] yPoints = {y, y + lado, y + lado};

        g.drawPolygon(xPoints, yPoints, 3);
    }
}