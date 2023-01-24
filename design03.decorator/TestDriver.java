package design03.decorator;

/* to add functions to a class without touching it */
/* class is wrapped by other class */
abstract class Beverage {
    protected String description = "null";

    public String getDescription() {
        return description;
    }
    public abstract double cost();
}

        class Espresso extends Beverage {

            Espresso() {
                description = "Espresso";
            }
            @Override
            public double cost() {
                return 1.99;
            }
        }

        abstract class CondimentDecorator extends Beverage {
            protected Beverage beverage;
            public abstract String getDescription();
        }

                class Mocha extends CondimentDecorator {
                    public Mocha(Beverage beverage) {
                        this.beverage = beverage;
                    }
                    public String getDescription() {
                        return beverage.getDescription() + ", Mocha";
                    }
                    public double cost() {
                        return beverage.cost() + .20;
                    }
                }



class TestDriver {
    public static void main(String[] args) {
        Beverage beverage = new Espresso();
        beverage = new Mocha(beverage);

        System.out.println(beverage.getDescription());
        System.out.println(beverage.cost());
    }
}