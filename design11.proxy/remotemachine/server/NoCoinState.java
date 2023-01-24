public class NoCoinState implements State {

    private static final long serialVersionUID = 2L; // implements Serializable, you need to this data member
    // transient Machine machine; // transient means that 'do not serialize'
    transient Machine machine;

    public NoCoinState(Machine machine) {
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