import java.io.*;

public class PruebaPatron {
    public static void main(String args[]) throws IOException {
        BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
        FachadaCajero fachada = new FachadaCajero();
        int opcion = 0;

        do {
            System.out.println("\n--- Introduce operacion cajero ---"
                    + "\n1- Sacar Dinero"
                    + "\n2- Recarga Movil"
                    + "\n3- Prestamo"
                    + "\n4- Retiro de Tarjeta"
                    + "\n5- Deposito"
                    + "\n6- Salir"
                    + "\nSeleccione una opcion: ");

            try {
                opcion = Integer.parseInt(entrada.readLine());

                switch (opcion) {
                    case 1:
                        System.out.println(fachada.sacarDinero());
                        break;
                    case 2:
                        System.out.println(fachada.recargaMovil());
                        break;
                    case 3:
                        System.out.println(fachada.pedirPrestamo());
                        break;
                    case 4:
                        System.out.println(fachada.retiroDeTarjeta());
                        break;
                    case 5:
                        System.out.println(fachada.realizarDeposito());
                        break;
                    case 6:
                        System.out.println("Saliendo del cajero...");
                        break;
                    default:
                        System.out.println("Opcion no valida. Intente de nuevo.");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingrese un numero valido.");
            }
        } while (opcion != 6);
    }
}