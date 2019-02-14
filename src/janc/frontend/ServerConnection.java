package janc.frontend;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.MessageFormat;

public class ServerConnection {
    private static PrintWriter out;
    private Listener listener;
    private String uName;

    /**
     * This method is the constructor of the ServerConnection class.
     * It makes a new connection to a Server with a certain ip an the port 9980.
     * Also it sends a new login command to the connected server.
     * @param ipAdress the ip-adress to connect to the server.
     * @param uName the username to make it possible, that the server can identify his logged in users.
     * @param pWord the password for verify the identity of the user.
     */
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

    /**
     * This method is used for sending new messages to the server.
      * @param msg the message of the user.
     * @param uName the name of the sending user.
     */
    public static void sendMessageToServer(String msg, String uName) {
        String input = msg;
        out.println("msg;null;" + uName + ";null;" + System.currentTimeMillis() + ";" + input + ";");
    }

    /**
     * Gets uName.
     *
     * @return Value of uName.
     */
    public String getUName() {
        return uName;
    }

    /**
     * Gets listener.
     *
     * @return Value of listener.
     */
    public Listener getListener() {
        return listener;
    }
}