import java.util.*;

public class DocumentacionEnBlanco extends Documentacion {
    private static DocumentacionEnBlanco _instance = null;

    private DocumentacionEnBlanco()
    {
        documentos = new ArrayList<Documento>();
    }

    public static DocumentacionEnBlanco Instance()
    {
        if (_instance == null)
            _instance = new DocumentacionEnBlanco();
        return _instance;
    }

    DocumentacionEnBlanco documentacionEnBlanco =
            DocumentacionEnBlanco.Instance();
    List<Documento> documentosEnBlanco =
            documentacionEnBlanco.getDocumentos();

}
