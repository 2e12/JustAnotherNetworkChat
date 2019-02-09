package sample;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class Listener extends Thread{
    public Listener(Socket socket) {
        //BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        while (!socket.isClosed()) {
            // response = in.readLine();
            //if (response != null) {
            //    System.out.println(response);
            //}
        }
    }
}
