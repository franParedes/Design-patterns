import java.io.*;

public class ControlEntrada {

    private static BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
    // Se agrega la variable password
    private static String usuario, password, res;

    public static void main(String args[]) {
        ContadorLogin contador = ContadorLogin.getInstancia();
        while (true) {
            try {
                do {
                    System.out.println("\n\n--- Operaciones de Login ---");
                    System.out.println(" 1 - Login. ");
                    System.out.println(" 2 - Salir. ");
                    System.out.print(" Seleccione la operación: ");
                    res = entrada.readLine();
                } while (!res.equals("1") && !res.equals("2"));

                // Login
                if (res.equals("1")) {
                    System.out.println("\n -- Login -- ");
                    System.out.print("Introduzca usuario: ");
                    usuario = entrada.readLine();

                    System.out.print("Introduzca contraseña: ");
                    password = entrada.readLine();

                    // Se evalúa pasando ambos parámetros
                    if (contador.devolverEstadoCuenta(usuario, password)) {
                        System.out.println("Ya se ha registrado en el sistema.");
                        System.out.println("La contraseña almacenada es: " + contador.obtenerPassword(usuario));
                    } else {
                        System.out.println("Bienvenido al sistema");
                        System.out.println("Se registró correctamente con la contraseña: " + password);
                    }

                } // Salir
                else if (res.equals("2")) {
                    System.exit(0);
                }
            } catch (IOException ioe) {
                System.out.println("\nError de entrada/salida: " + ioe.toString());
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
    }
}