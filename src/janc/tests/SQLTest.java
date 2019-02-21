package janc.tests;

import janc.backend.ConnectionFactory;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class SQLTest {

    @Test
    public void testSQL() {
        try {
            Connection connectionFactory = ConnectionFactory.getInstance().getConnection();
        } catch (SQLException se) {

        }
    }
}