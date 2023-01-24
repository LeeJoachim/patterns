import java.rmi.*;

class MonitorTestDriver {
    public static void main(String[] args) {
        String[] location = {
            "rmi://localhost/seoul",
            "rmi://localhost/busan"
        };
        Monitor[] monitor = new Monitor[location.length];

        for (int i = 0; i < location.length; i++) {
            
            try {
                MachineRemote machine = 
                    (MachineRemote) Naming.lookup(location[i]);
                
                monitor[i] = new Monitor(machine);
                System.out.println(monitor[i]);
                
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        }

        for (int i = 0; i < monitor.length; i++) {
            monitor[i].report();
        }

    }
}