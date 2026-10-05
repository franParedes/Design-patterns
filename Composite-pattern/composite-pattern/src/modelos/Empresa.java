package modelos;

public abstract class Empresa {
    protected String nombre;
    protected static double costeUnitarioVehiculo = 5.0;
    protected int nVehiculos;

    public Empresa(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public int getVehiculos() {
        return nVehiculos;
    }

    public void agregaVehiculo(int cantidad) {
        nVehiculos = nVehiculos + cantidad;
    }

    public abstract double calculaCosteMantenimiento();
    public abstract boolean agregaFilial(Empresa filial);
}