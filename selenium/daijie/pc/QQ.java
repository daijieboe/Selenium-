package daijie.pc;

import java.sql.Connection;

import com.thoughtworks.selenium.Selenium;

import daijie.basic.UserData;

public class QQ {
	/*
	 * 本方法为PC端QQ登录
	 * 输入参数:
	 *         Selenium（测试必备）
	 *         Connection（连接数据库用）
	 *         String（登录用户名） 
	 * 输出参数：无
	 * @Author DaiJie
	 * @Date 2016-1-30
	 */
	public void qqLogin(Selenium selenium, Connection conn, String userName)throws Exception {
		String userPwd = null;
		String type = "2";

		// 利用用户名获取密码
		userPwd = new UserData().userGet(conn, type, userName);

		System.out.println("qq:" + userName + "; password:********");

		//打开登录页
		selenium.click("link=登录");
		selenium.waitForPageToLoad("30000");
		//打开QQ登录页
		selenium.click("id=qq");
		selenium.waitForPageToLoad("30000");
		//打开用户名密码输入界面
		selenium.selectFrame("ptlogin_iframe");
		selenium.click("id=switcher_plogin");

		// 输入用户名密码
		selenium.type("id=u", userName);
		selenium.type("id=p", userPwd);

		// QQ抽风，需要多点下登录按钮
		selenium.click("id=login_button");
		//两次点击间隔1s
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		selenium.click("id=login_button");

	}
}
