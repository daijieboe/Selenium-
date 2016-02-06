package daijie.mobile;

import java.sql.Connection;

import com.thoughtworks.selenium.Selenium;

import daijie.basic.UserData;

public class WeiboM {
	/*
	 * 本方法为手机端微博登录
	 * 输入参数:
	 *         Selenium（测试必备）
	 *         Connection（连接数据库用）
	 *         String（登录用户名） 
	 * 输出参数：无
	 * @Author DaiJie
	 * @Date 2016-1-30
	 */
	public void weiboLogin(Selenium selenium, Connection conn,String userName) throws Exception {
		String userPwd = null;
		String type = "3";

		// 利用用户名获取密码
		userPwd = new UserData().userGet(conn, type, userName);

		System.out.println("weibo:" + userName + "; password:********");

		//点击左上角抽屉导航菜单，进入登录页面
		selenium.click("css=a.menubtn");
		selenium.click("link=登录");
		// 1.5s的页面加载等待
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//进入微博登录页面，并等待其加载
		selenium.click("id=loginSina");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// 输入用户名密码
		selenium.type("id=userId", userName);
		selenium.type("id=passwd", userPwd);

		// 点击登录按钮
		selenium.click("css=a.WB_btn_login.formbtn_01");

	}
}
