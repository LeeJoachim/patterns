package design02.observer;

import java.util.ArrayList;

/* for loosely coupling the data occurrence and data using */
/* need to collection obj */
/* e.g. event handler, listener... */

/** in the swing library, it is called listener */
class Observer {

    private Subject subject;
    private int temperature;

    public Observer(Subject subject) {
        this.subject = subject;
        temperature = 0;
    }
    public int getTemperature() {
        return temperature;
    }
    public void updateDataMembers() {
        this.temperature = subject.getTemperature();
    }
}


class Subject {

    private ArrayList<Observer> observers;
    private int temperature;

    public Subject() {
        observers = new ArrayList<Observer>();
        temperature = 0;
    }
    public void registerObserver(Observer o) {
        observers.add(o);
    }
    public void removeObserver(Observer o) {
        observers.remove(o);
    }
    public void updateObserver() {
        for (Observer o : observers) {
            o.updateDataMembers();
        }
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }
    public int getTemperature() {
        return temperature;
    }
}

class TestDriver {
    public static void main(String[] args) {
        Subject subject = new Subject();
        Observer observer = new Observer(subject);
    }
}