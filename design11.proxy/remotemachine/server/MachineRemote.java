import java.rmi.*;

public interface MachineRemote extends Remote {
    public int getCount() throws RemoteException;
    public String getLocation() throws RemoteException;
    public State getState() throws RemoteException; // every return type must primitive or serializable
}