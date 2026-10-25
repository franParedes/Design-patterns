package Proxy;

public class ProxySeguridad implements Servidor {
    private Servidor servidorReal;
    private String passwordAcceso;

    public ProxySeguridad(Servidor servidorReal, String passwordAcceso) {
        this.servidorReal = servidorReal;
        this.passwordAcceso = passwordAcceso;
    }

    public String subir(String nomFich) {
        if (autenticar()) {
            return "[Proxy de Seguridad: Acceso Concedido] " + servidorReal.subir(nomFich);
        } else {
            return "[Proxy de Seguridad: Acceso Denegado] Contrasena incorrecta. No se subio el fichero.";
        }
    }

    public Fichero bajar(String id) {
        if (autenticar()) {
            System.out.println("[Proxy de Seguridad: Acceso Concedido para descarga]");
            return servidorReal.bajar(id);
        } else {
            System.out.println("[Proxy de Seguridad: Acceso Denegado] Contrasena incorrecta para descargar.");
            return null;
        }
    }

    // El listado lo dejamos publico como demostracion de control de acceso parcial
    public String listado() {
        return "[Proxy de Seguridad: Listado Publico Solicitado] " + servidorReal.listado();
    }

    // Simulacion de validacion
    private boolean autenticar() {
        // En un caso real, aqui se pediria credenciales o se validaria un token.
        // Para la prueba, simulamos que la contrasena correcta es "admin123"
        return this.passwordAcceso.equals("admin123");
    }
}