public class FachadaCajero {
    private ValidacionUsuario val = new ValidacionUsuario();
    private RetirarDinero ret = new RetirarDinero();
    private RecargaMovil rec = new RecargaMovil();

    // Nuevos subsistemas
    private Prestamo pres = new Prestamo();
    private RetiroTarjeta retTarj = new RetiroTarjeta();
    private Deposito dep = new Deposito();

    public String sacarDinero() {
        return val.valida() + ret.retirar();
    }

    public String recargaMovil() {
        return val.valida() + rec.recarga();
    }

    // Nuevos métodos expuestos por la fachada
    public String pedirPrestamo() {
        return val.valida() + pres.solicitar();
    }

    public String retiroDeTarjeta() {
        return val.valida() + retTarj.retirarConTarjeta();
    }

    public String realizarDeposito() {
        return val.valida() + dep.depositar();
    }
}