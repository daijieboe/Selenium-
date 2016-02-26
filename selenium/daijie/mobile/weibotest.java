package daijie.mobile;

import java.sql.Connection;
import java.sql.ResultSet;
import com.thoughtworks.selenium.*;
import daijie.basic.GetData;
import daijie.basic.GetConnection;
import org.junit.Before;
import org.junit.Test;


public class weibotest {
	private Selenium selenium;
	Connection c = new GetConnection().getConnection("sit");

	// @Before下的方法先执行
	@Before
	// 设定运行的浏览器路径和测试网站url
	public void setUp() throws Exception

	{
		selenium = new DefaultSelenium("localhost", 4444,
				"*firefox D:\\Program Files\\Mozilla Firefox\\firefox.exe",
				"http://premobile.boe.com/mobileStore/index.html#!");
		selenium.start();
	}

	@Test
	public void testLogin() throws Exception {

		ResultSet sourceData = null;
		// 测试数据行数暂时固定
		String testNo = "tc.or.1";

		// 从测试数据表中按照testNo获取数据
		GetData g = new GetData();
		sourceData = g.getData(c, testNo);
		sourceData.next();

		// 打开首页
		selenium.open("/mobileStore/index.html#!");
		// 利用用户名登录
		WeiboM w = new WeiboM();
		w.weiboLogin(selenium, c, "boesc2014@163.com");
	}

}