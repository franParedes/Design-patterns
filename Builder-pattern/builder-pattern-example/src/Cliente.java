public static void main(String[] args) {
    Pizza pizza;
    Cocinero cocinero = new Cocinero();

    // Instanciamos los diferentes constructores (Builders)
    PizzaBuilder bpb = new BarbacoaPizzaBuilder();
    PizzaBuilder cpb = new CarbonaraPizzaBuilder();
    PizzaBuilder hpb = new HawaianaPizzaBuilder(); // <--- Nuestro nuevo builder

    // 1. Preparamos la de Barbacoa
    cocinero.setPizzaBuilder(bpb);
    cocinero.crearPizza();
    pizza = cocinero.getPizza();
    System.out.println(pizza.toString());

    // 2. Preparamos la Carbonara
    cocinero.setPizzaBuilder(cpb);
    cocinero.crearPizza();
    pizza = cocinero.getPizza();
    System.out.println(pizza.toString());

    // 3. Preparamos la Hawaiana (Con piña)
    cocinero.setPizzaBuilder(hpb);
    cocinero.crearPizza();
    pizza = cocinero.getPizza();
    System.out.println(pizza.toString());
}