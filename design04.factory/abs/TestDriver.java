package design04.factory.abs;

interface Cheese {
    public String toString();
}
class MozzarellaCheese implements Cheese {
    public String toString() {
        return "Mozza";
    }
}

interface PizzaIngredientFactory {
    Cheese createCheese();
}
class NYPizzaIngredientFactory implements PizzaIngredientFactory{
    public Cheese createCheese() {
        return new MozzarellaCheese();
    }
}

abstract class Pizza {
    String name;
    Cheese cheese;

    public void setName(String name) {
        this.name = name;
    }
    abstract void prepare();
}

class CheesePizza extends Pizza {
    PizzaIngredientFactory factory;

    CheesePizza(PizzaIngredientFactory factory) {
        this.factory = factory;
    }
    void prepare() {
        System.out.println("preparing " + name);
        cheese = factory.createCheese();
    }
}


abstract class PizzaStore {

    abstract Pizza createPizza(String s);

    Pizza orderPizza(String s) {
        Pizza pizza = createPizza(s);
        pizza.prepare();
        return pizza;
    }
}

class NYPizzaStore extends PizzaStore {

    Pizza createPizza(String s) {

        Pizza pizza = null;
        PizzaIngredientFactory ingredientFactory = new NYPizzaIngredientFactory();

        if (s.equals("cheese")) {
            
            pizza = new CheesePizza(ingredientFactory);
            pizza.setName("New York Style Cheese Pizza");
        }
        return pizza;
    }
}


class TestDriver {
    public static void main(String[] args) {

        PizzaStore nyStore = new NYPizzaStore();
        Pizza pizza = nyStore.orderPizza("cheese");
        System.out.println(pizza.name);
        System.out.println(pizza.cheese.toString());
        // System.out.println(pizza.cheese);
    }
}