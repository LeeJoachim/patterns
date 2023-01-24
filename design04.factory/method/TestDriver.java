package design04.factory.method;

class Pizza {

    String name = "";
    String sauce = "";

    void prepare() {}
}

class NYStyleCheesePizza extends Pizza {
    NYStyleCheesePizza() {
        name = "NYStyleCheesePizza";
        sauce = "NYStyleSauce";
    }
}

abstract class PizzaStore {

    Pizza orderPizza(String s) {
        Pizza pizza = createPizza(s);
        pizza.prepare();
        
        return pizza;
    }
    
    abstract Pizza createPizza(String s);

}

class NYPizzaStore extends PizzaStore {
    
    @Override
    Pizza createPizza(String s) {
        if (s.equals("cheese")) {
            return new NYStyleCheesePizza();
        } else return null;
    }
}

class TestDriver {
    public static void main(String[] args) {

        PizzaStore nyStore = new NYPizzaStore(); // upcasting
        Pizza pizza = nyStore.orderPizza("cheese"); 
        
        pizza.prepare();
        System.out.println(pizza.name);
    }
}