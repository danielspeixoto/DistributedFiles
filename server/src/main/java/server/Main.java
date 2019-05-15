package server;

import server.data.FileManager;
import server.domain.Manager;
import server.presentation.RMICommunication;
import server.presentation.IRMIObject;
import server.presentation.RequestManager;
import server.presentation.Security;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Main {

    public static void main(String[] args) {
        System.out.println("Starting server...");
        RequestManager requestManager = new RequestManager(new Manager(
                new FileManager()
        ));
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
            IRMIObject r = (IRMIObject) UnicastRemoteObject
                    .exportObject(new RMICommunication(requestManager), 0);
            Registry registry = LocateRegistry.createRegistry(6060);
            registry.bind("request", r);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
