package janc.backend;

import java.io.IOException;
import java.net.Socket;

public abstract class JancCommand {

    private ServerClientConnection connection;
    private Socket sourceSocket; //Some legacy code. I know...

    /**
     * The constructor of the method. Put everything where it should be.
     *
     * @param parts      The command parts. They will forwarded to the JnacCommand super constructor. The constructor pass it further to the parseFromString method from actual instance.
     * @param connection The command parts. They will forwarded to the JnacCommand super constructor. The constructor pass it further to the parseFromString method from actual instance.
     * @throws MalformedCommandException
     */
    public JancCommand(String[] parts, ServerClientConnection connection) throws MalformedCommandException {
        parseFromString(parts);
        setSourceSocket(connection.getClientConnection());
        setConnection(connection);
    }

    /**
     * Handles a request for an incoming command
     */
    abstract void handle();

    /**
     * Send a command to a spesific socket
     *
     * @param socket The connection to the reciver
     * @throws IOException
     */
    @Deprecated
    abstract void send(Socket socket) throws IOException;


    /**
     * Sets the default values for an command object.
     * @param parts The parts of the command. They will redirected in this function to the right place.
     * @throws MalformedCommandException
     */
    abstract void parseFromString(String[] parts) throws MalformedCommandException;

    /**
     * Gets connection.
     *
     * @return Value of connection.
     */
    public ServerClientConnection getConnection() {
        return connection;
    }

    /**
     * Sets new sourceSocket.
     *
     * @param sourceSocket New value of sourceSocket.
     */
    public void setSourceSocket(Socket sourceSocket) {
        this.sourceSocket = sourceSocket;
    }

    /**
     * Gets sourceSocket.
     *
     * @return Value of sourceSocket.
     */
    public Socket getSourceSocket() {
        return sourceSocket;
    }

    /**
     * Sets new connection.
     *
     * @param connection New value of connection.
     */
    public void setConnection(ServerClientConnection connection) {
        this.connection = connection;
    }
}
