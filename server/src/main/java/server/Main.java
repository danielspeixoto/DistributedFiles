package server;

import server.data.FileManager;
import server.domain.Manager;
import server.presentation.*;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Main {

    public static void main(String[] args) {
        System.out.println("Starting server...");
        RequestManager requestManager = new RequestManager(new Manager(
                new FileManager()
        ));
        new Thread(() -> {
            try {
                new SocketCommunication(requestManager).answer();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                if (System.getSecurityManager() == null) {
                    System.setSecurityManager(new Security(System.getSecurityManager()));
                }
                IRMIObject r = (IRMIObject) UnicastRemoteObject
                        .exportObject(new RMICommunication(requestManager), 0);
                Registry registry = LocateRegistry.createRegistry(6060);
                registry.bind("request", r);
                System.out.println("RMI ready");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

    }
}
