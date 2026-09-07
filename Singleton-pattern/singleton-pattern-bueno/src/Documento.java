public abstract class Documento
        implements Cloneable
{
    protected String contenido = new String();

    public Documento duplica()
    {
        Documento resultado;

        try
        {
            resultado = (Documento)this.clone();
        }
        catch (CloneNotSupportedException exception)
        {
            return null;
        }
        return resultado;
    }

    public void rellena(String informacion)
    {
        contenido = informacion;
    }

    public String getContenido() {
        return contenido;
    }

    public abstract void imprime();
    public abstract void visualiza();
}
