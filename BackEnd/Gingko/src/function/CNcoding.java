package function;

import java.io.UnsupportedEncodingException;

public class CNcoding {
	public static String getRightCoding(String string){
		String result = null;
		try {
			result = new String(string.getBytes("ISO-8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
