package api;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import function.Resize;

public class AddUrl extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5960691689430949339L;

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doAction(request,response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doAction(request,response);
	}

	private void doAction(HttpServletRequest request, HttpServletResponse response) 
			throws UnsupportedEncodingException{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setCharacterEncoding("UTF-8");
		
		String identify = request.getParameter("identify");
		String name = request.getParameter("name");
		String url = request.getParameter("url");
		String img = request.getParameter("img");
		System.out.println(identify);
		
		//图片压缩
		Resize resize = new Resize();
		img = resize.resizeImg(img,name);
		String type = request.getParameter("type");
		
		String sql = "insert into url (identify,name,url,img,type) values ('"+identify+"','"+name+"','"+url+"','"+img+"','"+type+"')";
		String result = driver.Insert.doInsert(sql);
		result = "{\"status\":\""+result+"\"}";
		
		function.Output.write(response, result);
	}
}
