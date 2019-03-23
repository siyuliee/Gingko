package function;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

import javax.imageio.ImageIO;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Resize {
	private String path = "/home/admin/Buffer/";
	public String resizeImg(String base64,String name){
		String orignal_path = Base64ToImg(base64);
		Date date = new Date();
		long time = date.getTime();
		String formImg_path = path+ name+ "_" + time +".jpg";
        //读取图片
		FileInputStream fileInput = null;
		BufferedInputStream bufferedInput = null;
		try {
			fileInput = new FileInputStream(orignal_path);
			bufferedInput = new BufferedInputStream(fileInput);
			//字节流转图片对象
	        Image bi =ImageIO.read(bufferedInput);
	      //构建图片流
	        BufferedImage tag = new BufferedImage(200, 200, BufferedImage.TYPE_INT_RGB);
	        //绘制改变尺寸后的图
	        tag.getGraphics().drawImage(bi, 0, 0,200, 200, null);  
	        //输出流
	        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(formImg_path));
	        ImageIO.write(tag, "JPG",out);
	        out.flush();
	        out.close();
	        bufferedInput.close();
	        fileInput.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String resize_base64 = ImgToBase64(formImg_path);
        return resize_base64;
	}
	
	private synchronized String Base64ToImg(String base64){
		base64 = base64.split(",")[1];
		if (base64 == null){
			return null;
		}
		String orignal_path = path + "orignal.jpg";
		System.out.println(orignal_path);
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			byte[] b = decoder.decodeBuffer(base64);
			for (int i = 0; i < b.length; ++i) {
			 	if (b[i] < 0) {
			 		b[i] += 256;
			 	}
			}
			File file = new File(orignal_path);
			if(!file.exists()){
				file.createNewFile();
			}
			OutputStream out = new FileOutputStream(file);
			out.write(b);
			out.flush();
			out.close();
			return orignal_path;
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}
	private String ImgToBase64(String path){
		StringBuilder base64 = new StringBuilder("data:image/jpg;base64,");
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(path);
			byte[] data = new byte[inputStream.available()];
			inputStream.read(data);
			inputStream.close();
			BASE64Encoder encoder = new BASE64Encoder();
		    String img = encoder.encode(data);
		    base64.append(img);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return base64.toString();
	}
}
