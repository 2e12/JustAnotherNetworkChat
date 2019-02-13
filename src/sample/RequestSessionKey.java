package sample;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class RequestSessionKey extends JancCommand{
    String username;
    String password;

    public RequestSessionKey(String[] parts, Socket source) {
        super(parts, source);
    }

    @Override
    void handle() {
        System.out.println(this.username);
        JancProtocolHandler.getInstance().putUserConnection(this.username, getSourceSocket());

        //Implement login
    }

    @Override
    void send(Socket socket) throws IOException {
        var out = new PrintWriter(socket.getOutputStream(), true);
        out.println("lgn;gibb;sml12345");
        out.close();
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
