package daijie.basic;

import java.sql.Connection;
import java.sql.ResultSet;

public class UserData {
	/*
	 * 本方法为用户名密码获取，包括前台中台登录
	 * 输入参数：Connection（用于连接数据库）
	 *          String type（登录类型，中台/普通登录/第三方登录等）
	 *          String userName（登录账户名） 
	 * 输出参数：String（用户名对应的密码）
	 * @Author DaiJie
	 * @Date 2016-1-30
	 */
	public String userGet(Connection conn, String type, String userName)throws Exception

	{
		String password = null;
		// 从数据库获取登录名和密码
		String userSql = "select * from SELENIUM_USERDATA where username = '"
		                 + userName + "' and type = '" + type + "'";
		System.out.println(userSql);
		java.sql.Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(userSql);
		// 提前完成“.next()”
		rs.next();
		password = rs.getString("password");
		return password;
	}
}
