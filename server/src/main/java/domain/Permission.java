package domain;

public class Permission {

    private String filename;
    private String mode;
    private int position;

    public Permission(String filename, String mode, int position) {
        this.filename = filename;
        this.mode = mode;
        this.position = position;
    }

    public String getFilename() {
        return filename;
    }

    public String getMode() {
        return mode;
    }

    public int getPosition(){return position;}
}
