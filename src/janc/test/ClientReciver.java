package janc.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientReciver extends Thread{

    private Socket socket;


    public ClientReciver(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            while (!this.socket.isClosed()) {
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
