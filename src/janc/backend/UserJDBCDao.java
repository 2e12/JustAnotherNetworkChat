package janc.backend;

import java.sql.*;

public class UserJDBCDao implements UserDao {

    private java.sql.Connection connection = null;

    /**
     * Constructor set the connection
     * @param connection DB Connection that needed to be set.
     */
    public UserJDBCDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insertUser(User user) {
        try {
            String sql = "INSERT INTO user (username, password, salt) VALUES (?, ?, ?)";
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getSalt());
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public LoginState checkLoginCredentials(User user) {
        try {
            String sql = "SELECT * FROM user WHERE username = ?";
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ps.setString(1, user.getUsername());

            ResultSet result = ps.executeQuery();
            if (result.next()) {
                String password = result.getString("password");
                if (password.equals(user.getPassword())) {
                    return LoginState.correct;
                } else {
                    return LoginState.worngPassword;
                }
            } else {
                return LoginState.noUser;
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }
}
