package cn.pzy.contorller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.pzy.domain.User;
import cn.pzy.service.UserService;

import java.sql.*;

public class loginClServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//post方法解决中文乱码
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String id=request.getParameter("id");
		String password=request.getParameter("password");
		PrintWriter out=response.getWriter();
		//到数据库去验证
//		Connection conn=null;
//		PreparedStatement pst=null;
//		ResultSet rst=null;
//		try {
//			//1.反射获取驱动类，注册 JDBC 驱动；
//			Class.forName("com.mysql.jdbc.Driver");
//			//2.获得连接
//			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/User","root","123123");
//			//3.创建PreparedSatement
//			pst=conn.prepareStatement("select * from userInfo where id=? and password=?");
//			//4.设置
//			pst.setObject(1, id);
//			pst.setObject(2, password);
//			//5.执行操作
//			rst=pst.executeQuery();
//			//根据结果处理
		    User user=new User();
		    user.setId(Integer.parseInt(id));
		    user.setPassword(Integer.parseInt(password));
		    UserService uss=new UserService();
			if(uss.checkUser(user) != null){
				//重定向,有用户且正确则成功
				request.getRequestDispatcher("/mainFrame").forward(request, response);
			}else{
				request.setAttribute("err", "用户名或者密码有误");
				request.getRequestDispatcher("/loginServlet").forward(request, response);
			}
//		} catch (Exception e) {
//			// TODO: handle exception
////			System.out.println("数据库打开错误！");
////			request.setAttribute("err", "用户名或者密码出错！");
//			e.printStackTrace();
//		}finally{
//			if(rst!=null){
//				try {
//					rst.close();
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				rst=null;
//			}
//			if(pst!=null){
//				try {
//					pst.close();
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				pst=null;
//			}
//			if(conn!=null){
//				try {
//					conn.close();
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				conn=null;
//			}

		}
		//out.println(username);
		//out.println(password);
//		//简单的验证
//		if("pzy".equals(username)&&"123".equals(password)){
//			//进行重定向或者转发forward（这个在上下文里） 格式如下
//			response.sendRedirect("/UserManager/mainFrame?uname="+username+"&pwd="+password);
//		}else{
//			response.sendRedirect("/UserManager/loginServlet");
//		}

//	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

}
