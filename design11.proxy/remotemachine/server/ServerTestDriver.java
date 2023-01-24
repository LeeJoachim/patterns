import java.rmi.*;

public class ServerTestDriver {
    public static void main(String[] args) {
        MachineRemote machine = null;
        int count;

        if (args.length < 2) { // defensive programming
            System.out.println("Machine <name> <inventory>"); // inform message
            System.exit(1);
        }

        try {
           count = Integer.parseInt(args[1]);

           machine = new Machine(args[0], count);
           Naming.rebind(args[0], machine);
           
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}