package presentation;

import java.rmi.RemoteException;
import java.util.HashMap;

public class RMICommunication implements IRMIObject {

    public RMICommunication() { }

    @Override
    public HashMap<String, String> get() throws RemoteException {
        HashMap<String, String> h = new HashMap<>();
        h.put("hello", "world");
        System.out.println("invoked");
        return h;
    }
}
