package sample;

import java.net.Socket;

public class ServerClientConnection extends Thread {

    private Socket clientConnection;
    private JancProtocolHandler protocol = JancProtocolHandler.getInstance();


    public ServerClientConnection() {
    }

    public void run() {
    }
}
