import java . util .*;

public class Persona {
    private String dni ;
    private String nombre ;
    private Fecha fechaNac ;
    private String direccion ;
    private ArrayList<String> tfnos ;
    private String sexo;
    private String estadoCivil;

    // constructor
    public Persona ( String p_dni , String p_nombre , Fecha
            p_fecnac , String p_direccion , ArrayList p_tfnos, String p_sexo, String p_estadoCivil ) {
        dni = p_dni ;
        nombre = p_nombre ;
        fechaNac = p_fecnac ;
        direccion = p_direccion ;
        tfnos = p_tfnos ;
        sexo = p_sexo;
        estadoCivil = p_estadoCivil;
    }
    // constructor de copia
    public Persona ( Persona otra ) {
        dni = otra.getDni () ;
        nombre = otra.getNombre () ;
        fechaNac = new
                Fecha ( otra.getFechaNac().getDia (),
                otra.getFechaNac().getMes(),
                otra.getFechaNac().getAnyo() ) ; // copia profunda
                direccion = otra . getDireccion () ;
        tfnos = new ArrayList ( otra . getTfnos () ) ; // copia profunda

        sexo = otra.getSexo();
        estadoCivil = otra.getEstadoCivil();
    }
    // metodos
    public String getDni () {
        return this . dni ;
    }
    public void setDni ( String dni ) {
        this . dni = dni ;
    }
    public String getNombre () {
        return this . nombre ;
    }
    public void setNombre ( String nombre ) {
        this . nombre = nombre ;
    }
    public Fecha getFechaNac () {
        return this . fechaNac ;
    }
    public void setFechaNac ( Fecha fechaNac ) {
        this . fechaNac = fechaNac ;
    }
    public String getDireccion () {
        return this . direccion ;
    }
    public void setDireccion ( String direccion ) {
        this . direccion = direccion ;
    }
    public ArrayList getTfnos () {
        return this . tfnos ;
    }
    public void setTfnos ( ArrayList tfnos ) {
        this . tfnos = tfnos ;
    }

    public String getSexo() { return sexo; }
    public void setSexo(String sexo) { this.sexo = sexo; }
    public String getEstadoCivil() { return estadoCivil; }
    public void setEstadoCivil(String estadoCivil) { this.estadoCivil = estadoCivil; }

    public String toString() {
        return dni + " " + nombre + " (" + sexo + " - " + estadoCivil + ") # codigo : " + this.hashCode();
    }
}