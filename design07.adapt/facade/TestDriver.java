package design07.adapt.facade;

class Amplifier {

}
class Tuner {

}
class StreamingPlayer {

}
class Projector {

}
class TheaterLights {

}
class Screen {

}
class PopcornPopper {
    void on() {
        System.out.println("pop On!");

    }
    void off() {
        System.out.println("pop Off!");

    }
}

/* law of Demeter : Principle of Least Knowledge
 * many functions do not complicate system. but the amount increase
 * more access functions e.g. getter setter ...
 * as a maintenance measure, the coupling becomes loose 
 * so it is better to apply the law.
 * 
 * don't worry about the performance degradation.
 * 
 * e.g.
 * 
 * public float getTemp() { // this is not 'law of demeter'
 *     Thermometer thermometer = station.getThermometer();
 *     return thermometer.getTemperature();
 * } // it just wrote it down by dividing it twice.
 * 
 */
class HomeTheaterFacade {

    Amplifier amplifier;
    Tuner tuner;
    StreamingPlayer streamingPlayer;
    Projector projector;
    TheaterLights theaterLights;
    Screen screen;
    PopcornPopper popcornPopper;

    HomeTheaterFacade(Amplifier amplifier,
                        Tuner tuner,
                        StreamingPlayer streamingPlayer,
                        Projector projector,
                        TheaterLights theaterLights,
                        Screen screen,
                        PopcornPopper popcornPopper) {

        this.amplifier = amplifier;
        this.tuner = tuner;
        this.streamingPlayer = streamingPlayer;
        this.projector = projector;
        this.theaterLights = theaterLights;
        this.screen = screen;
        this.popcornPopper = popcornPopper;
    }

    void watchMovie() {
        System.out.println("Start!");
        this.popcornPopper.on();
        // Add 'function call' and set your routine
    }
    void endMovie() {
        System.out.println("End!");
        this.popcornPopper.off();
        // Add 'function call' and set your routine
    }
}

class TestDriver {
    public static void main(String[] args) {

        /* These instantiations are used for testing */
        /* In normal cases, take over the reference. */
        Amplifier amplifier = new Amplifier();
        Tuner tuner = new Tuner();
        StreamingPlayer streamingPlayer = new StreamingPlayer();
        Projector projector = new Projector();
        TheaterLights theaterLights = new TheaterLights();
        Screen screen = new Screen();
        PopcornPopper popcornPopper = new PopcornPopper();
        /**/ 


        HomeTheaterFacade facade = new HomeTheaterFacade(amplifier, 
                                                            tuner,
                                                            streamingPlayer,
                                                            projector,
                                                            theaterLights,
                                                            screen,
                                                            popcornPopper);
        
        /* function call or request or message sending */
        facade.watchMovie();
        facade.endMovie();
    }
}