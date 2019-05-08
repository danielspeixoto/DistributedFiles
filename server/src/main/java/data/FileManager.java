package data;

import java.io.*;

public class FileManager {

    public static void main(){
        System.out.println("Test");
    }

    public String read(String filename, int count) {
        InputStream f = null;
        try{
            f = new FileInputStream(filename);
            char content = (char) f.read();
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }finally {

        }

        return "asdf";
    }

    public int eof(String filename) {
        int r = 1;
        try {

        } catch (Exception e) {

        }
        return r;
    }

    public int write(int rid){

        return 1;
    }

    public int seek(int rid){
        return 1;
    }

    public int getpos(int rid){
        return 1;
    }

    public int remove(int rid){
        return 1;
    }
}
