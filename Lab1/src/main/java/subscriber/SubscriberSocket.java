package subscriber;

import common.BrokerParameters;
import common.ConnectionInfo;
import common.JsonParser;
import common.JsonSerializer;
import org.json.JSONObject;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class SubscriberSocket {
    private final Socket socket;
    private String topic;
    public boolean isConnected;

    public SubscriberSocket(String topic) {
        this.socket = new Socket();
        this.topic = topic;
    }

    public void connect(String host, int port) throws IOException {
        this.socket.connect(new InetSocketAddress(host, port));
        System.out.println("Waiting for connection...");
        ifConnected();
    }

    private void ifConnected() throws IOException {
        if (this.socket.isConnected()) {
            System.out.println("Subscriber Connected to Broker");
            subscribe();
            startReceive();
        } else {
            System.out.println("Error :");
        }
        this.isConnected = this.socket.isConnected();
    }

    private void subscribe() throws IOException {
        byte[] data = JsonSerializer.serializeJSON(JsonSerializer.getJSONByData("none", this.topic, "none"));
        send(data);
    }

    private void startReceive() throws IOException {
        ConnectionInfo connectionInfo = new ConnectionInfo();
        connectionInfo.setSocket(this.socket);
        DataInputStream inputStream = new DataInputStream(connectionInfo.getSocket().getInputStream());
        try {
            while (true) {
                inputStream.read(connectionInfo.getData());
                JSONObject jsonData = JsonSerializer.deserializeJSON(connectionInfo.getData());
                JsonParser parsedData = new JsonParser();
                parsedData.parseJSON(jsonData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void send(byte[] data) {
        try {
            DataOutputStream socketOutputStream = new DataOutputStream(this.socket.getOutputStream());
            socketOutputStream.write(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
