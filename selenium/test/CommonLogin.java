package test;

import java.sql.Connection;
import java.sql.ResultSet;

import com.thoughtworks.selenium.*;

import daijie.basic.GetConnection;

import org.junit.Before;
import org.junit.Test;

public class CommonLogin {
	private Selenium selenium;

	@Before
	public void setUp() throws Exception

	{
		selenium = new DefaultSelenium("localhost", 4444,
				"*firefox D:\\Program Files\\Mozilla Firefox\\firefox.exe",
				"http://PREstore.boe.com/");
		selenium.start();
	}

	public ResultSet userGet(String object) throws Exception

	{
		// 从数据库获取登录名和密码
		Connection conn = new GetConnection().getConnection("sit");
		System.out.println("no problem,for now");
		String userSql = "select * from test_commonuser where username='"
				+ object + "'";
		java.sql.Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(userSql);
		System.out.println("data is accessed");
		return rs;
	}

	public void login(String username, String password) throws Exception {

		// 利用参数执行登录数据输入

	}

	public void denglu(String accountname) throws Exception {
		String userName = accountname;
		String userPwd = null;
		ResultSet rs = null;
		int i = 1;

		// 利用用户名获取密码
		rs = userGet(userName);
		while (rs.next() && i != 0) {
			userPwd = rs.getString("password");
			i = 0;// 必不可少的rs.next()
		}
		System.out.println("account:" + userName + "; password:********");

		// 根据“消息”链接的存在来判断是否已登录
		if (selenium.isElementPresent("name=message")) {
			System.out.println("logined already, quit now");
			return;// 如果已登录，退出当前登录函数
		} else {
			System.out.println("havn't logined, do it now");
			selenium.click("link=登录");
			selenium.waitForPageToLoad("30000");
			selenium.click("css=div.text_default");

			// 输入用户名密码，登录
			System.out.println("commonLogin data is being entered:");
			selenium.type("id=username", userName);
			selenium.type("id=password", userPwd);

			selenium.click("css=span.bgcolor_f7f7f7");
			selenium.click("id=sendLogin");
			selenium.waitForPageToLoad("30000");
			System.out.println("common login succeed");
		}
	}

	@Test
	public void testLogin() throws Exception {

		// 打开首页
		selenium.open("/store/index.html");
		denglu("18701620085");

	}

}
