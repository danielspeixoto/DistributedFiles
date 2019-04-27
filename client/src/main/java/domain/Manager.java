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
}
