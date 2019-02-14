package janc.backend;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLException;

public class RequestSessionKey extends JancCommand{
    String username;
    String password;

    public RequestSessionKey(String[] parts, Socket source) throws MalformedCommandException {
        super(parts, source);
    }

    @Override
    void handle() {
        String[] parts = new String[]{"key", this.username, null};
        try {
            SendSessionKey key = new SendSessionKey(parts, this.getSourceSocket());
            try {
                User user = new User();
                user.setUsername(this.username);
                user.setPassword(this.password);
                UserJDBCDao userLogin = new UserJDBCDao(ConnectionFactory.getInstance().getConnection());

                LoginState state = userLogin.checkLoginCredentials(user);
                if (state != LoginState.worngPassword) {
                    boolean sendWelcomeMessage = false;
                    if (state == LoginState.noUser) {
                        userLogin.insertUser(user);
                        sendWelcomeMessage = true;
                    }
                    key.send(this.getSourceSocket());
                    JancProtocolHandler.getInstance().putUserConnection(user, this.getSourceSocket());
                    System.out.println("[Login] Hello " + user.getUsername() + this.getSourceSocket().getInetAddress());

                    if (sendWelcomeMessage) {
                        var out = new PrintWriter(this.getSourceSocket().getOutputStream(), true);
                        out.println("dsb;Hello;" + System.currentTimeMillis() + ";Hello " + this.username + "! As we can see, you haven't got an account yet.... We created one for you! :);");
                    }

                } else {
                    getSourceSocket().close();
                }
            } catch (IOException | SQLException e) {

            }
        } catch (MalformedCommandException me) {

        }
    }

    @Override
    void send(Socket socket) throws IOException {
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        out.println("lgn;gibb;sml12345");

    }

    @Override
        //Set Parameters
    void parseFromString(String[] parts) throws MalformedCommandException {
        if(parts.length == 3) {
            this.username = parts[1];
            this.password = parts[2];
        } else {
            throw new MalformedCommandException("Wrong number of paramteres");
        }
    }
}
