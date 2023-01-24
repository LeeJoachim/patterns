import java.rmi.*;

public class MyRemoteClient {
    public static void main(String[] args) {

        try {

            MyRemote service = 
                (MyRemote)Naming.lookup("rmi://localhost/RemoteHello"); //socket connection, "rmi://hostname/portnumber" 
            
            String s = service.sayHello();
            System.out.println(s);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}