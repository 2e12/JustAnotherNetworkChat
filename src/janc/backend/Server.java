package janc.backend;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server
{

    private List<Socket> socketList = new ArrayList<Socket>();

    //Creating Server Instance
    public static void main(String[] args) {
        Server server = new Server();
    }

    //Listening for initial requests ("commands")
    public Server(){

        try {
            ServerSocket listener = new ServerSocket(9980);
            System.out.println("Server is running on port " + listener.getLocalPort());
            while (true) {
                //Waiting for client
                Socket socket = listener.accept();
                socket.setKeepAlive(true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String response = in.readLine();
                if (response != null) {
                    JancProtocolHandler.getInstance().ParseFromString(response, socket, null);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
