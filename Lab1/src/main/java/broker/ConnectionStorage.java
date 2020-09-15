package broker;

import common.ConnectionInfo;

import java.util.ArrayList;
import java.util.List;

public class ConnectionStorage {
    private static transient List<ConnectionInfo> connections;

    public ConnectionStorage() {
        connections = new ArrayList<>();
    }

    public static void add(ConnectionInfo connectionInfo){
        synchronized (connections){
            connections.add(connectionInfo);
        }
    }
    public static void remove(){
    }
}
