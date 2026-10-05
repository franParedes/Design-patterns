package modelos;

public class EmpresaSinFilial extends Empresa {
    public EmpresaSinFilial(String nombre) {
        super(nombre);
    }

    public boolean agregaFilial(Empresa filial) {
        return false;
    }

    public double calculaCosteMantenimiento() {
        return nVehiculos * costeUnitarioVehiculo;
    }
}