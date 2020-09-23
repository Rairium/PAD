package broker;

import common.ConnectionInfo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ConnectionStorage {
    private static List<ConnectionInfo> connections = Collections.synchronizedList(new ArrayList<>());

    public static void add(ConnectionInfo connectionInfo) {
        System.out.println("Connection Info successfully added");
        connections.add(connectionInfo);
    }

    public static void remove(String address) {
        connections.removeIf(obj -> obj.getAddress().equals(address));
    }

    public static List<ConnectionInfo> getConnectionsByTopic(String topic) {
        List<ConnectionInfo> selectedConnections = Collections.synchronizedList(new ArrayList<>());
        synchronized (selectedConnections) {
//            while (connections.iterator().hasNext()) {
//                selectedConnections.add(connections.stream()
//                        .filter(connection -> connection.getTopic()
//                                .equals(topic)).findAny()
//                        .orElse(null));
//            }
            for (ConnectionInfo connection : connections) {
                if (connection.getTopic().equals(topic)) {
                    selectedConnections.add(connection);
                }
            }
        }
        return selectedConnections;
    }

    public static boolean isEmpty() {
        return connections.isEmpty();
    }
}
