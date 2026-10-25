package Proxy;
import java.io.*;

public class Fichero {
    private String id;
    private String nombre;
    private Fecha fechaCrea;
    private File fichero;
    private long tamanio;
    private static int contador = 0;

    public Fichero(String nombre) {
        this.id = Integer.toString(getContador());
        this.nombre = nombre;
        this.fechaCrea = new Fecha();
        this.fichero = new File(nombre);
        this.tamanio = fichero.length();
    }

    private static int getContador() {
        return contador++;
    }

    public String getId() { return this.id; }
    public String getNombre() { return this.nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public Fecha getFechaCrea() { return this.fechaCrea; }
    public File getFichero() { return this.fichero; }

    @Override
    public String toString() {
        return id + " " + nombre + " # " + tamanio + " bytes";
    }
}