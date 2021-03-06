package server.presentation;

import com.google.gson.Gson;

import java.io.*;
import java.net.*;
import java.util.Map;

//classe para lidar com a comunicacao via Socket com os clientes
public class SocketCommunication {

    private RequestManager processManager;
    private Gson gson = new Gson();

    public SocketCommunication(RequestManager manager) {
        this.processManager = manager;
    }

    //funcao para criar Thread para atender multiplos clientes
    public void answer() {
        try {
            ServerSocket server = new ServerSocket(3333);
            System.out.println("socket ready");
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

            Map<String, String> m = (Map<String, String>) gson.fromJson(input, Map.class);
            response(this.processManager.process(m), writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //funcao para envio da resposta
    public void response(Map<String, String> map, BufferedWriter writer){
        try {
            System.out.println("response: " + map);
            writer.write(gson.toJson(map) + "\n");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


