package Command;

public class ComandoCambiarLocalizacion implements ComandoDeshacer {
    private Reunion reunion; //referencia al receptor
    private String nuevaLocalizacion; //cuando el usuario la cambie
    private String antiguaLocalizacion; //guardar´a la antigua localizaci´on
    public Reunion getReunion() {
        return this.reunion;
    }
    public void setReunion(Reunion reunion) {
        this.reunion = reunion;
    }
    //Cambia el lugar de la reuni´on por otro introducido por el usuario y
//almacena el valor anterior de la reunion por si luego se quiere recuperar
    public void ejecutar(String param) {
        antiguaLocalizacion = reunion.getLocalizacion();
        nuevaLocalizacion = param;
        reunion.setLocalizacion(nuevaLocalizacion);
    }
    //Recupera el valor de la Reuni´on anterior al introducido por el usuario
    public void deshacer() {
        reunion.setLocalizacion(antiguaLocalizacion);
    }

    public void rehacer() {
        reunion.setLocalizacion(nuevaLocalizacion);
    }
}
