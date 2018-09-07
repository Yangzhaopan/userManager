package cn.pzy.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ManageUsers extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.println("<img src='images/hsmall.gif'/><hr/>");
		out.println("管理用户");
		out.println("<a href='/UserManager2/loginServlet'>安全退出</a><br/>");
		int pageNow=2;//当前页，设默认值为1
		int pageSize=3;//每页现实的记录数量
		int pageCount=1;//总页数
		int rowCount=1;//记录数
		String pc=request.getParameter("pageCount");
		if(pc!=null){
		 pageNow=Integer.parseInt(pc);
		}
		Connection conn=null;
		PreparedStatement pst=null;
		ResultSet rst=null;
		try {
			//1.反射获取驱动类，注册 JDBC 驱动；
			Class.forName("com.mysql.jdbc.Driver");
			//2.获得连接
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/User","root","123123");
			//3.创建PreparedSatement
			pst=conn.prepareStatement("select ts.* FROM(select *from userInfo ORDER BY id) ts limit "+(pageNow-1)*pageSize+","+pageSize+";");
			//5.执行操作
			rst=pst.executeQuery();
			out.println("<table border='1px' width='307px' bgcolor='#F0F8FF'>");
			out.println("<th>用户名</th><th>姓名</th><th>邮箱</th>");
			//根据结果处理
			while(rst.next()){
				out.println("<tr><td>"+rst.getInt(1)+"</td>"+"<td>"+rst.getString(2)+"</td>"+"<td>"+rst.getString(3)+"</td></tr>");
			}
			out.println("</table>");
			pst=conn.prepareStatement("select count(*) from userInfo;");
			rst=pst.executeQuery();
			rst.next();
			rowCount=rst.getInt(1);
			pageCount=rowCount%pageSize==0? rowCount/pageSize:rowCount/pageSize+1;//总页数
			for(int i=1;i<=pageCount;i++){
				out.println("<<a href='/UserManager2/ManageUsers?pageCount="+i+"'>"+i+"</a>>");
			}
			
			out.println("<br/><img src='images/b_small.gif'/><hr/>");
		} catch (Exception e) {
			// TODO: handle exception
//			System.out.println("数据库打开错误！");
			e.printStackTrace();
		}finally{
			if(rst!=null){
				try {
					rst.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				rst=null;
			}
			if(pst!=null){
				try {
					pst.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				pst=null;
			}
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				conn=null;
			}
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

}
