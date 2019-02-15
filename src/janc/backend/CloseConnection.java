package janc.backend;

import java.io.IOException;
import java.net.Socket;

public class CloseConnection extends JancCommand{

    /**
     * The constructor of the method.
     *
     * @param parts      The command parts. They will forwarded to the JnacCommand super constructor. The constructor pass it further to the parseFromString method from actual instance.
     * @param connection The connection object, that contains information about the connection to the user.
     * @throws MalformedCommandException
     */
    public CloseConnection(String[] parts, ServerClientConnection connection) throws MalformedCommandException {
        super(parts, connection);
        try {
            connection.getClientConnection().close();
        }catch (IOException e){

        }
    }

    @Override
    void handle() {

    }

    @Override
    void send(Socket socket) throws IOException {

    }

    @Override
    void parseFromString(String[] parts) throws MalformedCommandException {
        if (parts.length != 1) {
            throw new MalformedCommandException("Wrong number of paramteres");
        }
    }
}
