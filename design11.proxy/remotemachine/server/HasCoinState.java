public class HasCoinState implements State {

    private static final long serialVersionUID = 2L; // implements Serializable, you need to this data member
    // transient Machine machine; // transient means that 'do not serialize'
    Machine machine;

    public HasCoinState(Machine machine) {
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