package janc.test;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientStressTest {


    public static void main(String[] args) {
        try {
            Socket socket = new Socket("192.168.52.18", 9980);
            socket.setKeepAlive(true);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);


            out.println("lgn;Gibb;sml12345");
            //out.flush();
            Scanner scanner = new Scanner(System.in);
            ClientReciver listener = new ClientReciver(socket);
            listener.start(); //Runs in its own thread ;)

            while (!socket.isClosed()) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {

                }
                out.println("msg;null;Gibb;null;" + System.currentTimeMillis() + ";Hallo Welt " + System.currentTimeMillis() + ";");
            }


            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while (!socket.isClosed()) {
                String response = in.readLine();
                if (response != null) {
                    System.out.println(response);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
