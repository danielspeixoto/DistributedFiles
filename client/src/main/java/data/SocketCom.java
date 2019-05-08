package data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import domain.ICommunication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.Buffer;
import java.util.HashMap;
import java.util.Map;

public class SocketCom implements ICommunication {

    private Socket socket;
    private OutputStreamWriter output;
    private BufferedReader input;
    private Gson g = new GsonBuilder().create();
    private String url;
    private int port;

    public SocketCom(String url, int port) {
        this.url = url;
        this.port = port;
//        try {
//            socket = new Socket(url, port);
//            output = new OutputStreamWriter(socket.getOutputStream());
//            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public long ropen(String filename, String mode) {
        Map<String, String> map = new HashMap<>();
        map.put("operation", "ropen");
        map.put("filename", filename);
        map.put("mode", mode);
        Map<String, String> response = request(map);
        if (response.containsKey("rid")) {
            return Long.valueOf(response.get("rid"));
        }
        return 1;
    }

    @Override
    public long rread(StringBuffer buffer, int sizeBuf, int count, long rid) {
        Map<String, String> map = new HashMap<>();
        map.put("operation", "rread");
        map.put("count", String.valueOf(count));
        map.put("rid", String.valueOf(rid));
        Map<String, String> result = request(map);
        if (result.containsKey("text")) {
            String text = result.get("text");
            for(int i = 0; i < sizeBuf && i < text.length(); i++) {
                buffer.append(text.charAt(i));
            }
            return text.length();
        }
        return 1;
    }

    @Override
    public long reof(long rid) {
        Map<String, String> map = new HashMap<>();
        map.put("operation", "reof");
        map.put("rid", String.valueOf(rid));
        Map<String, String> result = request(map);
        if (result.containsKey("final")){
            return Long.valueOf(result.get("final"));
        }
        return 1;
    }

    @Override
    public long rclose(long rid) {
        Map<String, String> map = new HashMap<>();
        map.put("operation", "rclose");
        map.put("rid", String.valueOf(rid));
        Map<String, String> result = request(map);
        if (result.containsKey("close")){
            return Long.valueOf(result.get("close"));
        }
        return 1;
    }

    public long rremove(String filename) {
        Map<String, String> map = new HashMap<>();
        map.put("operation", "rremove");
        map.put("filename", filename);
        Map<String, String> result = request(map);
        if (result.containsKey("del")){
            return Long.valueOf(result.get("del"));
        }
        return 1;
    }

    @Override
    public long rgetpos(long rid, int pos){
        Map<String, String> map = new HashMap<>();
        map.put("operation", "rgetpos");
        map.put("rid", String.valueOf(rid));
        map.put("pos", String.valueOf(pos));
        Map<String, String> result = request(map);
        if (result.containsKey("getpos")) {
            return Long.valueOf(result.get("getpos"));
        }
        return 1;
    }

    @Override
    public long rseek(long rid, int offset, String origin) {
        Map<String, String> map = new HashMap<>();
        map.put("operation", "rseek");
        map.put("rid", String.valueOf(rid));
        map.put("offset", String.valueOf(offset));
        map.put("origin", origin);
        Map<String, String> result = request(map);
        if (result.containsKey("spos")) {
            return Long.valueOf(result.get("spos"));
        }
        return 1;
    }

    public long rwrite(StringBuffer buffer, int sizeBuf, int count, long rid) {
        Map<String, String> map = new HashMap<>();
        map.put("operation", "rwrite");
        map.put("rid", String.valueOf(rid));
        map.put("buffer", buffer.toString());
        map.put("size", String.valueOf(sizeBuf));
        map.put("count", String.valueOf(count));
        Map<String, String> result = request(map);
        if (result.containsKey("total")) {
            return Long.valueOf(result.get("total"));
        }
        return 1;
    }

    private Map<String, String> request(Map<String, String> params) {
        Map<String, String> empty = new HashMap<>();
        try {
            socket = new Socket(url, port);
            output = new OutputStreamWriter(socket.getOutputStream());
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            output.write(g.toJson(params) + "\n");
            output.flush();
            Thread.sleep(500);
            if(input.ready()) {
                String response = input.readLine();
                return g.fromJson(response, Map.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return empty;
    }


}
