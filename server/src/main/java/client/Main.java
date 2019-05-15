package client;

import client.data.Operation;
import client.data.RMICom;
import client.domain.ClientManager;
import client.presentation.Terminal;
import server.domain.Manager;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Main {

    public static void main(String[] args) {
        System.out.println("Starting client...");
//        new Terminal(
//                new ClientManager()(
//                        new Operation(
//                                new SocketCom("127.0.0.1", 3001)
//                        )
//                )
//        ).start();
        try {
            new Terminal(
                    new ClientManager(
                            new Operation(
                                    new RMICom("127.0.0.1", 6060)
                            )
                    )
            ).start();

        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
