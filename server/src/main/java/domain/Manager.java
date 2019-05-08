package domain;

import data.FileManager;

import java.util.*;

public class Manager {

    private FileManager fileManager;

    private Map<Long, Permission> permissions =
            Collections.synchronizedMap(new HashMap<>());

    public Manager(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    private static String READ = "r";
    private static String WRITE = "w";
    private static String APPEND = "a";
    private static String READ_PLUS = "r+";
    private static String WRITE_CREATE = "w+";
    private static String APPEND_CREATE = "a+";
    private static List<String> MODES = Arrays.asList(
            READ, WRITE, APPEND, READ_PLUS, WRITE_CREATE, APPEND_CREATE
    );

    public synchronized Long open(String filename, String mode) {
        long result = 0;
        if(filename != null && mode != null) {
            ArrayList<Permission> usages = filenameUsage(filename);
            if (!MODES.contains(mode)) {
                // Invalid mode
               return 0L;
            }
            if (usages.size() == 0) {
                // Nobody is using this file
                result = createPermission(filename, mode);
            } else if (!isBeingWritten(usages.get(0)) && mode.contains(READ)) {
                // If someone is reading, anyone else can read.
                result = createPermission(filename, mode);
            }
        }
        return result;
    }

    public String read(long rid, int count) {
        String result = "";
        if (permissions.containsKey(rid)) {
            Permission permission = permissions.get(rid);
            result = fileManager.read(permission.getFilename(), count);
        }
        return result;
    }

    public long eof(long rid, ILongCallback callback){
        long result = 1;
        if (permissions.containsKey((rid))) {
            Permission permission = permissions.get(rid);
            result = fileManager.eof(permission.getFilename());
        }
        return result;
    }

    public long remove(long rid) {
        long result = 1;
        if (permissions.containsKey((rid))) {
            Permission permission = permissions.get(rid);
            result = fileManager.remove(permission.getFilename());
        }
        return result;
    }

    private synchronized long createPermission(String filename, String mode) {
        long rid = System.currentTimeMillis();
        Permission permission = new Permission(filename, mode,0);
        permissions.put(rid, permission);
        return rid;
    }

    private boolean isBeingWritten(Permission permission) {
        String mode = permission.getMode();
        return mode.contains(WRITE) || mode.contains(APPEND);
    }

    private ArrayList<Permission> filenameUsage(String filename) {
        ArrayList<Permission> permissions = new ArrayList<>();
        this.permissions.forEach((rid, permission) -> {
            if (permission.getFilename().equals(filename)) {
                permissions.add(permission);
            }
        });
        return permissions;
    }

}
