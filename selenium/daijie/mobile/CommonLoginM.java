package daijie.mobile;

import java.sql.Connection;

import com.thoughtworks.selenium.Selenium;

import daijie.basic.UserData;

public class CommonLoginM {
	/*
	 * 本方法为手机端普通登录
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

		//点击抽屉导航菜单，进入登录页面
		selenium.click("css=a.menubtn");
		selenium.click("link=登录");
		//等待页面加载
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// 输入用户名密码
		selenium.type("id=loginName", userName);
		selenium.type("id=login_password", userPwd);

		//点击登录按钮，等待页面加载
		selenium.click("id=login_button");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
