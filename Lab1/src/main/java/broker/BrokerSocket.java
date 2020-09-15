package broker;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BrokerSocket {
    private final ServerSocket serverSocket;
    private final ExecutorService connectionPool;
    int connections = 0;

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public BrokerSocket() throws IOException {
        this.serverSocket = new ServerSocket();
        this.connectionPool = Executors.newFixedThreadPool(4);
    }

    public void start(int port) throws IOException {
        this.serverSocket.bind(new InetSocketAddress(port));
            accept();
    }

    private void accept() throws IOException {
        while (true){
            connections++;
            Socket connectedSocket = this.serverSocket.accept();
            BrokerThread brokerThread = new BrokerThread(connectedSocket,connections, this);
            this.connectionPool.execute(brokerThread);
        }
    }
}
