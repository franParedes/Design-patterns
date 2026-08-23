package modelos;

public class PedidoContado extends Pedido {
    public PedidoContado(double importe)
    {
        super(importe);
    }
    public void paga()
    {
        System.out.println(
                "El pago del pedido por importe de: " +
                        importe + " se ha realizado.");
    }

    public boolean valida()
    {
        return true;
    }
}
