package miaoqian;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectPRE {

	public Connection connectPRE(){
	// TODO Auto-generated method stub
	//定义一个连接对象
			Connection conn=null;
			//定义连接数据库的URL资源
			String url="jdbc:oracle:thin:@10.80.20.187:1521:scpro";
			//定义连接数据库的用户名称与密码
			String username="scadm";
			String password="adm2014";
			//加载数据库连接驱动
			String className="oracle.jdbc.driver.OracleDriver";
			try {
			Class.forName(className);
			} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
			//获取数据库的连接对象
			try {
			conn=DriverManager.getConnection(url,username,password);
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
			
			if (conn != null) {
				System.out.println("数据库链接成功!"); 											
			} else {
				System.out.println("数据库链接失败!");
			}
			
			return conn;
	}
			
}
