package sample;
import java.net.Socket;

public abstract class JancCommand {

    //Pass parameters to the parseFromString Function
    public JancCommand(String[] parts) {
        parseFromString(parts);
    }

    static JancProtocolHandler protocol;
    abstract void handle();
    abstract void send(Socket socket);

    abstract void parseFromString(String[] parts);
}
