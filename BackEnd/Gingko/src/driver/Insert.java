package driver;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Insert {
	public static String doInsert (String sql){
		System.out.println(sql);
        String result = null;
        Connection connection = Driver.getConnection();
        try {
            Statement statement = connection.createStatement();
            statement.execute(sql);
            result = "finished";
        } catch (SQLException e) {
        	e.printStackTrace();
            System.out.println("exception error for insert");
            result = "unfinished";
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("connection closed error");
            }
        }
        return result;
    }
}
