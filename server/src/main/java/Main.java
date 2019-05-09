import data.FileManager;
import domain.Manager;
import presentation.SocketCommunication;

//classe inicializadora do servidor
public class Main {

    public static void main(String[] args) {
        System.out.println("Starting server...");
        new SocketCommunication(
                new Manager(
                        new FileManager()
                )
        ).answer();
    }
}
