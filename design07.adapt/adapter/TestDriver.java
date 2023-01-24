package design07.adapt.adapter;

/* to change class shape or type without touching it */
/* class is wrapped by other class */


interface Duck {
    void quack();
    void fly();
}

class MallardDuck implements Duck {
    @Override
    public void quack() {
        System.out.println("MallardDuck : Quack!");
    }
    @Override
    public void fly() {
        System.out.println("MallardDuck : Fly!");
    }
}

class TurkeyAdapter implements Duck {
    Turkey turkey;

    TurkeyAdapter(Turkey turkey) {
        this.turkey = turkey;
    }

    @Override
    public void quack() {
        turkey.gobble();
    }
    @Override
    public void fly() {
        turkey.fly();
    }
}

interface Turkey {

    void gobble();
    void fly();
}

class WildTurkey implements Turkey {

    @Override
    public void gobble() {
        System.out.println("WildTurkey : Gobble!");
    }
    @Override
    public void fly() {
        System.out.println("WildTurkey : Fly!");
    }

}

class TestDriver {
    public static void main(String[] args) {
        Duck duck = new MallardDuck();
        Turkey turkey = new WildTurkey();

        Duck turkeyAdapter = new TurkeyAdapter(turkey);

        testDuck(duck);
        testDuck(turkeyAdapter);
    }

    static void testDuck(Duck duck) {
        duck.quack();
        duck.fly();
    }
}