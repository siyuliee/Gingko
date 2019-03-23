package function;

public class Identify {
	public static String getIdentify(){
		String identify;
		char c=65;
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<64;i++){
			char alpha = (char) (c+Math.floor(Math.random()*26));
			sb.append(alpha);
		}
		identify = sb.toString();
		return identify;
	}
}
