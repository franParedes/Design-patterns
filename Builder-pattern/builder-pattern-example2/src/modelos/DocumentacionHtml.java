package modelos;

public class DocumentacionHtml extends Documentacion {
    public void agregaDocumento(String documento) {
        if (documento.startsWith("<HTML>")) contenido.add(documento);
    }
    public void imprime() {
        System.out.println("Documentacion HTML\n" + obtenerContenidoCompleto());
    }
}