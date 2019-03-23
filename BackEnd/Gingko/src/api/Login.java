package api;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Login extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3976013846451264196L;

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
		    throws IOException{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setCharacterEncoding("UTF-8");

		String mail = request.getParameter("mail");
		String passport = request.getParameter("passport");
		
		String sql = "select * from user where mail = '"+mail+"'";
		String password = driver.Query.queryOne(sql, "passport");
		String result;
		if(password==null){
			result = "{\"status\":\"404\"}";
		}else if(password.equals(passport)){
			String identify = driver.Query.queryOne(sql, "identify");
			String nickname = driver.Query.queryOne(sql, "nickname");
			result = "{\"status\":\"success\",\"identify\":\""+identify+"\",\"nickname\":\""+nickname+"\"}";
		}else{
			result = "{\"status\":\"fail\"}";
		}
		
		function.Output.write(response, result);
	}
}
