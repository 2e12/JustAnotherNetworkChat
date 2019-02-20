package janc.backend;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class SendSessionKey extends JancCommand{

    private String username;
    private String sessionKey;

    /**
     * The constructor of the method.
     *
     * @param parts      The command parts. They will forwarded to the JnacCommand super constructor. The constructor pass it further to the parseFromString method from actual instance.
     * @param connection The connection object, that contains information about the connection to the user.
     * @throws MalformedCommandException
     */
    public SendSessionKey(String[] parts, ServerClientConnection connection) throws MalformedCommandException {
        super(parts, connection);
    }

    @Override
    void handle() {

    }

    @Override
    void send(Socket socket) throws IOException {
        var out = new PrintWriter(socket.getOutputStream(), true);
        out.println("key;" + this.sessionKey + ";");
    }

    @Override
    void parseFromString(String[] parts) throws MalformedCommandException {
        if(parts.length == 3){
            this.username = parts[1];
            this.sessionKey = parts[2];
        } else {
            throw new MalformedCommandException("Wrong number of paramteres");
        }
    }
}
