package subscriber;

import common.BrokerParameters;
import common.JsonSerializer;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Subscriber {
    public static void main(String[] args) throws Exception {
        System.out.println("Subscriber");
        String topic;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the topic: ");
        topic = sc.nextLine().toLowerCase();
        SubscriberSocket subscriberSocket = new SubscriberSocket(topic);
        subscriberSocket.connect(BrokerParameters.BROKER_IP, BrokerParameters.BROKER_PORT);
    }
}
