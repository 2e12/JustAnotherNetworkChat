package sample;

import java.io.IOException;
import java.net.Socket;

public abstract class JancCommand {

    private Socket sourceSocket;

    public Socket getSourceSocket() {
        return sourceSocket;
    }

    public void setSourceSocket(Socket sourceSocket) {
        this.sourceSocket = sourceSocket;
    }

    //Pass parameters to the parseFromString Function
    public JancCommand(String[] parts, Socket source) {
        parseFromString(parts);
        this.sourceSocket = source;
    }

    abstract void handle();

    abstract void send(Socket socket) throws IOException;

    abstract void parseFromString(String[] parts);
}
