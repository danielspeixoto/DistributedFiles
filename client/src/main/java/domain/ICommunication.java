package domain;

public interface ICommunication {

    long ropen(String filename, String mode);
    long rread(StringBuffer buffer, int sizeBuf, int count, long rid);
    long reof(long rid);
    long rclose(long rid);
    long rremove(String filename);
    long rgetpos(long rid, int pos);
    long rseek(long rid, int offset, String origin);
    long rwrite(StringBuffer buffer, int sizeBuf, int count, long rid);
}
