package memento;

public class Recuerdo {
    private int id;                 // id en la BD (0 = aún no guardado)
    private Partida partida = null;

    public Recuerdo(Partida partida) {
        this.partida = partida;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Partida getPartida() {
        return partida;
    }
    public void setPartida(Partida partida) {
        this.partida = partida;
    }
}