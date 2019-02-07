package clientServerTest;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class ClientTest {


    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 9090);
//            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//            String response = in.readLine();
            var out = new PrintWriter(socket.getOutputStream(), true);
            out.println("lgn;gibb;sml12345");
//            System.out.println(response);
        }catch(IOException e){

        }

    }

}
