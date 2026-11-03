package memento;

public class Partida {
    private String nombre;
    private String fase;
    private FechaHora fecha;

    // Partida nueva: la fecha es el momento actual
    public Partida(String nombre, String fase) {
        this.nombre = nombre;
        this.fase = fase;
        this.fecha = new FechaHora();
    }

    // Partida recuperada de la base de datos: conserva su fecha original
    public Partida(String nombre, String fase, FechaHora fecha) {
        this.nombre = nombre;
        this.fase = fase;
        this.fecha = fecha;
    }

    public String getNombre() {
        return this.nombre;
    }
    public String getFase() {
        return this.fase;
    }
    public String getFecha() {
        return this.fecha.toString();
    }
    public FechaHora getFechaHora() {
        return this.fecha;
    }
    @Override
    public String toString() {
        return "- Partida salvada:\n" + nombre + " # " + fase + " # " + fecha.toString();
    }
}