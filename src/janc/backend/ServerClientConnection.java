package janc.backend;

import java.io.*;
import java.net.Socket;

public class ServerClientConnection extends Thread {

    private Socket clientConnection;
    private User user;

    /**
     * The constructor. Setup things.
     *
     * @param clientConnection The socket connection to an user.
     * @param user             The user whose waiting for commands.
     */
    public ServerClientConnection(Socket clientConnection, User user) {
        this.clientConnection = clientConnection;
        this.setUser(user);
    }

    /**
     * This method waits for incoming commands from a spesific client and pass it to the command parser.
     */
    public void run() {
        boolean run = true;
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(this.clientConnection.getInputStream()));
            while (!clientConnection.isClosed()) {
                String response = in.readLine();
                JancProtocolHandler.getInstance().parseFromString(response, this);
            }

        } catch (IOException e) {
            try {
                this.clientConnection.close();
                System.out.println("ServerConnection lost with " + this.clientConnection.getInetAddress());
            } catch (IOException ee) {

            }
            run = false;
        }
    }

    /**
     * Gets clientConnection.
     *
     * @return Value of clientConnection.
     */
    public Socket getClientConnection() {
        return clientConnection;
    }

    /**
     * Sets new clientConnection.
     *
     * @param clientConnection New value of clientConnection.
     */
    public void setClientConnection(Socket clientConnection) {
        this.clientConnection = clientConnection;
    }

    /**
     * Sets new user.
     *
     * @param user New value of user.
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Gets user.
     *
     * @return Value of user.
     */
    public User getUser() {
        return user;
    }
}
