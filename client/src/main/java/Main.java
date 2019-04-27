import data.SocketCom;
import domain.Manager;
import presentation.Terminal;

public class Main {

    public static void main(String[] args) {
        new Terminal(
                new Manager(
                        new SocketCom("127.0.0.1", 3001)
                )
        ).start();
    }
}