package presentation;

import domain.Manager;

import javax.sound.midi.SysexMessage;
import java.util.Scanner;

public class Terminal {

    private static final String OPEN = "ropen";
    private static final String READ = "rread";
    private static final String EOF = "reof";
    private static final String SEEK = "rseek";
    private static final String WRITE = "rwrite";
    private static final String GETPOS = "rgetpos";
    private static final String CLOSE = "rclose";
    private static final String REMOVE = "rremove";

    private Manager manager;

    public Terminal(Manager manager) {
        this.manager = manager;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        long lastRid = 0;
        while(true) {
            String input = scanner.nextLine();
            String[] params = input.split(" ");
            String operation = params[0];
            System.out.println("param " + params[0]);
            switch (operation) {
                case OPEN:
                    lastRid = manager.open(params[1], params[2]);
                    System.out.println(lastRid);
                    break;
                case READ:
                    System.out.println(manager.read(Long.valueOf(params[1])));
                    break;
                case WRITE:

                    break;
                case EOF:

                    break;
                case SEEK:

                    break;
                case CLOSE:

                    break;
                case GETPOS:

                    break;
                case REMOVE:

                    break;

                default:
                    break;
            }
        }
    }
}
