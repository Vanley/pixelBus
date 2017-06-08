package pixel.bus.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by vanley on 08/06/2017.
 */
public class DerbyConnectionUtility {

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        } catch (ClassNotFoundException e) {
            throw new SQLException(e);
        }
        Connection connection = DriverManager.getConnection(
                "jdbc:derby:db;create=true", "app",
                "app");
        return connection;
    }

//    public static void main(String[] args) throws SQLException{
//        getConnection();
//    }

}

