package janc.backend;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLException;
import java.util.List;

public class RequestMessages extends JancCommand {

    private String timestamp;

    /**
     * The constructor of the method.
     *
     * @param parts      The command parts. They will forwarded to the JnacCommand super constructor. The constructor pass it further to the parseFromString method from actual instance.
     * @param connection The connection object, that contains information about the connection to the user.
     * @throws MalformedCommandException
     */
    public RequestMessages(String[] parts, ServerClientConnection connection) throws MalformedCommandException {
        super(parts, connection);
    }

    @Override
    void handle() {
        try {
            MessageJDBCDao messageDB = new MessageJDBCDao(ConnectionFactory.getInstance().getConnection());
            List<Message> messages = messageDB.getAllMessagesSince(this.timestamp);
            var out = new PrintWriter(this.getConnection().getClientConnection().getOutputStream(), true);
            for (Message message : messages) {
                out.println("dsb;" + message.getUsername() + ";" + message.getTimestamp() + ";" + message.getText() + ";");
            }
        } catch (SQLException | IOException e) {

        }
    }

    @Override
    void send(Socket socket) throws IOException {

    }

    @Override
    void parseFromString(String[] parts) throws MalformedCommandException {
        if (parts.length == 3) {
            this.timestamp = parts[2];
        } else {
            throw new MalformedCommandException("Wrong number of parameters.");
        }
    }
}
