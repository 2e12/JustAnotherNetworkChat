package janc.backend;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Random;

public class RequestSessionKey extends JancCommand{
    String username;
    String password;

    public RequestSessionKey(String[] parts, Socket source) {
        super(parts, source);
    }

    @Override
    void handle() {
        String[] parts = new String[]{"key", this.username, null};
        SendSessionKey key = new SendSessionKey(parts, this.getSourceSocket());
        try {
            User user = new User();
            user.setUsername(this.username);
            user.setPassword(this.password);
            UserJDBCDao userLogin = new UserJDBCDao(ConnectionFactory.getInstance().getConnection());

            loginstate state = userLogin.checkLoginCredentials(user);
            if (state != loginstate.worngpassword) {
                if (state == loginstate.nouser) {
                    userLogin.insertUser(user);
                }
                key.send(this.getSourceSocket());
                JancProtocolHandler.getInstance().putUserConnection(this.username, this.getSourceSocket(), null);
                System.out.println("[Login] Hello " + this.username + this.getSourceSocket().getInetAddress());
            } else {
                getSourceSocket().close();
            }
        } catch (IOException | SQLException e) {

        }

    }

    @Override
    void send(Socket socket) throws IOException {
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        out.println("lgn;gibb;sml12345");

    }

    @Override
        //Set Parameters
    void parseFromString(String[] parts) {
        if(parts.length == 3) {
            this.username = parts[1];
            this.password = parts[2];
        }
    }
}
