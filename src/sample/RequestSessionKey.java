package sample;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class RequestSessionKey extends JancCommand{
    String username;
    String password;
    String prefix = "lgn";

    public RequestSessionKey(String[] parts, Socket source) {
        super(parts, source);
    }

    @Override
    void handle() {
        System.out.println(this.username);
        JancProtocolHandler.getInstance().putUserConnection(this.username, getSourceSocket());
    }

    @Override
    void send(Socket socket) throws IOException {
        var out = new PrintWriter(socket.getOutputStream(), true);
        out.println("lgn;gibb;sml12345");
    }

    @Override
        //Set Parameters
    void parseFromString(String[] parts) {
        this.username = parts[1];
        this.password = parts[2];
    }
}
