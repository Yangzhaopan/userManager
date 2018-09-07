package cn.pzy.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class loginServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		//添加图片，美化界面细节src问题
		out.println("<img src='images/hsmall.gif'/><hr/>");
		//action应该写web应用名称和他的serlet的url
		out.println("<form action='/UserManager2/loginClServlet' method='post'>");
		out.println("用户名:"+"<input type='text' name='id'/></br>");
		out.println("密码:"+"<input type='text' name='password'/></br>");
		out.println("<input type='submit' value='登陆'/></br>");
		out.println("</form>");
		String errInfo=(String)request.getAttribute("err");
		if(errInfo!=null){
		out.println("<font size='3' color='red' >"+errInfo+"</font>");
		}
		out.println("<hr/><img src='images/bsmall.gif'/><hr/>");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

}
