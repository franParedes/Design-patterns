package modelos;

public class MarcaDecorador extends Decorador {
    public MarcaDecorador(ComponenteGraficoVehiculo componente) {
        super(componente);
    }
    protected String visualizaLogo() {
        return " | + Logotipo de la marca";
    }
    public String visualiza() {
        return super.visualiza() + visualizaLogo();
    }
}