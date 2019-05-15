package server;

import server.presentation.RMICommunication;
import server.presentation.IRMIObject;
import server.presentation.Security;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Main {

    public static void main(String[] args) {
        System.out.println("Starting server...");
//        new SocketCommunication(
//                new Manager(
//                        new FileManager()
//                )
//        ).answer();
//        if (System.getSecurityManager() == null) {
//            System.setSecurityManager(new SecurityManager());
//        }
        try {
            if (System.getSecurityManager() == null) {
                System.setSecurityManager(new Security(System.getSecurityManager()));
            }
//            RMICommunication r = new RMICommunication(4000);
            IRMIObject r = (IRMIObject) UnicastRemoteObject
                    .exportObject(new RMICommunication(), 0);
            Registry registry =  LocateRegistry.createRegistry(6060);
            registry.bind("get", r);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
