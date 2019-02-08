package sample;

import java.net.Socket;

public class ServerClientConnection extends Thread {

    private Socket clientConnection;
    private JancProtocolHandler protocol = JancProtocolHandler.getInstance();
    private String SessionKey;

    public Socket getClientConnection() {
        return clientConnection;
    }

    public void setClientConnection(Socket clientConnection) {
        this.clientConnection = clientConnection;
    }

    public JancProtocolHandler getProtocol() {
        return protocol;
    }

    public void setProtocol(JancProtocolHandler protocol) {
        this.protocol = protocol;
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
    }
}
