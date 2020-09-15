package publisher;

import common.BrokerParameters;
import common.JsonSerializer;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Scanner;

public class Publisher {
    public static Scanner sc = new Scanner(System.in);
    static int messageCount = 0;

    public static void main(String[] args) throws IOException {
        System.out.println("Publisher");
        PublisherSocket publisherSocket = new PublisherSocket();
        publisherSocket.connect(BrokerParameters.BROKER_IP, BrokerParameters.BROKER_PORT);
        if (publisherSocket.isConnected) {
            while (true) {
                messageCount++;
                System.out.println("Enter Topic");
                String topic = sc.nextLine().toLowerCase();
                System.out.println("Enter Message");
                String message = sc.nextLine();
                JSONObject data = JsonSerializer.getJSONByData(Integer.toString(messageCount), topic, message);
                byte[] serializedData = JsonSerializer.serializeJSON(data);
                publisherSocket.send(serializedData);
            }
        }
    }
}
