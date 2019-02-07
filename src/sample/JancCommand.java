package sample;
import java.net.Socket;

public abstract class JancCommand {
    abstract void handle();
    abstract void send(Socket socket);
    abstract void parseFromString(String input);
}
