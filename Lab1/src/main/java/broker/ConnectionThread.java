package broker;

import common.ConnectionInfo;
import common.JsonParser;
import common.JsonSerializer;
import org.json.JSONObject;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ConnectionThread implements Runnable {

    BrokerSocket brokerSocket;
    Socket connectedClient;
    DataOutputStream outputStream;
    DataInputStream inputStream;
    Scanner sc = new Scanner(System.in);
    int id;
    ConnectionInfo connectionInfo = new ConnectionInfo();

    ConnectionThread(Socket connectedClient, int count, BrokerSocket brokerSocket) throws IOException {
        this.brokerSocket = brokerSocket;
        this.connectedClient = connectedClient;
        this.id = count;
        System.out.println("Connection " + id + " established with client " + connectedClient);
        outputStream = new DataOutputStream(connectedClient.getOutputStream());
        inputStream = new DataInputStream(connectedClient.getInputStream());

    }

    public void run() {
        try {
            while (true) {
                inputStream.read(connectionInfo.getData());
                JSONObject jsonData = JsonSerializer.deserializeJSON(connectionInfo.getData());
                JsonParser parsedData = new JsonParser();
                parsedData.parseJSON(jsonData);
                connectionInfo.setTopic(parsedData.getTopic());
                connectionInfo.setAddress(this.connectedClient.getRemoteSocketAddress().toString());
                connectionInfo.setSocket(this.connectedClient);
                if (connectionInfo.getTopic().contains("subscribe#")) {
                    connectionInfo.setTopic(parsedData.getTopic().replace("subscribe#", ""));
                    ConnectionStorage.add(connectionInfo);
                } else {
                    DataStorage.add(connectionInfo);
                }
            }
        } catch (Exception e) {
            System.out.println("The Connection with " + this.connectedClient.getRemoteSocketAddress().toString() + "  was forcefully interrupted");
            ConnectionStorage.remove(this.connectedClient.getRemoteSocketAddress().toString());
            if (this.connectedClient.isConnected()) {
                try {
                    this.connectedClient.close();
                } catch (IOException ioException) {
                    System.out.println("Client Socket already closed");
                }
            }
        }
    }
}