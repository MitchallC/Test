package Database;

import java.sql.*;

public class dbManager
{
    public static Connection getConnection() throws SQLException
    {
        DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
        return DriverManager.getConnection("jdbc:oracle:thin:@crusstuora1.staffs.ac.uk:1521:stora", "c015678g", "C015678G");
    }
}
