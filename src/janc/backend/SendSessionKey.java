package janc.backend;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class SendSessionKey extends JancCommand{

    private String username;
    private String sessionKey;

    public SendSessionKey(String[] parts, Socket source) throws MalformedCommandException {
        super(parts, source);
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
