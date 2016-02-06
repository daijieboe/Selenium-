package daijie.mobile;

import java.sql.Connection;

import com.thoughtworks.selenium.Selenium;

import daijie.basic.UserData;

public class QQM {
	/*
	 * 本方法为手机端QQ登录
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

		//点开抽屉式导航，进入登录页面
		selenium.click("css=a.menubtn.");
		selenium.click("link=登录");
		// 1.5s的页面加载等待
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//进入QQ登录页面，等待其加载
		selenium.click("id=loginQQ");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//选择用户名密码输入界面
		selenium.selectFrame("ptlogin_iframe");
		selenium.click("id=switcher_plogin");

		// 输入用户名密码
		selenium.type("id=u", userName);
		selenium.type("id=p", userPwd);

		// QQ抽风，需要点两下登录按钮
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
