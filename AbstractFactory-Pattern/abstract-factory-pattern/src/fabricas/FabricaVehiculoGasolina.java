package fabricas;

import modelos.Automovil;
import modelos.AutomovilGasolina;
import modelos.Scooter;
import modelos.ScooterGasolina;

public class FabricaVehiculoGasolina implements FabricaVehiculo {
    public Automovil creaAutomovil(String modelo, String
            color, int potencia, double espacio)
    {
        return new AutomovilGasolina(modelo, color,
                potencia, espacio);
    }

    public Scooter creaScooter(String modelo, String
            color, int potencia)
    {
        return new ScooterGasolina(modelo, color, potencia);
    }
}
