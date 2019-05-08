package data;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.io.FileWriter;
import java.nio.file.Path;
import java.util.Scanner; // Import the Scanner class to read text files

public class FileManager {

    private static final String PATH = "C:\\Users\\Silva\\Documents\\UFBA\\Sistemas Distribuidos\\DistributedFiles\\server\\src\\main\\java\\res\\";

    private String fileToString(String filename) {
        String acum = "";
        try {
            File f = new File(filename);
            Scanner reader = new Scanner(f);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                acum += data + "\n";
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return acum;
    }

    private long fileSize(String filename) {
        long acum = -1;
        try {
            File f = new File(filename);
            acum = f.length();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return acum;
    }

    private boolean fileExists(String filename) {
        try {
            File f = new File(filename);
            return f.exists();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public String read(String filename, long start, long count) {
        String fPath = PATH + filename;
        String acum="";
        if(fileExists(fPath)) {
            String data = fileToString(PATH + filename);
            acum = data.substring((int) start, Math.min((int) (start + count), data.length()));
        }
        return acum;
    }

    public boolean eof(String filename, long position) {
        String data = fileToString(PATH + filename);
        return data.length() == (int)position;
    }

    public int write(String buffer, String filename, String mode, long position){
        int result=0;
        String file = PATH + filename;
        boolean append = mode.contains("a");

        try {
           FileWriter fWriter = new FileWriter(file, append);
           fWriter.write(buffer);
        } catch (Exception e){
           result=0;
        }

        return result;
    }

    public long seek(String filename, long position, long offset, String origin) {
        long fileSize = fileSize(PATH + filename);
        switch(origin) {
            case "SEEK_SET":
                if(offset <= fileSize) {
                    return offset;
                }
                return -1;
            case "SEEK_CUR":
                if(position + offset <= fileSize) {
                    return position + offset;
                }
                return -1;
            case "SEEK_END":
                if(fileSize + offset <= fileSize) {
                    return position + offset;
                }
                return -1;
        }
        return position;
    }

    public long getpos(long position){
        return position;
    }

    public int remove(String filename){
        String f = PATH + filename;
        File deleteFile = new File(f);
        if(deleteFile.delete()){
            return 0;
        }
        return 1;
    }
}
