package common;

import java.net.Socket;

public class ConnectionInfo {
    public final int BUFF_SIZE = 1024;
    public byte[] data;
    public Socket socket;
    public String address;
    public String topic;

    public ConnectionInfo() {
        this.data = new byte[BUFF_SIZE];
    }

    public byte[] getData() {
        return data;
    }

    public ConnectionInfo setData(byte[] data) {
        this.data = data;
        return this;
    }

    public Socket getSocket() {
        return socket;
    }

    public ConnectionInfo setSocket(Socket socket) {
        this.socket = socket;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public ConnectionInfo setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getTopic() {
        return topic;
    }

    public ConnectionInfo setTopic(String topic) {
        this.topic = topic;
        return this;
    }
}
