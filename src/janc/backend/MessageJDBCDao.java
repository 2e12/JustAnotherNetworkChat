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
    public List<Message> getAllMessagesSince(String timestamp) {
        List<Message> messages = new ArrayList<Message>();
        try {
            String sql = "SELECT message.*, user.username FROM message " +
                    "JOIN user ON user.id = message.userid " +
                    "WHERE timestamp > ?";
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ps.setTimestamp(1, new Timestamp(Long.parseLong(timestamp)));
            ResultSet result = ps.executeQuery();

            Message message = null;
            while (result.next()) {
                message = new Message();
                message.setId(result.getInt(1));
                message.setUserid(result.getInt(2));

                String messageTimestamp = Long.toString(result.getTimestamp(3).getTime()); //Converts the date into the milliseconds timestamp format
                message.setTimestamp(messageTimestamp);
                message.setText(result.getString(4));
                message.setUsername(result.getString(5));

                messages.add(message);
            }
        } catch (SQLException e) {

        }
        return messages;
    }
}
