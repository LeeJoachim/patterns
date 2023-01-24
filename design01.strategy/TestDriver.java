package design01.strategy;

/* service classes */
/* that must have only one function */
interface FlyBehavior {
    void fly();
}
class FlyNoWay implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("Fly No Way!");
    }
}
class FlyWithWings implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("I can fly!");
    }
}
/**/

abstract class Duck {
    
    /* service field */
    FlyBehavior flyBehavior; // aggregation 
    /**/

    public void setFlyBehavior(FlyBehavior flyBehavior) {
        this.flyBehavior = flyBehavior;
    }

    abstract void performFly();
}

class RubberDuck extends Duck {

    public RubberDuck() { // service instaintiation
        //super.flyBehavior = () -> System.out.println("Fly No Way!");
        super.flyBehavior = new FlyNoWay();
    }
    @Override
    public void performFly() {
        super.flyBehavior.fly();
    }
}




class TestDriver {
    public static void main(String[] args) {

        Duck rubberDuck = new RubberDuck();
        rubberDuck.performFly();
        // rubberDuck.setFlyBehavior(() -> System.out.println("I can fly!"));
        rubberDuck.setFlyBehavior(new FlyWithWings());
        rubberDuck.performFly();
    }
}