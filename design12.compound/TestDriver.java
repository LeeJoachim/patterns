import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/*
 * QuackObservable <|.. Obsersvable
 * QuackObservable <|-- Quackable <|.. MallardDuck, RubberDuck
 * Observer <|.. Quackologist
 */

interface QuackObservable {
    void registerObserver(Observer o);
    void notifyObservers();
}

class Observable implements QuackObservable {

    List<Observer> observerList = new ArrayList<Observer>();
    QuackObservable duck;

    Observable(QuackObservable duck) {
        this.duck = duck;
    }

    @Override
    public void registerObserver(Observer o) {
        observerList.add(o);
    }

    @Override
    public void notifyObservers() {
        Iterator<Observer> iterator = observerList.iterator();
        
        while(iterator.hasNext()) {
            Observer o = iterator.next();
            o.update(duck);
        }
        
    }
    
}

interface Observer {
    void update(QuackObservable duck);
}

class Quackologist implements Observer {

    @Override
    public void update(QuackObservable duck) {
        System.out.println("Quackologist: " + duck + " call!");
        
    }    
}


interface AbstractDuckFactory {
    Quackable createMallardDuck();
    Quackable createRubberDuck();
}

class DuckFactory implements AbstractDuckFactory {
    public Quackable createMallardDuck() {
        return new MallardDuck();
    }
    public Quackable createRubberDuck() {
        return new RubberDuck();
    }
}

class CountingDuckFactory implements AbstractDuckFactory {
    public Quackable createMallardDuck() {
        return new QuackCounter(new MallardDuck());
    }
    public Quackable createRubberDuck() {
        return new QuackCounter(new RubberDuck());
    }
}

// 
interface Quackable extends QuackObservable {
    void quack();
}

class MallardDuck implements Quackable {
    public void quack() {
        System.out.println("Mallard Quack!");
    }

    @Override
    public void registerObserver(Observer o) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void notifyObservers() {
        // TODO Auto-generated method stub
        
    }
}

class RubberDuck implements Quackable {
    public void quack() {
        System.out.println("Rubber Quack!");
    }

    @Override
    public void registerObserver(Observer o) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void notifyObservers() {
        // TODO Auto-generated method stub
        
    }
}

class Goose {
    public void honk() {
        System.out.println("Goose honk!");
    }
}

class GooseAdapter implements Quackable{

    Goose goose;
    
    GooseAdapter(Goose goose) {
        this.goose = goose;
    }
    public void quack() {
        goose.honk();
    }
    @Override
    public void registerObserver(Observer o) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void notifyObservers() {
        // TODO Auto-generated method stub
        
    }
}

// 
class QuackCounter implements Quackable {
    Quackable duck;
    static int numberOfQuacks;

    QuackCounter(Quackable q) {
        this.duck = q;
    }

    public void quack() {
        duck.quack();
        numberOfQuacks++;
    }

    public static int getNumberOfQuacks() {
        return numberOfQuacks;
    }

    @Override
    public void registerObserver(Observer o) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void notifyObservers() {
        // TODO Auto-generated method stub
        
    }
}
// 

class DuckList implements Quackable {

    List<Quackable> duckList = new ArrayList<Quackable>();

    public void add(Quackable duck) {
        duckList.add(duck);
    }

    @Override
    public void quack() {
        Iterator<Quackable> iterator = duckList.iterator();
        while (iterator.hasNext()) {
            Quackable q = iterator.next();
            q.quack();
        }
        
    }

    @Override
    public void registerObserver(Observer o) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void notifyObservers() {
        // TODO Auto-generated method stub
        
    }
    
}

class Simulator {

    void simulate(AbstractDuckFactory duckFactory) {

        Quackable mallardOne = duckFactory.createMallardDuck();
        Quackable mallardTwo = duckFactory.createMallardDuck();
        Quackable mallardThree = duckFactory.createMallardDuck();
        Quackable mallardFour = duckFactory.createMallardDuck();

        Quackable rubberOne = duckFactory.createRubberDuck();
        Quackable rubberTwo = duckFactory.createRubberDuck();
        Quackable rubberThree = duckFactory.createRubberDuck();
        Quackable rubberFour = duckFactory.createRubberDuck();

        Quackable gooseAdapter = new GooseAdapter(new Goose());

        DuckList mallardDuckList = new DuckList();
        DuckList rubberDuckList = new DuckList();
        
        mallardDuckList.add(mallardOne);
        mallardDuckList.add(mallardTwo);
        mallardDuckList.add(mallardThree);
        mallardDuckList.add(mallardFour);
        rubberDuckList.add(rubberOne);
        rubberDuckList.add(rubberTwo);
        rubberDuckList.add(rubberThree);
        rubberDuckList.add(rubberFour);

        DuckList duckList = new DuckList();
        duckList.add(mallardDuckList);
        duckList.add(rubberDuckList);

// 

        Quackologist quackologist = new Quackologist();
        duckList.registerObserver(quackologist);



// 
        System.out.println("Duck Simulator Start!\n");       

        simulate(duckList);
        simulate(gooseAdapter);

        System.out.println("Number Of Quacks: " + 
                            QuackCounter.getNumberOfQuacks());
// 
    }

    void simulate(Quackable duck) {
        duck.quack();
    }

}



class TestDriver {
    public static void main(String[] args) {
        Simulator simulator = new Simulator();
        AbstractDuckFactory countingDuckFactory = new CountingDuckFactory();
        simulator.simulate(countingDuckFactory);
    }
}