package cn.pzy.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class mainFrame extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<img src='images/hsmall.gif'/><hr/>");
		out.println("欢迎你!<br/>");
		out.println("<a href='/UserManager2/ManageUsers'>管理界面</a><br/>");
		out.println("<a href='*'>添加用户</a><br/>");
		out.println("<a href='*'>查找用户</a><br/>");
		out.println("<a href='/UserManager2/loginServlet'>安全退出</a><br/>");
		out.println("<img src='images/b_small.gif'/><hr/>");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

}
