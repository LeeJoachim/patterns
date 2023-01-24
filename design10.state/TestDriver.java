package design10.state;

/* software is state-changing machine */
/* constantly changes the states */
/* flickering lights on memory */
interface State {

    public void insertCoin();
    public void ejectCoin();
    public void trunCrank();
    public void dispense();

    public void refill();
}
class SoldOutState implements State {
    Machine machine;

    SoldOutState(Machine machine) {
        this.machine = machine;
    }

    public void insertCoin() {
        System.out.println("Sold Out");
    }
    public void ejectCoin() {
        System.out.println("Sold Out");
        
    }
    public void trunCrank() {
        System.out.println("Sold Out");
        
    }
    public void dispense() {
        System.out.println("Sold Out");

    }

    public void refill() {
        machine.setState(machine.getNoCoinState());
    }

    public String toString() {
        return "sold out";
    }

}
class NoCoinState implements State {
    Machine machine;

    NoCoinState(Machine machine) {
        this.machine = machine;
    }

    public void insertCoin() {
        System.out.println("You inserted a Coin");
        machine.setState(machine.getHasCoinState());
    }
    public void ejectCoin() {
        System.out.println("waiting Coin");
        
    }
    public void trunCrank() {
        System.out.println("waiting Coin");
        
    }
    public void dispense() {
        System.out.println("waiting Coin");

    }

    public void refill() { } // hook function

    public String toString() {
        return "waiting coin";
    }

}
class HasCoinState implements State {
    Machine machine;

    HasCoinState(Machine machine) {
        this.machine = machine;
    }

    public void insertCoin() {
        System.out.println("can't insert another coin");
    }
    public void ejectCoin() {
        System.out.println("coin returned");
        machine.setState(machine.getNoCoinState());
        
    }
    public void trunCrank() {
        System.out.println("turned...");
        machine.setState(machine.getSoldState());
        
    }
    public void dispense() {
        System.out.println("No ball dispensed");

    }

    public void refill() { } // hook function

    public String toString() {
        return "waiting for turn of crank";
    }

}
class SoldState implements State {
    Machine machine;

    SoldState(Machine machine) {
        this.machine = machine;
    }

    public void insertCoin() {
        System.out.println("already giving you a ball");
    }
    public void ejectCoin() {
        System.out.println("already turned the crank");
        
    }
    public void trunCrank() {
        System.out.println("Do not turning twice!");
        
    }
    public void dispense() {
        machine.releaseBall();
        if (machine.getCount() > 0) {
            machine.setState(machine.getNoCoinState());
        } else {
            System.out.println("Sold Out!");
            machine.setState(machine.getSoldOutState());
        }
    }

    public void refill() { } // hook function

    public String toString() {
        return "dispensing a ball";
    }

}


class Machine {

    State soldOutState;
    State noCoinState;
    State hasCoinState;
    State soldState;

    State state;
    int count = 0;

    Machine(int numberOfBalls) {

        soldOutState = new SoldOutState(this);
        noCoinState = new NoCoinState(this);
        hasCoinState = new HasCoinState(this);
        soldState = new SoldState(this);

        this.count = numberOfBalls;
        if (numberOfBalls > 0) {
            state = noCoinState; // init : default value
        } else {
            state = soldOutState; // init : default value
        }
    }

    void insertCoin() {
        state.insertCoin();
    }
    void ejectCoin() {
        state.ejectCoin();
    }
    void turnCrank() {
        state.trunCrank();
        state.dispense();
    }

    void releaseBall() {
        System.out.println("please take the ball");
        if (count > 0) {
            count = count - 1;
        }
    }

    void refill(int count) {
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

    @Override
    public String toString() {
        StringBuffer result = new StringBuffer();
        result.append("\nInventory: " + count + " ball(s)\n");
        result.append("Machine is " + state + "\n");

        return result.toString();
    }
}

class TestDriver {
    public static void main(String[] args) {
        Machine machine = new Machine(2);

        System.out.println(machine);

        machine.insertCoin();
        machine.turnCrank();
        machine.insertCoin();
        machine.turnCrank();

        machine.refill(5);
        machine.insertCoin();
        machine.turnCrank();

        System.out.println(machine);
    }

}