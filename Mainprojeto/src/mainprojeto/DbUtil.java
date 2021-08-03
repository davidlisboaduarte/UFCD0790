package mainprojeto;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DbUtil implements iDbUtil {

    final String DB_URL = "jdbc:mysql://localhost:3394/mainprojeto";
    final String USER = "root";
    final String PASS = "qwerty321";

    @Override
    public Connection getConnection() throws SQLException, NullPointerException {
        Driver mysqlDriver = null;
        Connection conn = null;

        mysqlDriver = new com.mysql.cj.jdbc.Driver();

        DriverManager.registerDriver(mysqlDriver);

        conn = DriverManager.getConnection(DB_URL, USER, PASS);

        return conn;
    }

    @Override
    public void closeConnection(Connection conn) {
        try {
            conn.close();
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }

}
