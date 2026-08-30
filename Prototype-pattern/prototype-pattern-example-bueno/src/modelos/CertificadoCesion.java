package modelos;

public class CertificadoCesion extends Documento {
    public void visualiza()
    {
        System.out.println(
                "Muestra el certificado de cesion: " + contenido);
    }

    public void imprime()
    {
        System.out.println(
                "Imprime el certificado de cesion: " + contenido);
    }
}
