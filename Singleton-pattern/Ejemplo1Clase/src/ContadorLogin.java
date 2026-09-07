import java.util.HashMap;

public class ContadorLogin {

    // Se reemplaza ArrayList por HashMap para guardar el par (usuario, contraseña)
    private HashMap<String, String> usuarios = new HashMap<>();
    private static ContadorLogin instancia;

    // Constructor privado
    private ContadorLogin() {
    }

    public static ContadorLogin getInstancia() {
        if (instancia == null) {
            instancia = new ContadorLogin();
        }
        return instancia;
    }

    // Se actualiza el método para recibir la contraseña
    public boolean devolverEstadoCuenta(String userId, String password) {
        if (usuarios.containsKey(userId)) {
            return true;
        } else {
            usuarios.put(userId, password);
            return false;
        }
    }

    // Método adicional para consultar la contraseña almacenada
    public String obtenerPassword(String userId) {
        return usuarios.get(userId);
    }

    public void borrarLogin(String userId) {
        usuarios.remove(userId);
    }
}