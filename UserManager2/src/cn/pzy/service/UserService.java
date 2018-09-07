package cn.pzy.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.pzy.domain.User;

public class UserService {
	//检查用户合法性
	Connection conn=null;
	PreparedStatement pst=null;
	ResultSet rst=null;
	public User checkUser(User user){
		try {
			//1.反射获取驱动类，注册 JDBC 驱动；
			Class.forName("com.mysql.jdbc.Driver");
			//2.获得连接
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/User","root","123123");
			//3.创建PreparedSatement
			pst=conn.prepareStatement("select * from userInfo where id=? and password=?");
			//4.设置
			pst.setObject(1,user.getId());
			pst.setObject(2, user.getPassword());
			//5.执行操作
			rst=pst.executeQuery();
			//根据结果处理
			if(rst.next()){
				return user;
			}
			else{
				return null;
				}
		}catch (Exception e) {
			// TODO: handle exception
//			System.out.println("数据库打开错误！");
//			request.setAttribute("err", "用户名或者密码出错！");
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
		return user;
	}
}
		

