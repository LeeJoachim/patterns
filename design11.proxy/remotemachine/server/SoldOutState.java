public class SoldOutState implements State {

    private static final long serialVersionUID = 2L; // implements Serializable, you need to this data member
    // transient Machine machine; // transient means that 'do not serialize'
    transient Machine machine;

    public SoldOutState(Machine machine) {
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