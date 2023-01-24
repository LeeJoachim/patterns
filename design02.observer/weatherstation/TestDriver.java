package design02.observer.weatherstation;

import java.util.ArrayList;
import java.util.List;

interface Subject {
    void registerObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers();

}

class WeatherData implements Subject {

    List<Observer> observers;
    double temperature;
    
    public WeatherData() {
        observers = new ArrayList<Observer>();
    }

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);        
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer i : observers) {
            i.update(temperature); // data value sending, i.e. push way 
            // i.update(this) // address value sending, i.e. pull way
        }
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
        notifyObservers();
    }
}

interface Observer {
    void update(double temperature);
    double getTemperature();
}

class CurrentConditionDisplay implements Observer {
    double temperature;

    @Override
    public void update(double temperature) {
        this.temperature = temperature;
    }
    public double getTemperature() {
        return temperature;
    }
    
}

class TestDriver {
    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();
        Observer o = new CurrentConditionDisplay();
        weatherData.registerObserver(o);
        System.out.println(o.getTemperature());
        weatherData.setTemperature(10);
        System.out.println(o.getTemperature());
    }
}
