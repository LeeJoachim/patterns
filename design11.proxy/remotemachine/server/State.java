import java.io.*; // for serializable

public interface State extends Serializable { // for serializable, and must public

    public void insertCoin();
    public void ejectCoin();
    public void trunCrank();
    public void dispense();

    public void refill();
}