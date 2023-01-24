package design05.singleton;

/* in multi thread programming */
/* only one instantiation */

class Singleton {

    private volatile static Singleton uniqueInstance = new Singleton(); // NOTE: volatile

    private Singleton() {}

    static Singleton getInstance() { 
        return uniqueInstance;
    }
    
    // other useful methods here
    public String getDescription() {
        return "I'm a Singleton!";
    }
}

class TestDriver {
    public static void main(String[] args) {
        Singleton singleton = Singleton.getInstance();
        System.out.println(singleton.getDescription());
    }
}