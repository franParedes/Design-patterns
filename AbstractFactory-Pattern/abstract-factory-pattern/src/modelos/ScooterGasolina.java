package modelos;

public class ScooterGasolina extends Scooter {
    public ScooterGasolina(String modelo, String color,
                           int potencia)
    {
        super(modelo, color, potencia);
    }

    public void mostrarCaracteristicas()
    {
        System.out.println("modelos.Scooter de gasolina de modelo: " +
                modelo + " de color: " + color +
                " de potencia: " + potencia);
    }
}
