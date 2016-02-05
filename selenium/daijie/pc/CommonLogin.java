package daijie.pc;

import java.sql.Connection;

import com.thoughtworks.selenium.Selenium;

import daijie.basic.UserData;

public class CommonLogin {
	/*
	 * 本方法为PC端普通登录
	 * 输入参数:
	 *         Selenium（测试必备）
	 *         Connection（连接数据库用）
	 *         String（登录用户名） 
	 * 输出参数：无
	 * @Author DaiJie
	 * @Date 2016-1-30
	 */
	public void commonLogin(Selenium selenium, Connection conn,String userName) throws Exception {
		String userPwd = null;
		String type = "1";

		// 利用用户名获取密码
		userPwd = new UserData().userGet(conn, type, userName);

		System.out.println("account:" + userName + "; password:********");

		//打开登录页面
		selenium.click("link=登录");
		selenium.waitForPageToLoad("30000");
		//点击用户名输入文本框使其获取焦点
		selenium.click("css=div.text_default");

		// 输入用户名密码
		selenium.type("id=username", userName);
		selenium.type("id=password", userPwd);

		//点击登录按钮，等待跳转
		selenium.click("css=span.bgcolor_f7f7f7");
		selenium.click("id=sendLogin");
		try {
			Thread.sleep(3500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
