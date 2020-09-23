package broker;

import common.ConnectionInfo;

import java.util.concurrent.LinkedBlockingQueue;

public class DataStorage {
    private static LinkedBlockingQueue<ConnectionInfo> dataQueue = new LinkedBlockingQueue<>();

    public static void add(ConnectionInfo connectionInfo) {
        dataQueue.add(connectionInfo);
        System.out.println("Data Info successfully added");
    }

    public static ConnectionInfo getNext() {
        ConnectionInfo connectionInfo;
        if (dataQueue.iterator().hasNext()) {
            connectionInfo = dataQueue.iterator().next();
            dataQueue.remove(dataQueue.iterator().next());
            return connectionInfo;
        } else return null;
    }

    public static boolean isEmpty() {
        return dataQueue.isEmpty();
    }
}
