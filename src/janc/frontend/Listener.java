package janc.frontend;

import javafx.application.Platform;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Listener extends Thread{
    private Socket socket;
    private Model model;

    /**
     * The creator of this class gives the instance variable the value of the parameter socket.
     * @param socket the server socket.
     */
    public Listener(Socket socket) {
        this.socket = socket;
        this.model = model;
    }

    /**
     * This method listens to the server for any distributed messages.
     */
    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            while (!this.socket.isClosed()) {
                String response = in.readLine();
                if (response != null) {
                    Model.setOutput(response);
                }
            }
            Platform.exit();
        } catch (IOException e) {
            Platform.runLater(() ->
                    Client.switchToScene("home")
                    );
        }
    }

    /**
     * Gets socket.
     *
     * @return Value of socket.
     */
    public Socket getSocket() {
        return socket;
    }
}
