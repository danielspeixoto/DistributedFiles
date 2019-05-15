package client.data;

import client.domain.ICommunication;
import server.presentation.IRMIObject;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashMap;
import java.util.Map;

public class RMICom implements ICommunication {

    private Registry registry;

    public RMICom(String host, int port) throws RemoteException {
        registry = LocateRegistry.getRegistry("127.0.0.1", port);
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new Security(System.getSecurityManager()));
        }
    }

    @Override
    public Map<String, String> request(Map<String, String> params) {
        try {
            return ((IRMIObject) registry.lookup("request")).request(params);
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
        return new HashMap<>();
    }
}
