import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class HotelDB {
    static Connection connection = null;
    public static Connection connect(){
        try{
            String url = "jdbc:mysql://localhost:3306/hotel_db";
            String userName = "root";
            String password = "HeyMySQL@2112!";

            connection = DriverManager.getConnection(url,userName,password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
}
