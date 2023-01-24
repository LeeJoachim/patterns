import java.rmi.*;

class Monitor {
    MachineRemote machine;

    Monitor(MachineRemote machine) { // upcasting
        this.machine = machine;
    }

    void report() {
        try {
            System.out.println("current location: " + machine.getLocation());
            System.out.println("current ball(s): " + machine.getCount());
            System.out.println("current state: " + machine.getState());
            
        } catch (Exception e) {
            e.printStackTrace();
        }
                
    }
}