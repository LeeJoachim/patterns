// import java.rmi.RemoteException;
// import java.rmi.server.UnicastRemoteObject;
import java.rmi.*;
import java.rmi.server.*;

public class Machine 
    extends UnicastRemoteObject implements MachineRemote {

    private static final long serialVersionUID = 2L; // implements Serializable, you need to this data member

    State soldOutState;
    State noCoinState;
    State hasCoinState;
    State soldState;

    State state = soldOutState;
    int count = 0;
    String location;

    public Machine(String location, int numberOfBalls) throws RemoteException {

        soldOutState = new SoldOutState(this);
        noCoinState = new NoCoinState(this);
        hasCoinState = new HasCoinState(this);
        soldState = new SoldState(this);

        this.location = location;
        this.count = numberOfBalls;
        if (numberOfBalls > 0) {
            state = noCoinState; // init : default value
        } else {
            state = soldOutState; // init : default value
        }
    }

    public void insertCoin() {
        state.insertCoin();
    }
    public void ejectCoin() {
        state.ejectCoin();
    }
    public void turnCrank() {
        state.trunCrank();
        state.dispense();
    }

    void releaseBall() {
        System.out.println("please take the ball");
        if (count > 0) {
            count = count - 1;
        }
    }

    public void refill(int count) {
        this.count = this.count + count;
        System.out.println("was refilled; its new count is: " + this.count);
        state.refill();
    }

    // setter functions
    public void setState(State state) {
        this.state = state;
    }

    // getter functions
    public int getCount() {
        return count;
    }
    public State getHasCoinState() {
        return hasCoinState;
    }
    public State getNoCoinState() {
        return noCoinState;
    }
    public State getSoldOutState() {
        return soldOutState;
    }
    public State getSoldState() {
        return soldState;
    }
    public State getState() {
        return state;
    }
    public String getLocation() {
        return location;
    }

    @Override
    public String toString() {
        StringBuffer result = new StringBuffer();
        result.append("\nInventory: " + count + " ball(s)\n");
        result.append("Machine is " + state + "\n");

        return result.toString();
    }
}