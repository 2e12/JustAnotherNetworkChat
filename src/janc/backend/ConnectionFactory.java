package janc.backend;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class ConnectionFactory {

    private static ConnectionFactory instance;
    private static Connection connection;

    private final String dbURL = "jdbc:mysql://127.0.0.1:3306/jancdb?serverTimezone=UTC&useSSL=false";
    private final String dbUser = "janc";
    private final String dbPwd = "sml12345";

    private ConnectionFactory() {
        try {
            this.connection = DriverManager.getConnection(this.dbURL, this.dbUser, this.dbPwd);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static ConnectionFactory getInstance() throws SQLException {
        if (ConnectionFactory.instance == null) {
            ConnectionFactory.instance = new ConnectionFactory();
        }
        return ConnectionFactory.instance;
    }
}
