package sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class Server
{
    ServerSocket listener;
    JancProtocolHandler protocol;

    //Creating Server Instance
    public static void main(String[] args) {
        Server server = new Server();
    }

    //Listening for requests ("commands")
    public Server(){
        this.protocol = new JancProtocolHandler();
        try {
            this.listener = new ServerSocket(9090);
            System.out.println("Server is running on port " + this.listener.getLocalPort());
            while (true) {
                try (var socket = listener.accept()) {
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String response = in.readLine();
                    if (response != null) {
                        this.protocol.ParseFromString(response);
                    }
                }
            }
        }catch (IOException e){}

    }
}
