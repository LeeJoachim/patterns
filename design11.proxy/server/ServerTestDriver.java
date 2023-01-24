import java.rmi.Naming;

class ServerTestDriver {
    public static void main(String[] args) {
        
        try {

            MyRemote service = new MyRemoteImpl(); // upcasting
            Naming.rebind("RemoteHello", service); // register server obj : service
                                                         // and, waiting client's connection
                                                         // "RemoteHello" String type replaces Port Number

        } catch (Exception e) {
            e.printStackTrace();
        }     
    }
}