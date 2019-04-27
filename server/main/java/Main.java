

import data.FileManager;
import domain.Manager;
import presentation.SocketCommunication;

public class Main {

    public static void main(String[] args) {

        new SocketCommunication(
                new Manager(
                        new FileManager()
                )
        ).answer();
    }
}
