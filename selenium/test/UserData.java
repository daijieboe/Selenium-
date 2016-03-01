package test;

import java.sql.Connection;
import java.sql.ResultSet;
import daijie.basic.GetConnection;
public class UserData {
	public ResultSet userGet(String db,String name) throws Exception

	{
		//从数据库获取登录名和密码
		Connection conn = new GetConnection().getConnection("sit");
		String userSql= "select * from "+ db + " where username = '" + name + "'";
		System.out.println(userSql);
		java.sql.Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(userSql);	
		return rs;
	}
}
