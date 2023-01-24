package design02.observer.library;

import java.util.Observable;
import java.util.Observer;

class WeatherData extends Observable {
    double temperature;
    
    public WeatherData() {
    }
    public void setTemperature(double temperature) {
        this.temperature = temperature;
        setChanged(); // flag!
        notifyObservers();
    }
    public double getTemperature() {
        return temperature;
    }
}

class CurrentConditionDisplay implements Observer {
    double temperature;
    WeatherData weatherData;

    public CurrentConditionDisplay(WeatherData weatherData) {
        this.weatherData = weatherData;
        this.weatherData.addObserver(this);
    }
    @Override
    public void update(Observable o, Object arg) {

        /* dangerous : down casting */
        /* RTTI : Run Time Type Identification */
        if (o instanceof WeatherData) {
            this.weatherData = (WeatherData)o;
            this.temperature = weatherData.getTemperature();
        }
        /**/
    }
    public double getTemperature() {
        return temperature;
    }
}

class Display implements Observer {

    WeatherData weatherData;

    public Display(WeatherData weatherData) {
        this.weatherData = weatherData;
        this.weatherData.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof WeatherData) { // RTTI
            this.weatherData = (WeatherData)o;
            System.out.println(weatherData.getTemperature());
        }
    }
}

class TestDriver {
    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();
        CurrentConditionDisplay x = new CurrentConditionDisplay(weatherData);
        Display y = new Display(weatherData);

        weatherData.setTemperature(10);
        weatherData.setTemperature(20);
        weatherData.setTemperature(30);
    }
}