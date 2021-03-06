package driver;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Delete {
	public static String doDelete (String sql){
		System.out.println(sql);
        String result = null;
        Connection connection = Driver.getConnection();
        try {
            Statement statement = connection.createStatement();
            statement.execute(sql);
            result = "finished";
        } catch (SQLException e) {
            System.out.println("exception error for delete");
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
