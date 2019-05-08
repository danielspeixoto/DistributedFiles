package domain;

public class Manager {

    private ICommunication communication;

    public Manager(ICommunication communication) {
        this.communication = communication;
    }

    public String read(long rid) {
        int bufSize = 10000;
        StringBuffer buffer = new StringBuffer(bufSize);
        if(communication.rread(buffer, bufSize, 10000, rid) == 0) {
            return "Nada para ler";
        }
        return "Conteúdo: " + buffer.toString();
    }

    public long open(String filename, String mode) {
        return communication.ropen(filename, mode);
    }

    public long reof(long rid) {
        return communication.reof(rid);
    }

    public long rclose(long rid) {
        return communication.rclose(rid);
    }

    public long rremove(String filename) {
        return communication.rremove(filename);
    }

    public long rgetpos(long rid, int pos) {
        return communication.rgetpos(rid, pos);
    }

    public long rseek(long rid, int offset, String origin) {
        if(origin.equals("SEEK_SET") || origin.equals("SEEK_CUR") || origin.equals("SEEK_END")){
            return communication.rseek(rid, offset, origin);
        }
        return 1;
    }
}
