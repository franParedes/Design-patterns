public abstract class PizzaBuilder {
    protected Pizza pizza ;
    public Pizza getPizza () {
        return pizza ;
    }
    public void crearNuevaPizza () {
        pizza = new Pizza () ;
    }
    public abstract void ponerNombre () ;
    public abstract void crearMasa () ;
    public abstract void crearSalsa () ;
    public abstract void crearIngredientes () ;
}
