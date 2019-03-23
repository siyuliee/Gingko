package function;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.google.gson.JsonParser;

public class Download {
	public static String getUrl(String json,String id){
		String result = null;
		JsonParser jsonParser = new JsonParser();
		String url = jsonParser.parse(json).getAsJsonObject().get("url").getAsString();
		if(url.equals("null")){
			result = "{\"url\":null}";
		}else{
			download(url,id);
			result = "{\"url\":\"https://www.gingko.tech/music/"+id+".mp3\"}";
		}
		return result;
	} 
	public static void download(String url,String id){
		InputStream is = Http.getInputStream(url);
    	File f = new File("/home/admin/Apache/webapps/music/"+id+".mp3");
    	OutputStream out = null;
    	try {
    		out = new FileOutputStream(f);
			int len ;
			byte[]  buffer = new byte[2048];
			while ((len=is.read(buffer))!=-1){
                out.write(buffer, 0, len);
                out.flush();
            }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(out != null){
				try {
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(is != null){
				try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}