package publisher;


import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class PublisherSocket {
    private final Socket socket;
    public boolean isConnected;

    public PublisherSocket() {
        this.socket = new Socket();
    }

    public void connect(String ip, int port) throws IOException {
        this.socket.connect(new InetSocketAddress(ip, port));
        ifConnected();
    }

    private void ifConnected() {
        if (this.socket.isConnected()) {
            System.out.println("Sender Connected to Receiver");
        } else {
            System.out.println("Error :");
        }
        this.isConnected = this.socket.isConnected();
    }

    public void send(byte[] data) {
        try {
            DataOutputStream socketOutputStream = new DataOutputStream(this.socket.getOutputStream());
            socketOutputStream.write(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
