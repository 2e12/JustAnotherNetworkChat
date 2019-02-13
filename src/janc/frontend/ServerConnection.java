package janc.frontend;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.MessageFormat;

public class ServerConnection {
    private static PrintWriter out;
    private Listener listener;
    private String uName;

    public Listener getListener() {
        return listener;
    }

    public String getuName() {
        return uName;
    }

    public ServerConnection(String ipAdress, String uName, String pWord) {
        try {
            this.uName = uName;
            Socket socket = new Socket(ipAdress, 9980);
            socket.setKeepAlive(true);
            out = new PrintWriter(socket.getOutputStream(), true);
            out.println(MessageFormat.format("lgn;{0};{1}", uName, pWord));

            listener = new Listener(socket);
            listener.start();
        }catch(IOException e){
            e.printStackTrace();
        }

    }

    public static void sendMessageToServer(String msg, String uName) {
        String input = msg;
        out.println("msg;null;" + uName + ";null;" + System.currentTimeMillis() + ";" + input + ";");
    }
}