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


    public static void main(String[] args) {
        Server server = new Server();
    }
    public Server(){
        try {
            this.listener = new ServerSocket(9090);
            System.out.println("Server is running on port " + this.listener.getLocalPort());
            while (true) {
                try (var socket = listener.accept()) {
                    var out = new PrintWriter(socket.getOutputStream(), true);
                    out.println(new Date().toString());
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String response = in.readLine();
                    System.out.println(response);
                }
            }
        }catch (IOException e){}

    }
}
