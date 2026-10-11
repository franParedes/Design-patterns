package modelos;

public class ModeloDecorador extends Decorador {
    public ModeloDecorador(ComponenteGraficoVehiculo componente) {
        super(componente);
    }
    protected String visualizaInformacionTecnica() {
        return " | + Información técnica del modelo";
    }
    public String visualiza() {
        return super.visualiza() + visualizaInformacionTecnica();
    }
}
