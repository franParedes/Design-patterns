package modelos;

public class Comercial {
    protected String nombre;
    protected String direccion;
    protected String email;

    private static Comercial _instance = null;

    private Comercial(){}

    public static Comercial Instance() {
        if (_instance == null) {
            _instance = new Comercial();
        }
        return _instance;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}