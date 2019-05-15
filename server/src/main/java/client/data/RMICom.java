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

    public RMICom(String path) throws RemoteException, NotBoundException, MalformedURLException {
        Registry registry = LocateRegistry.getRegistry("127.0.0.1", 6060);

        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new Security(System.getSecurityManager()));
        }
        System.out.println(registry.list()[0]);

        HashMap<String, String> h = ((IRMIObject) registry.lookup("get")).get();
        System.out.println(h);
    }

    @Override
    public Map<String, String> request(Map<String, String> params) {
        return null;
    }
}
