package janc.test;


import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientTest {


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
                String input = scanner.nextLine();
                out.println("msg;null;Gibb1;null;" + System.currentTimeMillis() + ";" + input + ";");
            }


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
