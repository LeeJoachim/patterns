package design5.singleton.reservedsynchronized;

/* problems when multi-thread and if() syntax
 * if() : the gate keeper just one checking when flow enter
 * so some problem is caused 
 * if flow's state is changed in block and he can't be any longer
 * flow is not killed by gate keeper 
 * flow just goes to the next step
 */

class Singleton {

    private volatile static Singleton uniqueInstance; // NOTE: volatile!

    private Singleton() {}

    /* critical section */
    /* mutual exclusion(mutex) or semaphore */
    static synchronized Singleton getInstance() { // synchronized : thread safe!
                                                  // 100x performance degradation
        if (uniqueInstance == null) {
            return new Singleton();
        }
        return uniqueInstance;
    }
    /**/
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