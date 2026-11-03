package memento;

public class Originador {
    private Partida partida;
    public Partida getPartida() {
        return this.partida;
    }
    public void setPartida(Partida partida) {
        this.partida = partida;
    }
    public void setRecuerdo(Recuerdo recuerdo) {
        partida = recuerdo.getPartida();
    }
    public Recuerdo crearRecuerdo() {
        return new Recuerdo(partida);
    }
}
