package presentation;

import domain.Manager;

import java.util.Scanner;

public class Terminal {

    private static final String OPEN = "ropen";
    private static final String READ = "rread";


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
            switch (operation) {
                case OPEN:
                    lastRid = manager.open(params[1], params[2]);
                    System.out.println(lastRid);
                    break;
                case READ:
                    System.out.println(manager.read(Long.valueOf(params[1])));
            }
        }
    }
}
