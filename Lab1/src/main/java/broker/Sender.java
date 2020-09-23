package broker;

import common.ConnectionInfo;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;

public class Sender {
    public static void SendingMessage() throws InterruptedException, IOException {
        while (true) {
            System.out.println(!ConnectionStorage.isEmpty());
            while (!ConnectionStorage.isEmpty()) {
                ConnectionInfo connectionInfo = DataStorage.getNext();
                if (connectionInfo != null) {
                    List<ConnectionInfo> connections = ConnectionStorage.getConnectionsByTopic(connectionInfo.getTopic());
                    for (ConnectionInfo connection : connections) {
                        byte[] data = connectionInfo.getData();
                        DataOutputStream socketOutputStream = new DataOutputStream(connection.getSocket().getOutputStream());
                        socketOutputStream.write(data);
                    }
                }
            }
            Thread.sleep( 1000);
        }
    }
}
