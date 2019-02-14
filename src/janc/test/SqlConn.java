package janc.test;

import janc.backend.ConnectionFactory;
import janc.backend.User;
import janc.backend.UserDao;
import janc.backend.UserJDBCDao;

import java.sql.Connection;
import java.sql.SQLException;

public class SqlConn {
    public static void main(String[] args) {
        try {
            //Let's create a user ;)
            User user = new User();
            user.setUsername("Gibb");
            user.setPassword("sml12345");
            user.setSalt("54321lms");

            //Very Complex SQL Stuff
            Connection conn = ConnectionFactory.getInstance().getConnection();
            UserJDBCDao data = new UserJDBCDao(conn);

            data.insertUser(user);
        }catch (SQLException e){

        }
    }
}
