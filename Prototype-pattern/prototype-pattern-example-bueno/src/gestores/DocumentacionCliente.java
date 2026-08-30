package gestores;

import modelos.Documento;

import java.util.*;

public class DocumentacionCliente extends Documentacion {
    public DocumentacionCliente(String informacion) {
        documentos = new ArrayList<>();
        DocumentacionEnBlanco documentacionEnBlanco = DocumentacionEnBlanco.Instance();
        List<Documento> documentosEnBlanco = documentacionEnBlanco.getDocumentos();

        for (Documento documento : documentosEnBlanco) {
            Documento copiaDocumento = documento.duplica();
            copiaDocumento.rellena(informacion); // Personalizamos el clon
            documentos.add(copiaDocumento);
        }
    }

    public void visualiza()
    {
        for (Documento documento: documentos)
            documento.visualiza();
    }

    public void imprime()
    {
        for (Documento documento: documentos)
            documento.imprime();
    }
}
