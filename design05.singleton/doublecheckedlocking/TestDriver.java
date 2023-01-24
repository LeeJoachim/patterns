package design05.singleton.doublecheckedlocking;

class Singleton {
    /* volatile modifier
     * To compiler : "don't optimization"
     * meaning : volatile == memory
     * volatile <-> register : do optimization
     * 
     * optimize mechanism is similar that 
     * delivery company optimization
     * 
     * we don't know where it is interrupted by os
     * and optimize mechanism is a possibility
     * that the data will be lost.
     */
    private volatile static Singleton uniqueInstance; // NOTE: volatile!

    private Singleton() {}

    static Singleton getInstance() { 
        if (uniqueInstance == null) {
            synchronized (Singleton.class) { // thread safe!, 100x performance degradation
                if (uniqueInstance == null) {
                    return new Singleton();
                }
            }
        }
        return uniqueInstance;
    }

    public String getDescription() { // other useful methods here
        return "I'm a Singleton!";
    }
}

class TestDriver {
    public static void main(String[] args) {
        Singleton singleton = Singleton.getInstance();
        System.out.println(singleton.getDescription());
    }
}