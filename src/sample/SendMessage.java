package sample;

import java.io.IOException;
import java.net.Socket;

public class SendMessage extends JancCommand{


    String sessionKey;
    String fromUsername;
    String toUsername;
    String timestamp;
    String message;

    //Init neeaded parameters
    public SendMessage(String[] parts, Socket source){
        super(parts, source);
    }

    @Override
    void handle() {
        System.out.println("[Message] " + this.fromUsername + ": " + this.message);
        JancProtocolHandler.getInstance().broadcastToAllClients("dsb;" + this.fromUsername + ";" + this.timestamp + ";" + this.message);
    }

    @Override
    void send(Socket socket) throws IOException {

    }

    @Override
    void parseFromString(String[] parts) {
        if(parts.length == 6) {
            this.sessionKey = parts[1];
            this.fromUsername = parts[2];
            this.toUsername = parts[3];
            this.timestamp = parts[4];
            this.message = parts[5];
        }
    }
}
