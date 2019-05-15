package server.presentation;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Map;

public interface IRMIObject extends Remote {

    Map<String, String> request(Map<String, String> req) throws RemoteException;
}
