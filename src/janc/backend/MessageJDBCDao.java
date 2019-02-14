package janc.backend;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class MessageJDBCDao implements MessageDao {

    Connection connection;

    /**
     * Constructor set the connection
     *
     * @param connection DB Connection that needed to be set.
     */
    public MessageJDBCDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    /**
     * This function addes a new message.
     * @param message This is the message, the needed to be inserted.
     * @return void
     */
    public void insertMessage(Message message) {
        try {
            String sql = "INSERT INTO message (userid, timestamp, text) VALUES (?, ?, ?)";
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ps.setInt(1, message.getUserid());
            ps.setTimestamp(2, new Timestamp(Long.parseLong(message.getTimestamp())));
            ps.setString(3, message.getText());
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }

    @Override
    /**
     * This function loads all messages from the database.
     * @return List<Message> A list with all messages.
     */
    public List<Message> getAllMessages() {
        List<Message> messages = new ArrayList<Message>();
        try {
            String sql = "SELECT * FROM messages";
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ResultSet result = ps.executeQuery();

            Message message = null;
            while (result.next()) {
                message = new Message();
                message.setId(result.getInt(1));
                message.setUserid(result.getInt(2));
                message.setTimestamp(result.getTimestamp(3).toString());
                message.setText(result.getString(4));

                messages.add(message);
            }
        } catch (SQLException e) {

        }
        return messages;
    }
}
