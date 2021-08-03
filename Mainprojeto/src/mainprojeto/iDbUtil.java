package mainprojeto;

import java.sql.Connection;
import java.sql.SQLException;

public interface iDbUtil {

    public Connection getConnection() throws SQLException, NullPointerException;

    public void closeConnection(Connection conn);
}
