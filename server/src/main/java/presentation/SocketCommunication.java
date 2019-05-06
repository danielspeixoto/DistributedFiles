package presentation;

import com.google.gson.Gson;
import domain.Manager;

import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

public class SocketCommunication {

    private static final String OPEN = "ropen";
    private static final String READ = "rread";
    private static final String EOF = "reof";
    private static final String SEEK = "rseek";
    private static final String WRITE = "rwrite";
    private static final String GETPOS = "rgetpos";
    private static final String CLOSE = "rclose";
    private static final String REMOVE = "rremove";

    private Manager manager;
    private Gson gson = new Gson();

    public SocketCommunication(Manager manager) {
        this.manager = manager;
    }

    public void answer() {

        try {
            ServerSocket server = new ServerSocket(3001);
            while (true) {
                Socket s = server.accept();
                new Thread(() -> process(s)).run();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void process(Socket client) {
        try {
            BufferedReader scanner = new BufferedReader(
                    new InputStreamReader(
                            client.getInputStream()
                    )
            );
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                    client.getOutputStream()
            ));
            String input = scanner.readLine();
            System.out.println(input);

            Map<String, String> m =
                    (Map<String, String>) gson.fromJson(input, Map.class);

            switch (m.get("operation")) {
                case OPEN:
                    manager.open(
                            m.get("filename"),
                            m.get("mode"), num -> {
                                HashMap<String, String> response = new HashMap<>();
                                response.put("rid", String.valueOf(num));
                                try {
                                    System.out.println("rid: " + num);
                                    writer.write(gson.toJson(response) + "\n");
                                    writer.flush();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }


                            });
                    break;
                case READ:
                    manager.read(
                            Long.valueOf(m.get("rid")),
                            Integer.valueOf(m.get("count")),
                            text -> {
                                HashMap<String, String> response = new HashMap<>();
                                response.put("text", String.valueOf(text));
                                try {
                                    System.out.println("response: " + response);
                                    writer.write(gson.toJson(response) + "\n");
                                    writer.flush();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                    );
                    break;
                case EOF:

                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
