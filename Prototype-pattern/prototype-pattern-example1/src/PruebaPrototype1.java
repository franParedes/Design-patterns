import java.util.*;

public class PruebaPrototype1 {
    Persona juan, luis;
    ArrayList<String> tfnos = new ArrayList<>();

    public PruebaPrototype1() {
        Fecha f1 = new Fecha(15, 3, 1965);
        tfnos.add("918885566");
        tfnos.add("606997755");

        // 1. Se crea a Juan con los nuevos parámetros al final (Sexo y Estado Civil)
        juan = new Persona("15664386 T", "Juan", f1, "calle", tfnos, "Masculino", "Soltero");

        // 2. Se CLONA a Juan para crear a Luis (PROTOTYPE)
        luis = new Persona(juan);

        // 3. Modificamos los datos de Luis para separarlo de Juan
        luis.setDni("22879654 X");
        luis.setNombre("Luis"); // ¡Agregué esto porque sino Luis se seguía llamando Juan!
        luis.getTfnos().add("968559977");
        luis.getTfnos().add("626776644");
        luis.setFechaNac(new Fecha(1, 1, 1971));

        // Cambiamos un atributo nuevo para confirmar que funciona
        luis.setEstadoCivil("Casado");
    }

    public void test() {
        System.out.print("Juan : ");
        System.out.println(juan.toString());

        System.out.print("Luis : ");
        System.out.println(luis.toString());

        System.out.println("\n-------------------- COPIA PROFUNDA --------------------");
        System.out.println("juan == luis : " + (juan == luis));
        System.out.println("juan.equals(luis) : " + juan.equals(luis));

        System.out.println("\n--- COMPROBACIÓN DE INDEPENDENCIA ---");
        System.out.println("Teléfonos Juan: " + juan.getTfnos().toString());
        System.out.println("Teléfonos Luis: " + luis.getTfnos().toString());

        System.out.println("Estado Civil Juan: " + juan.getEstadoCivil());
        System.out.println("Estado Civil Luis: " + luis.getEstadoCivil());
    }

    public static void main(String[] args) {
        PruebaPrototype1 prueba = new PruebaPrototype1();
        prueba.test();
    }
}