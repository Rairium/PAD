package broker;

import common.ConnectionInfo;
import common.JsonParser;
import common.JsonSerializer;
import org.json.JSONObject;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class BrokerThread implements Runnable {

    BrokerSocket brokerSocket;
    Socket connectedClient;
    DataOutputStream outputStream;
    DataInputStream inputStream;
    Scanner sc = new Scanner(System.in);
    int id;
    ConnectionInfo connectionInfo = new ConnectionInfo();

    BrokerThread(Socket connectedClient, int count, BrokerSocket brokerSocket) throws IOException {

        this.brokerSocket = brokerSocket;
        this.connectedClient = connectedClient;
        this.id = count;
        System.out.println("Connection " + id + " established with client " + connectedClient);
        outputStream = new DataOutputStream(connectedClient.getOutputStream());
        inputStream = new DataInputStream(connectedClient.getInputStream());

    }

    public void run() {
        int x = 1;
        try {
            while (true) {
                inputStream.read(connectionInfo.getData());
                JSONObject jsonData = JsonSerializer.deserializeJSON(connectionInfo.getData());
                JsonParser parsedData = new JsonParser();
                parsedData.parseJSON(jsonData);
                connectionInfo.setTopic(parsedData.getTopic());
                System.out.println(jsonData);
//                System.out.print("Cl
//                ient(" + id + ") :" + s + "\n");
//                System.out.print("Server : ");
//                //s=stdin.readLine();
//                s = sc.nextLine();
//                if (s.equalsIgnoreCase("bye")) {
//                    cout.println("BYE");
//                    x = 0;
//                    System.out.println("Connection ended by server");
//                    break;
//                }
//                cout.println(s);
//            }
//
//
//            cin.close();
//            brokerSocket.getServerSocket().close();
//            cout.close();
//            if (x == 0) {
//                System.out.println("Server cleaning up.");
//                System.exit(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}