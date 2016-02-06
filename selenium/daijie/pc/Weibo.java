package daijie.pc;

import java.sql.Connection;

import com.thoughtworks.selenium.Selenium;

import daijie.basic.UserData;

public class Weibo {
	/*
	 * 本方法为PC端微博登录 输入参数: Selenium（测试必备） Connection（连接数据库用） String（登录用户名） 输出参数：无
	 * 
	 * @Author DaiJie
	 * 
	 * @Date 2016-1-30
	 */
	public void weiboLogin(Selenium selenium, Connection conn, String userName)
			throws Exception {
		String userPwd = null;
		String type = "3";

		// 利用用户名获取密码
		userPwd = new UserData().userGet(conn, type, userName);

		System.out.println("weibo:" + userName + "; password:********");

		// 打开登录页
		selenium.click("link=登录");
		selenium.waitForPageToLoad("30000");
		// 进入微博登录页，并等待其加载
		selenium.click("id=weibo");
		try {
			Thread.sleep(3500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// 输入用户名密码
		selenium.type("id=userId", userName);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		selenium.type("id=userId", userName);
		selenium.type("id=passwd", userPwd);

		// 点击登录按钮
		selenium.click("css=a.WB_btn_login.formbtn_01");
		// 如果第一次点击无效，等1s后再点
		while(selenium.isElementPresent("css=a.WB_btn_login.formbtn_01")){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			selenium.click("css=a.WB_btn_login.formbtn_01");
			System.out.println("微博登录中");
		}
		// 等待登录跳转
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}
