package domain;

public class Permission {

    private String filename;
    private String mode;

    public Permission(String filename, String mode) {
        this.filename = filename;
        this.mode = mode;
    }

    public String getFilename() {
        return filename;
    }

    public String getMode() {
        return mode;
    }
}
