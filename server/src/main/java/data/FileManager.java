package data;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.nio.file.Path;
import java.util.Scanner; // Import the Scanner class to read text files

public class FileManager {

    private static final String PATH = "C:\\Users\\Silva\\Documents\\UFBA\\Sistemas Distribuidos\\DistributedFiles\\server\\src\\main\\java\\res\\";

    private String fileToString(String filename) {
        return "";
    }

    public String read(String filename, int count)
    {
        String acum="";
        String myFilePath = PATH + filename;
        int total=0;
        try {
            File myFile = new File(myFilePath);
            Scanner reader = new Scanner(myFile);
            while (reader.hasNextLine() && total < count) {
                String data = reader.nextLine();
                if(total + data.length() <= count) {
                    total += data.length();
                    acum += data;
                }else {
                    total += (data.length() - (count - total));
                    acum += data.substring(0, data.length() - (count - total));
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            return acum = "";
            //e.printStackTrace();
        }
        return acum;
    }

    public int eof(String filename) {
        int x = 1;
        try {

        } catch (Exception e) {

        }
        return x;
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
        String myFile = PATH + filename;
        File deleteFile = new File(myFile);
        if(deleteFile.delete()){
            return 0;
        }
        return 1;
    }
}
