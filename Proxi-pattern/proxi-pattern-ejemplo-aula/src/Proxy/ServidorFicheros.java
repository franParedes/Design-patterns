package Proxy;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

public class ServidorFicheros implements Servidor {
    private String nombre;
    private HashMap<String, Fichero> ficheros;
    private Fichero fich;

    public ServidorFicheros(String nombre) {
        this.nombre = nombre;
        ficheros = new HashMap<String, Fichero>();
    }

    public String subir(String nomFich) {
        fich = new Fichero(nomFich);
        ficheros.put(fich.getId(), fich);
        return "\nFichero subido correctamente al servidor real.";
    }

    public Fichero bajar(String id) {
        return ficheros.get(id);
    }

    public String listado() {
        String lis = "\n### Listado del servidor: " + this.nombre + " ###";
        if (ficheros.isEmpty()) {
            return lis + "\n\tEl servidor esta vacio.";
        }
        Collection<Fichero> c = ficheros.values();
        Iterator<Fichero> i = c.iterator();
        while (i.hasNext()) {
            fich = i.next();
            lis += "\n\t" + fich.toString();
        }
        return lis;
    }
}