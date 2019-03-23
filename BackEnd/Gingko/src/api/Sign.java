package api;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Sign extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4865021856820586780L;

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
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		response.setHeader("Access-Control-Allow-Origin", "*");
		
		String nickname = request.getParameter("nickname");
		String mail = request.getParameter("mail");
		String passport = request.getParameter("passport");
		String result;
		String check_only = "select mail from user where mail = '"+mail+"'";
		String check_result = driver.Query.queryOne(check_only, "mail");
		if(check_result!=null){
			result = "exist";
			result = "{\"status\":\""+result+"\"}";
		}else{
			String identify = function.Identify.getIdentify();
			String sql = "insert into user (nickname,mail,passport,identify) values ('"+nickname+"','"+mail+"','"+passport+"','"+identify+"')";
			result = driver.Insert.doInsert(sql);
			result = "{\"status\":\""+result+"\",\"identify\":\""+identify+"\"}";
		}
		
		function.Output.write(response, result);
	}
}
