package clientServerTest;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;

public class ClientTest {


    public static void main(String[] args) {
        try {
            Socket socket = new Socket("192.168.52.18", 9981);
            var out = new PrintWriter(socket.getOutputStream(), true);
            out.println("lgn;gibb;sml12345");
            Scanner scanner = new Scanner(System.in);


            Boolean run = true;
            while (run) {
                out.println("msg;null;gibb;null;null;" + scanner.next() + ";");
            }

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String response = in.readLine();
            System.out.println(response);
        }catch(IOException e){

        }

    }

}
