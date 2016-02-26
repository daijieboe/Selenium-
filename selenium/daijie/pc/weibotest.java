package daijie.pc;

import java.sql.Connection;

import com.thoughtworks.selenium.*;

import daijie.basic.GetConnection;

import org.junit.Before;
import org.junit.Test;


public class weibotest {
	private Selenium selenium;
	Connection c = new GetConnection().getConnection("sit");

	//@Before下的方法先执行
	@Before
	//设定运行的浏览器路径和测试网站url
	public void setUp() throws Exception

	{
		selenium = new DefaultSelenium("localhost", 4444,
			"*firefox D:\\Program Files\\Mozilla Firefox\\firefox.exe",
			"http://prestore.boe.com");
		selenium.start();}
		
	
	@Test
	public void testLogin() throws Exception {

		// 打开首页
		selenium.open("/store/index.html");
		//利用用户名登录
		Weibo w = new Weibo();
		w.weiboLogin(selenium, c,"boesc2014@163.com");
	}

}
