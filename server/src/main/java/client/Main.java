package client;

import client.data.RMICom;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Main {

    public static void main(String[] args) {
        System.out.println("Starting client...");
//        new Terminal(
//                new Manager(
//                        new Operation(
//                                new SocketCom("127.0.0.1", 3001)
//                        )
//                )
//        ).start();
        try {
            new RMICom("127.0.0.1");
        } catch (RemoteException | NotBoundException | MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
