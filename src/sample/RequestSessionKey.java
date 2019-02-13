package sample;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;

public class RequestSessionKey extends JancCommand{
    String username;
    String password;

    public RequestSessionKey(String[] parts, Socket source) {
        super(parts, source);
    }

    @Override
    void handle() {
        Random rand = new Random();
        String sessionKey = String.valueOf(rand.nextInt(2147483647));
        String[] parts = new String[]{"key", this.username, sessionKey};
        SendSessionKey key = new SendSessionKey(parts, this.getSourceSocket());
        try {
            key.send(this.getSourceSocket());
            JancProtocolHandler.getInstance().putUserConnection(this.username, this.getSourceSocket(), sessionKey);
            System.out.println("[Login] Hello " + this.username + this.getSourceSocket().getInetAddress());
        } catch (IOException e) {

        }

    }

    @Override
    void send(Socket socket) throws IOException {
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        out.println("lgn;gibb;sml12345");

    }

    @Override
        //Set Parameters
    void parseFromString(String[] parts) {
        if(parts.length == 3) {
            this.username = parts[1];
            this.password = parts[2];
        }
    }
}
