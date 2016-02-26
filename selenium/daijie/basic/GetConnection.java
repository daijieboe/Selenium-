package daijie.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GetConnection {
	/*
	 * 本方法为链接SIT/PRE数据库之用
	 * 输入参数：
	 *         prefix（目标数据库的前缀，PRE还是SIT）
	 * 输出参数：
	 *         Connection（数据库连接完毕之后的工具类）
	 * @Author DaiJie
	 * @Date 2016-2-4
	 */
	public Connection getConnection(String prefix) {
		// 定义一个连接对象
		Connection conn = null;
		switch (prefix) {
		case "sit": {
			// 定义连接数据库的URL资源
			String url = "jdbc:oracle:thin:@10.80.20.135:1521:scdev";
			// 定义连接数据库的用户名称与密码
			String username = "scadm";
			String password = "adm2014";
			// 加载数据库连接驱动
			String className = "oracle.jdbc.driver.OracleDriver";
			try {
				Class.forName(className);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			// 获取数据库的连接对象
			try {
				conn = DriverManager.getConnection(url, username, password);
			} catch (SQLException e) {
				e.printStackTrace();
			}

			if (conn != null) {
				System.out.println("数据库链接成功!");
			} else {
				System.out.println("数据库链接失败!");
			}
			break;

		}
		case "pre": {
			// 定义连接数据库的URL资源
			String url = "jdbc:oracle:thin:@10.80.20.187:1521:scpre";
			// 定义连接数据库的用户名称与密码
			String username = "scadm";
			String password = "adm2014";
			// 加载数据库连接驱动
			String className = "oracle.jdbc.driver.OracleDriver";
			try {
				Class.forName(className);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			// 获取数据库的连接对象
			try {
				conn = DriverManager.getConnection(url, username, password);
			} catch (SQLException e) {
				e.printStackTrace();
			}

			if (conn != null) {
				System.out.println("数据库链接成功!");
			} else {
				System.out.println("数据库链接失败!");
			}
			break;
		}
		default: {
			System.out.println("prefix can't be recognized!");
		}
		}
		return conn;
	}

}
