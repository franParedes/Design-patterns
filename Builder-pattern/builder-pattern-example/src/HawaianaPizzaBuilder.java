public class HawaianaPizzaBuilder extends PizzaBuilder {
    @Override
    public void ponerNombre() {
        pizza.setNombre("Hawaiana (Con Piña)");
    }

    @Override
    public void crearMasa() {
        pizza.setMasa("suave"); // Puedes cambiar el tipo de masa si gustas
    }

    @Override
    public void crearSalsa() {
        pizza.setSalsa("tomate");
    }

    @Override
    public void crearIngredientes() {
        pizza.setIngredientes("mozzarella, jamón, piña");
    }
}