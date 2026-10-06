package modelos;
import java.util.ArrayList;
import java.util.List;

public class EmpresaMadre extends Empresa {
    protected List<Empresa> filiales = new ArrayList<>();

    public EmpresaMadre(String nombre) {
        super(nombre);
    }

    public boolean agregaFilial(Empresa filial) {
        return filiales.add(filial);
    }

    public double calculaCosteMantenimiento() {
        double coste = 0.0;
        for (Empresa filial : filiales) {
            coste = coste + filial.calculaCosteMantenimiento();
        }
        return coste + (nVehiculos * costeUnitarioVehiculo);
    }
}