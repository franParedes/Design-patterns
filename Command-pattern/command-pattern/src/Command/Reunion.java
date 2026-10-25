package Command;

public class Reunion {
    private int id;                 // id en la BD (0 = aún no guardada)
    private String motivo;
    private String participantes;
    private String localizacion;
    private FechaHora fecha;

    public Reunion(String motivo, String participantes, String localizacion, FechaHora fecha) {
        this.motivo = motivo;
        this.participantes = participantes;
        this.localizacion = localizacion;
        this.fecha = fecha;
    }
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getMotivo() { return motivo; }
    public void setMotivo(String nuevo) { this.motivo = nuevo; }
    public String getParticipantes() { return participantes; }
    public void setParticipantes(String nuevos) { this.participantes = nuevos; }
    public String getLocalizacion() { return localizacion; }
    public void setLocalizacion(String nueva) { this.localizacion = nueva; }
    public FechaHora getFecha() { return fecha; }
    public void setFecha(FechaHora nueva) { this.fecha = nueva; }

    @Override
    public String toString() {
        return "\n#### Reunion #### \n# " + motivo + " \n# " + participantes
                + " \n# " + localizacion + " \n# " + fecha.toString();
    }
}