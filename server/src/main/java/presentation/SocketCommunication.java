package presentation;

import com.google.gson.Gson;
import domain.Manager;

import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

//classe para lidar com a comunicacao via Socket com os clientes
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

    //funcao para criar Thread para atender multiplos clientes
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

    //funcao para lidar com uma entrada
    //descontroi a mensagem passagem em JSON
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
            HashMap<String, String> response = new HashMap<>();

            switch (m.get("operation")) {
                case OPEN:
                    Long num = manager.open(
                            m.get("filename"),
                            m.get("mode"));
                    response.put("rid", String.valueOf(num));
                    break;
                case READ:
                    String text = manager.read(
                            Long.valueOf(m.get("rid")),
                            Integer.valueOf(m.get("count"))
                    );
                    response.put("text", String.valueOf(text));
                    break;
                case EOF:
                    num = manager.close(Long.valueOf(m.get("rid")));
                    response.put("final", String.valueOf(num));
                    break;
                case WRITE:
                    System.out.println("entrei aqui " + m.get("buffer"));
                    num = manager.write(Long.valueOf(m.get("rid")), m.get("buffer"));
                    response.put("total", String.valueOf(num));
                    break;
                case GETPOS:
                    num = manager.getpos(Long.valueOf(m.get("rid")));
                    response.put("getpos", String.valueOf(num));
                    break;
                case SEEK:
                    num = manager.seek(Long.valueOf(m.get("rid")), Long.valueOf(m.get("offset")), m.get("origin"));
                    response.put("spos", String.valueOf(num));
                    break;
                case CLOSE:
                    num = manager.close(Long.valueOf(m.get("rid")));
                    response.put("close", String.valueOf(num));
                    break;
                case REMOVE:
                    num = manager.remove(Long.valueOf(m.get("rid")));
                    response.put("del", String.valueOf(num));
                    break;
                default:
                    break;
            }
            response(response, writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //funcao para envio da resposta
    public void response(HashMap<String, String> map, BufferedWriter writer){
        try {
            System.out.println("response: " + map);
            writer.write(gson.toJson(map) + "\n");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


