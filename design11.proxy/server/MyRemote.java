import java.rmi.*;

public interface MyRemote extends Remote { // inheritance Remote

    public String sayHello() throws RemoteException; // every function must throw RemoteException
                                                     // return type must 'implements serializable' if you make your class, be noticed!
}