package domain;

public interface ICommunication {

    long ropen(String filename, String mode);
    long rread(StringBuffer buffer, int sizeBuf, int count, long rid);
}
