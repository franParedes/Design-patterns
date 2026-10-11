package modelos;

public abstract class Decorador implements ComponenteGraficoVehiculo {
    protected ComponenteGraficoVehiculo componente;

    public Decorador(ComponenteGraficoVehiculo componente) {
        this.componente = componente;
    }

    public String visualiza() {
        return componente.visualiza();
    }
}