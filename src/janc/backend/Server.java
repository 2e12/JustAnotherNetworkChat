package janc.backend;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server
{

    /**
     * The startpoint of the of the server backend part
     *
     * @param args Arguments. Not relevant. Will be ignored anyway.
     */
    public static void main(String[] args) {
        Server server = new Server(false);
    }

    /**
     * Searches for clients. When one client is found, a socket connection will be created.
     */
    public Server(Boolean testserver){
        try {
            ServerSocket listener = new ServerSocket(9980);
            System.out.println("Server is running on port " + listener.getLocalPort());
            while (true) {
                if (testserver == true) {
                    System.exit(0);
                }
                //Waiting for client
                Socket socket = listener.accept();
                socket.setKeepAlive(true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String response = in.readLine();
                if (response != null) {
                    ServerClientConnection connection = new ServerClientConnection(socket, null);
                    JancProtocolHandler.getInstance().parseFromString(response, connection);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
