package creadores;

import modelos.Pedido;
import modelos.PedidoContado;

public class ClienteContado extends Cliente {
    protected Pedido creaPedido(double importe)
    {
        return new PedidoContado(importe);
    }
}
