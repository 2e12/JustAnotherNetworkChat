package clientServerTest;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;

public class ClientTest {


    public static void main(String[] args) {
        try {
            Socket socket = new Socket("192.168.52.18", 9981);
            socket.setKeepAlive(true);
            var out = new PrintWriter(socket.getOutputStream(), true);
            out.println("lgn;gibb;sml12345");
            Scanner scanner = new Scanner(System.in);

            //while (!socket.isClosed()) {
            //    String input = scanner.nextLine();
            //    out.println("msg;null;gibb;null;" + System.currentTimeMillis() + ";" + input + ";");
            //}

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

}
