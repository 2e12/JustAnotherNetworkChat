package janc.backend;

import java.sql.*;

public class UserJDBCDao implements UserDao {

    private java.sql.Connection connection = null;

    /**
     * Constructor set the connection
     *
     * @param connection DB Connection that needed to be set.
     */
    public UserJDBCDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    /**
     * This function creates a new user in the database.
     * @param user This is the user, the needed to be inserted
     * @return void
     */
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
    /**
     * Checks if the login credentials are right.
     * @param user This is the user, whose credentials needed to be checked
     * @return loginstate This indicates, if the user is logged in, the password is wrong or the user doesn't exist
     */
    public loginstate checkLoginCredentials(User user) {
        try {
            String sql = "SELECT * FROM user WHERE username = ?";
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ps.setString(1, user.getUsername());

            ResultSet result = ps.executeQuery();
            if (result.next()) {
                String password = result.getString("password");
                if (password.equals(user.getPassword())) {
                    return loginstate.correct;
                } else {
                    return loginstate.worngpassword;
                }
            } else {
                return loginstate.nouser;
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }
}
