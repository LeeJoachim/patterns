import java.rmi.*;
import java.rmi.server.*;

public class MyRemoteImpl extends UnicastRemoteObject implements MyRemote {
    
    private static final long serialVersionUID = 1L; // Because UnicastRemoteObject implements Serializable, you need to this data member

    public String sayHello() {
        return "Server says, 'Hey'";
    }

    public MyRemoteImpl() throws RemoteException { }
}
