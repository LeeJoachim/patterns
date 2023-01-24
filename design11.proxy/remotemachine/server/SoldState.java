public class SoldState implements State {

    private static final long serialVersionUID = 2L; // implements Serializable, you need to this data member
    // transient Machine machine; // transient means that 'do not serialize'
    transient Machine machine;

    public SoldState(Machine machine) {
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