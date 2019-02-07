package sample;

import java.net.Socket;

public class RequestSessionKey extends JancCommand{
    String username;
    String password;

    public RequestSessionKey(String[] parts) {
        super(parts);
    }

    @Override
    void handle() {
        System.out.println(this.username);
    }

    @Override
    void send(Socket socket) {

    }

    @Override
        //Set Parameters
    void parseFromString(String[] parts) {
        this.username = parts[1];
        this.password = parts[2];
    }
}
