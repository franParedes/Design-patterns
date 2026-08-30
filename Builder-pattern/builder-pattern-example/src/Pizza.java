public class Pizza {
    private String nombre ;
    private String masa ;
    private String salsa ;
    private String ingredientes ;
    public Pizza () {
    }
    public String getNombre () {
        return nombre ;
    }
    public void setNombre ( String nombre ) {
        this . nombre = nombre ;
    }
    public String getMasa () {
        return masa ;
    }
    public void setMasa ( String masa ) {
        this . masa = masa ;
    }
    public String getSalsa () {
        return salsa ;
    }
    public void setSalsa ( String salsa ) {
        this . salsa = salsa ;
    }
    public String getIngredientes () {
        return ingredientes ;
    }
    public void setIngredientes ( String ingredientes ) {
        this . ingredientes = ingredientes ;
    }
    @Override
    public String toString () {
        return " Pizza : " + this . getNombre () + " - " +
                this . getMasa () + " - " + this . getSalsa () + "- " + this . getIngredientes () ;
    }
}
