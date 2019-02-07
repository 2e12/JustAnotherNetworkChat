package sample;

<<<<<<< HEAD
public class JancCommand {
=======
import java.net.Socket;

public abstract class JancCommand {
    abstract void handle();
    abstract void send(Socket socket);
    abstract void parseFromString(String input);
>>>>>>> e162e3812726db6383cd40de5e4a1252f6134224
}
