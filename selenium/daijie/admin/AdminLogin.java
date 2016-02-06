package daijie.admin;

import java.sql.Connection;
import java.sql.ResultSet;
import daijie.basic.GetConnection;
import com.thoughtworks.selenium.SeleneseTestCase;
import com.thoughtworks.selenium.Selenium;
import daijie.basic.UserData;

public class AdminLogin extends SeleneseTestCase {
	/*
	 * 本方法为中台登录 
	 * 输入参数：Selenium（selenium测试必备变量），ResultSet（某条测试数据记录） 
	 * 输出参数：无
	 * 实现效果：在登录页面输入用户名和密码，并点击登录
	 * @Author DaiJie
	 * @Date 2016-1-28
	 */
	public void adminLogin(Selenium selenium, ResultSet sourceData)throws Exception {
		 //准备从SIT数据库的SELENIUM_USERDATA里获取用户数据
		Connection c = new GetConnection().getConnection("sit");
		//从测试数据获取中台用户名
		String userName = sourceData.getString("ADMIN_USERNAME");
		String userPwd = null;
		//“4”代表中台登录
		String type = "4";

		// 利用用户名获取密码
		UserData u = new UserData();
		userPwd = u.userGet(c, type, userName);
		
		System.out.println("account:" + userName + "; password:********");

		//输入用户名和密码
		selenium.type("id=username", userName);
		selenium.type("id=password", userPwd);
		//点击登录按钮
		selenium.click("name=submit");
	}

}
