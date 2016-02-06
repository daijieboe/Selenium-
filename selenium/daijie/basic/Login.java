package daijie.basic;

import java.sql.Connection;
import java.sql.ResultSet;
import daijie.basic.GetConnection;
import com.thoughtworks.selenium.Selenium;
import daijie.mobile.CommonLoginM;
import daijie.mobile.QQM;
import daijie.mobile.WeiboM;
import daijie.pc.CommonLogin;
import daijie.pc.QQ;
import daijie.pc.Weibo;

public class Login {
	/*
	 * 本方法为前台登录（包括PC&手机，普通登录、QQ登录和微博登录）功能的调用方法
	 * 输入参数：
	 *          Selenium（selenium测试必备变量）
	 *          ResultSet（某条测试数据记录）
	 * 输出参数：无
	 * @Author DaiJie
	 * @Date 2016-1-28
	 * */
	public void login(Selenium selenium, ResultSet sourceData) throws Exception {
		 //准备从SIT数据库的SELENIUM_USERDATA里获取用户数据
		Connection accessUserData = new GetConnection().getConnection("sit");
		//从测试数据中取出需要的字段值
		int loginType = sourceData.getInt("login_type");
		String userName = sourceData.getString("username");
		//从测试数据的测试url取出字符串，以便判断是手机端还是PC端
		String url = sourceData.getString("test_url").substring(10, 15);

		//PC端登录对应的方法
		if (url.equalsIgnoreCase("store")) {
			switch (loginType) {
			//普通登录
			case 1: {
				CommonLogin cl = new CommonLogin();
				cl.commonLogin(selenium, accessUserData, userName);
				break;
			}
			//QQ登录
			case 2: {
				QQ qq = new QQ();
				qq.qqLogin(selenium, accessUserData, userName);
				break;
			}
			//微博登录
			case 3: {
				Weibo wb = new Weibo();
				wb.weiboLogin(selenium, accessUserData, userName);
				break;
			}
			}
			//手机端登录对应的方法
		} else if (url.equalsIgnoreCase("mobil")) {
			switch (loginType) {
			//普通登录
			case 1: {
				CommonLoginM cl = new CommonLoginM();
				cl.commonLogin(selenium, accessUserData, userName);
				break;
			}
			//QQ登录
			case 2: {
				QQM qq = new QQM();
				qq.qqLogin(selenium, accessUserData, userName);
				break;
			}
			//微博登录
			case 3: {
				WeiboM wb = new WeiboM();
				wb.weiboLogin(selenium, accessUserData, userName);
				break;
			}
			}
		}
	}
}
