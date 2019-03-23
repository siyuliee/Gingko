package driver;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Query {
	public static String queryOne(String sql,String name){
		System.out.println(sql);
		String result = null;
		Connection connection = Driver.getConnection();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet data = statement.executeQuery(sql);
            while (data.next()){
                result = data.getString(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
        	 try {
                 connection.close();
             } catch (SQLException e) {
                 System.out.println("connection closed error");
             }
        }
        return result;
	}
	
    public static String doQuery(String sql){
    	System.out.println(sql);
        String result;
        String res;
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        Connection connection = Driver.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet data = statement.executeQuery(sql);
            ResultSetMetaData resM = data.getMetaData();
            HashMap<String,String> hashMap = new HashMap<String, String>();
            ArrayList<String> arrayList = new ArrayList<String>();
            while (data.next()) {
                for (int i = 1; i < resM.getColumnCount()+1; i++) {
                    String label = resM.getColumnName(i);
                    String re = data.getString(label);
                    hashMap.put(label,re);
                }
                res = gson.toJson(hashMap);
                arrayList.add(res);
            }
            res = arrayList.toString();

        } catch (SQLException e) {
            System.out.println("exception error for query");
            res = "\"exception\"";
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("connection closed error");
            }
        }
        result = "{\"result\":"+res+"}";
        return result;
    }
}
