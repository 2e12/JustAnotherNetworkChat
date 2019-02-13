package sample;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class SendSessionKey extends JancCommand{

    private String username;
    private String sessionKey;

    public SendSessionKey(String[] parts, Socket source) {
        super(parts, source);
    }

    @Override
    void handle() {

    }

    @Override
    void send(Socket socket) throws IOException {
        var out = new PrintWriter(socket.getOutputStream(), true);
        out.println("key;");
    }

    @Override
    void parseFromString(String[] parts) {
        if(parts.length == 3){
            this.username = parts[1];
            this.sessionKey = parts[2];
        }
    }
}
