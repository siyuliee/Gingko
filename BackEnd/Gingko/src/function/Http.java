package function;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Http {
    public static String getMothod(String url) {
        String result=getData(url);
        return result;
    }
    public static InputStream getInputStream(String url){
        InputStream inputStream = null;
        try {
            URL Url = new URL(url);
            HttpURLConnection connection= (HttpURLConnection) Url.openConnection();
            connection.setDoInput(true);
            connection.setRequestMethod("GET");
            connection.connect();
            
            inputStream = connection.getInputStream();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputStream;
    }
    
    private static String getData(String url){
        String result=null;
        InputStream inputStream = getInputStream(url); 
        result = readStream(inputStream);
        return result;
    }

    private static String readStream(InputStream inputStream){
        byte[] buffer = new byte[1024];
        int len ;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        String result = null;
        try {
            while ((len=inputStream.read(buffer))!=-1){
                bos.write(buffer,0,len);
            }
            result = new String(bos.toByteArray(),"UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
        	if(inputStream!=null){
        		try {
					inputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
        }
        return result;
    }
    public static void main(String[] args){
    	System.out.println(getMothod("http://www.gingko.tech/Gingko/servlet/Music?id=1307186170"));
    }
}
