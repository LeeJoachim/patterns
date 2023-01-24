package design03.decorator.modified;

abstract class Beverage {
    private String description = "null";
    private int cost = 0;

    public void setDescription(String description) {
        this.description = description;
    }
    public void setCost(int cost) {
        this.cost = cost;
    }
    public String getDescription() {
        return description;
    }
    public int getCost() {
        return cost;
    }
}

        class Espresso extends Beverage{
            Espresso() {
                setDescription("Espresso");
                setCost(2000);
            }    
        }

        interface CondimentDecorator { }
        class Mocha extends Beverage implements CondimentDecorator {
            public Mocha(Beverage beverage) {
                setDescription(beverage.getDescription() + ", Mocha");
                setCost(beverage.getCost() + 1000);
            }
        }

class TestDriver {
    public static void main(String[] args) {
        Beverage beverage = new Espresso();
        beverage = new Mocha(beverage);

        System.out.println(beverage.getDescription() + " : " + beverage.getCost());
    }
}