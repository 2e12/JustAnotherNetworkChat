package janc.backend;

import java.io.IOException;
import java.net.Socket;

public abstract class JancCommand {

    private ServerClientConnection connection;
    private Socket sourceSocket; //Some legacy code. I know...


    //Pass parameters to the parseFromString Function
    public JancCommand(String[] parts, ServerClientConnection connection) throws MalformedCommandException {
        parseFromString(parts);
        setSourceSocket(connection.getClientConnection());
        setConnection(connection);
    }

    abstract void handle();

    abstract void send(Socket socket) throws IOException;

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
