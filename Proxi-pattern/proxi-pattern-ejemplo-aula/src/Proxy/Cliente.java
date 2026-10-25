package Proxy;
import java.io.*;

public class Cliente {
    private static BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));

    // Cambiamos el Proxy simple por el Proxy de Seguridad.
    // Prueba cambiando "admin123" por otra cosa y veras que bloquea las subidas y bajadas.
    private static Servidor proxy = new ProxySeguridad(new ServidorFicheros("MiServidor"), "admin123");

    public static void main(String args[]) {
        String res, nom, id;
        while (true) {
            try {
                do {
                    System.out.println("\n\n--- Operaciones del Servidor ---");
                    System.out.println(" 1 - Subir fichero. ");
                    System.out.println(" 2 - Bajar fichero. ");
                    System.out.println(" 3 - Listado ficheros. ");
                    System.out.println(" 4 - Salir. ");
                    System.out.print(" Seleccione la operacion: ");
                    res = entrada.readLine();
                } while (!res.equals("1") && !res.equals("2") && !res.equals("3") && !res.equals("4"));

                if (res.equals("1")) {
                    System.out.println("\n -- Subir fichero -- ");
                    System.out.print("Introduzca nombre fichero: ");
                    nom = entrada.readLine();
                    System.out.println(proxy.subir(nom));
                } else if (res.equals("2")) {
                    System.out.println("\n -- Bajar fichero -- ");
                    System.out.println(proxy.listado());
                    System.out.print("Introduzca id del fichero: ");
                    id = entrada.readLine();
                    Fichero fich = proxy.bajar(id);
                    if (fich != null) {
                        System.out.println("Fichero descargado: " + fich.toString());
                    }
                } else if (res.equals("3")) {
                    System.out.println("\n -- Listado ficheros -- ");
                    System.out.println(proxy.listado());
                } else if (res.equals("4")) {
                    System.exit(0);
                }
            } catch (NumberFormatException nfe) {
                System.out.println("\nError de formato numerico: " + nfe.toString());
            } catch (IOException ioe) {
                System.out.println("\nError de entrada/salida: " + ioe.toString());
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
    }
}