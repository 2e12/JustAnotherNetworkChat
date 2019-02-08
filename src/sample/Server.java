package sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Server
{
    ServerSocket listener;

    private List<Socket> socketList = new ArrayList<Socket>();

    //Creating Server Instance
    public static void main(String[] args) {
        Server server = new Server();
    }

    //Listening for initial requests ("commands")
    public Server(){

        try {
            this.listener = new ServerSocket(9981);
            System.out.println("Server is running on port " + this.listener.getLocalPort());
            while (true) {
                //Waiting for client
                var socket = listener.accept();
                socket.setKeepAlive(true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String response = in.readLine();
                if (response != null) {
                    //Pass command to the protocol handler
                    JancProtocolHandler.getInstance().ParseFromString(response, socket);
                    //new ServerClientConnection(socket, null).start();
                    //socketList.add(socket);

                }
            }
        } catch (IOException e) {
        }

    }
}
