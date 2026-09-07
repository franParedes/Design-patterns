import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.*;
import java.net.InetSocketAddress;
import java.sql.*;

public class ServidorAPI {
    // Configura tu usuario y contraseña de MySQL
    private static final String URL = "jdbc:mysql://localhost:3306/sistema_singleton_js";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    public static void main(String[] args) throws Exception {
        // Levantar servidor en el puerto 5000
        HttpServer server = HttpServer.create(new InetSocketAddress(5000), 0);
        server.createContext("/api/muro", new MuroHandler());
        server.setExecutor(null);
        server.start();
        System.out.println("Servidor Java iniciado en http://localhost:5000/api/muro");
    }

    static class MuroHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            // Habilitar CORS para que el navegador permita la conexión
            exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
            exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
            exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type");

            if ("OPTIONS".equals(exchange.getRequestMethod())) {
                exchange.sendResponseHeaders(204, -1);
                return;
            }

            if ("GET".equals(exchange.getRequestMethod())) {
                String json = obtenerMuro();
                exchange.sendResponseHeaders(200, json.getBytes().length);
                OutputStream os = exchange.getResponseBody();
                os.write(json.getBytes());
                os.close();
            } else if ("POST".equals(exchange.getRequestMethod())) {
                // Leer el JSON enviado desde JavaScript
                InputStream is = exchange.getRequestBody();
                String body = new String(is.readAllBytes());

                try {
                    // Extracción manual básica del JSON para no usar librerías externas
                    String alturaStr = body.split("\"altura\":")[1].split(",")[0].trim();
                    String comandante = body.split("\"comandante\":\"")[1].split("\"")[0].trim();

                    guardarMuro(Integer.parseInt(alturaStr), comandante);

                    String respuesta = "{\"status\":\"ok\"}";
                    exchange.sendResponseHeaders(200, respuesta.getBytes().length);
                    OutputStream os = exchange.getResponseBody();
                    os.write(respuesta.getBytes());
                    os.close();
                } catch (Exception e) {
                    exchange.sendResponseHeaders(400, -1);
                }
            }
        }
    }

    private static void guardarMuro(int altura, String comandante) {
        // El REPLACE funciona como un INSERT, pero si el id=1 ya existe, lo actualiza (Comportamiento Singleton)
        String sql = "REPLACE INTO muros (id, altura, comandante) VALUES (1, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, altura);
            pstmt.setString(2, comandante);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error en BD: " + e.getMessage());
        }
    }

    private static String obtenerMuro() {
        String sql = "SELECT altura, comandante FROM muros WHERE id = 1";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return "{\"altura\":" + rs.getInt("altura") + ",\"comandante\":\"" + rs.getString("comandante") + "\"}";
            }
        } catch (SQLException e) {
            System.err.println("Error al leer BD: " + e.getMessage());
        }
        return "{}";
    }
}