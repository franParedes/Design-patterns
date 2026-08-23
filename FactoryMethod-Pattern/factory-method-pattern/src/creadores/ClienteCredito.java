package creadores;

import modelos.Pedido;
import modelos.PedidoCredito;

public class ClienteCredito extends Cliente {
    protected Pedido creaPedido(double importe)
    {
        return new PedidoCredito(importe);
    }
}
