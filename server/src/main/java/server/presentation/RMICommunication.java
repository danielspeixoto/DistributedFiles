package server.presentation;

import java.rmi.RemoteException;
import java.util.Map;

public class RMICommunication implements IRMIObject {

    private RequestManager processManager;

    public RMICommunication(RequestManager manager) {

        this.processManager = manager;
    }

    @Override
    public Map<String, String> request(Map<String, String> req) throws RemoteException {
        return this.processManager.process(req);
    }
}
