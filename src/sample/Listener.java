package sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Listener extends Thread{
    private Socket socket;

    public Socket getSocket() {
        return socket;
    }

    public Listener(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            while (!this.socket.isClosed()) {
                String response = in.readLine();
                if (response != null) {
                    Model.setOutput(response);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
