package modelos;

public class VistaVehiculo implements ComponenteGraficoVehiculo {
    private String nombreVehiculo;

    public VistaVehiculo(String nombreVehiculo) {
        this.nombreVehiculo = nombreVehiculo;
    }

    public String visualiza() {
        return "Visualización del vehículo: " + nombreVehiculo;
    }
}