package broker;

import common.BrokerParameters;

import java.io.IOException;

public class Broker {
    public static void main(String[] args) throws IOException {
        BrokerSocket brokerSocket = new BrokerSocket();
        brokerSocket.start(BrokerParameters.BROKER_PORT);
    }
}
