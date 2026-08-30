import java . util .*;

class Fecha {
    // atributos
    private int anyo ;
    private int mes ;
    private int dia ;
    // constructores
    public Fecha (int dia , int mes , int anyo ) {
        this . dia = dia ;
        this . mes = mes ;
        this . anyo = anyo ;
    }
    public Fecha ( String cadenaFecha ) {
        StringTokenizer st = new
                StringTokenizer ( cadenaFecha , "/");
        String dd = st . nextToken () ;
        String mm = st . nextToken () ;
        String aa = st . nextToken () ;
        this . dia = Integer . parseInt ( dd ) ;
        this . mes = Integer . parseInt ( mm ) ;
        this . anyo = Integer . parseInt ( aa ) ;
    }
    public Fecha () {
        Calendar hoy = Calendar . getInstance () ;
        anyo = hoy . get ( Calendar . YEAR ) ;
        mes = hoy . get ( Calendar . MONTH ) + 1;
        dia = hoy . get ( Calendar . DAY_OF_MONTH ) ;
    }
    // metodos
    public String toString () {
        return dia + "/" + mes + "/" + anyo + " # codigo : " + this . hashCode () ;
    }
    public int getAnyo () {
        return this . anyo ;
    }
    public void setAnyo (int anyo ) {
        this . anyo = anyo ;
    }
    public int getDia () {
        return this . dia ;
    }
    public void setDia (int dia ) {
        this . dia = dia ;
    }
    public int getMes () {
        return this . mes ;
    }
    public void setMes (int mes ) {
        this . mes = mes ;
    }
}