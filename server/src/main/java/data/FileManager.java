package data;

import java.io.*;

public class FileManager {

    public static void main(String[] args){
        System.out.println("Test");
        FileManager fman = new FileManager();
        //System.out.println(fman.read("/home/felipe/Área de Trabalho/test.txt", 0));
        System.out.println(fman.remove("/home/felipe/Área de Trabalho/test.txt"));
    }

    public String read(String filename, int count) {
        InputStream f = null;
        int content = 0;
        try{
            f = new FileInputStream(filename);
            content = f.read();
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }finally {

        }

        return String.valueOf(content);
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

    public int remove(String filename){
        File file = new File(filename);
        if (file.delete()){
            return 0;
        }else{
            return 1;
        }
    }
}
