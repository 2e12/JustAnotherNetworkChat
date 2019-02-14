package janc.backend;

import java.io.IOException;
import java.net.Socket;
import java.sql.Connection;
import java.sql.SQLException;

public class SendMessage extends JancCommand{


    String sessionKey;
    String fromUsername;
    String toUsername;
    String timestamp;
    String message;

    //Init neeaded parameters
    public SendMessage(String[] parts, Socket source) throws MalformedCommandException {
        super(parts, source);
    }

    @Override
    void handle() {
        System.out.println("[Message] " + this.fromUsername + ": " + this.message);
        JancProtocolHandler.getInstance().broadcastToAllClients("dsb;" + this.fromUsername + ";" + this.timestamp + ";" + this.message);
        try {
            Connection connection = ConnectionFactory.getInstance().getConnection();
            MessageJDBCDao protocol = new MessageJDBCDao(connection);
            UserJDBCDao userDB = new UserJDBCDao(ConnectionFactory.getInstance().getConnection());
            Message message = new Message();
            message.setText(this.message);
            message.setTimestamp(this.timestamp);
            message.setUserid(userDB.getUserIdByName(this.fromUsername));
            protocol.insertMessage(message);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    void send(Socket socket) throws IOException {

    }

    @Override
    void parseFromString(String[] parts) throws MalformedCommandException {
        if(parts.length == 6) {
            this.sessionKey = parts[1];
            this.fromUsername = parts[2];
            this.toUsername = parts[3];
            this.timestamp = parts[4];
            this.message = parts[5];
            if (!(this.message.matches(".*\\w.*"))) {
                throw new MalformedCommandException("Message can't be empty");
            }
        } else {
            throw new MalformedCommandException("Wrong number of paramteres");
        }
    }
}
