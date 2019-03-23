package driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Driver {
	private static String url = "jdbc:mysql://localhost:3306/gingko?useUnicode=true&characterEncoding=utf-8&useSSL=false";
	private static String user = "Taylor";
	private static String passport = "icanfly520";
	
	public static Connection getConnection(){
		Connection connction = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connction = DriverManager.getConnection(url,user,passport);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connction;
	}
}
