package creadores;

import modelos.Pedido;
import java.util.ArrayList;
import java.util.List;

public abstract class Cliente {
    protected List<Pedido> pedidos = new ArrayList<>();

    // Este es el Factory Method puro
    protected abstract Pedido creaPedido(double importe);

    // Ajustamos a boolean para que la UI sepa qué pasó
    public boolean nuevoPedido(double importe) {
        Pedido pedido = this.creaPedido(importe);
        if (pedido.valida()) {
            pedido.paga();
            pedidos.add(pedido);
            return true; // Es válido
        }
        return false; // No es válido
    }
}