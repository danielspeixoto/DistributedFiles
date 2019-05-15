package presentation;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;

public interface IRMIObject extends Remote {

    HashMap<String, String> get() throws RemoteException;
}
