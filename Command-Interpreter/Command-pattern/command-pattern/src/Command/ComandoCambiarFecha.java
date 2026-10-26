package Command;

public class ComandoCambiarFecha implements ComandoDeshacer {
    private Reunion reunion; //referencia al receptor
    private FechaHora nuevaFecha; //cuando el usuario la cambie
    private FechaHora antiguaFecha; //guardar´a la antigua fecha
    public Reunion getReunion() {
        return this.reunion;
    }
    public void setReunion(Reunion reunion) {
        this.reunion = reunion;
    }
    //Cambia la fecha de la reuni´on por otra introducida por el usuario y
//almacena el valor anterior de la reuni´on por si luego se quiere recuperar
    public void ejecutar(String param) {
        antiguaFecha = reunion.getFecha();
        nuevaFecha = new FechaHora(param);
        reunion.setFecha(nuevaFecha);
    }
    //recupera el valor de la Reunion anterior al introducido por el usuario
    public void deshacer() {
        reunion.setFecha(antiguaFecha);
    }
    //recupera el valor introducido por el usuario despu´es de que se
//hubiera deshecho
    public void rehacer() {
        reunion.setFecha(nuevaFecha);
    }
}