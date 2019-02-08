package sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.MessageFormat;
import java.util.Scanner;

public class Connection {
    private Socket socket;
    private static PrintWriter out;

    public Connection(String ipAdress, String uName, String pWord) {
        try {
            Socket socket = new Socket(ipAdress, 9981);
            socket.setKeepAlive(true);
            out = new PrintWriter(socket.getOutputStream(), true);
            out.println(MessageFormat.format("lgn;{0};{1}", uName, pWord));

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while (!socket.isClosed()) {
                String response = in.readLine();
                if (response != null) {
                    System.out.println(response);
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }

    }
    public static void sendMessageToServer(String msg) {
        String input = msg;
        out.println("msg;null;gibb;null;" + System.currentTimeMillis() + ";" + input + ";");
    }
}