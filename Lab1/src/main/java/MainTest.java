import common.BrokerParameters;
import common.JsonParser;
import org.json.JSONObject;
import common.JsonSerializer;
import subscriber.SubscriberSocket;

import java.io.IOException;
import java.util.Arrays;

public class MainTest {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        JSONObject str = JsonSerializer.getJSONByData("topic1", "kekw");
        System.out.println(str);
        byte[] strByte = JsonSerializer.serializeJSON(str);
        System.out.println(Arrays.toString(strByte));
        JSONObject strDeserialized = JsonSerializer.deserializeJSON(strByte);
        System.out.println(strDeserialized);
        JsonParser parser = new JsonParser();
        parser.parseJSON(strDeserialized);
        System.out.println(" topic " + parser.getTopic() + " message " + parser.getMessage());

//        SubscriberSocket subscriberSocket = new SubscriberSocket(BrokerParameters.BROKER_IP, BrokerParameters.BROKER_PORT);
    }
}
