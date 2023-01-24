package design04.factory.simple;

/* pactory creates obj */

class Pizza {
    String name;
    int cost;

    void prepare() {
        System.out.println("is prepared");
    }
}

class CheesePizza extends Pizza{
    CheesePizza() {
        name = "CheesePizza";
        cost = 10000;
    }
}

class PizzaSotre {

    PizzaFactory factory;

    PizzaSotre(PizzaFactory factory) {
        this.factory = factory;
    }
    Pizza orderPizza(String s) {

        Pizza pizza = factory.createPizza(s);
        pizza.prepare();
        return pizza;
    }
}

class PizzaFactory {

    Pizza createPizza(String s) {
        Pizza pizza = null;

        if (s.equals("cheese")) {
            pizza = new CheesePizza();
        } else {
            // instantiation
        }
        return pizza;
    }
}

class TestDriver {
    public static void main(String[] args) {
        PizzaFactory factory = new PizzaFactory();
        PizzaSotre store = new PizzaSotre(factory);
        Pizza pizza = store.orderPizza("cheese");

        System.out.println(pizza.cost);
    }
}