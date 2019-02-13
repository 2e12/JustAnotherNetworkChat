package janc.backend;

import java.io.*;
import java.net.Socket;

public class ServerClientConnection extends Thread {

    private Socket clientConnection;
    private String SessionKey;


    public Socket getClientConnection() {
        return clientConnection;
    }

    public void setClientConnection(Socket clientConnection) {
        this.clientConnection = clientConnection;
    }

    public String getSessionKey() {
        return SessionKey;
    }

    public void setSessionKey(String sessionKey) {
        SessionKey = sessionKey;
    }

    public ServerClientConnection(Socket clientConnection, String sessionKey) {
        this.clientConnection = clientConnection;
        this.setSessionKey(sessionKey);
    }

    public void run() {
        boolean run = true;
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(this.clientConnection.getInputStream()));
            while (!clientConnection.isClosed()) {
                String response = in.readLine();
                JancProtocolHandler.getInstance().ParseFromString(response, this.getClientConnection(), this);
            }

        } catch (IOException e) {
            try {
                this.clientConnection.close();
                System.out.println("Connection lost with " + this.clientConnection.getInetAddress());
            } catch (IOException ee) {

            }
            run = false;
        }
    }
}
