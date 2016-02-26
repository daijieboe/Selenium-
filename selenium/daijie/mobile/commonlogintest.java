package daijie.mobile;

import java.sql.Connection;
import java.sql.ResultSet;

import com.thoughtworks.selenium.*;

import daijie.basic.GetData;
import daijie.basic.Login;
import daijie.basic.Preparation;
import daijie.basic.GetConnection;

import org.junit.Before;
import org.junit.Test;



public class commonlogintest {
	private Selenium selenium;
	 //根据测试数据中测试url是PRE还是SIT设定数据库连接


	// @Before下的方法先执行
	@Before
	// 设定运行的浏览器路径和测试网站url
	public void setUp() throws Exception

	{
		// selenium = new DefaultSelenium("localhost", 4444,
		// "*firefox D:\\Program Files\\Mozilla Firefox\\firefox.exe",
		// "http://premobile.boe.com/mobileStore/index.html#!");
		// selenium.start();
		ResultSet sourceData = null;
		// 测试数据行数暂时固定
		String testNo = "tc.or.1";
		// 浏览器路径暂时固定
		String route = "*firefox D:\\Program Files\\Mozilla Firefox\\firefox.exe";

		Connection c = new GetConnection().getConnection("sit");
		// 从测试数据表中按照testNo获取数据
		GetData g = new GetData();
		sourceData = g.getData(c, testNo);
		
		// 根据已获取的数据进行初始化
		Preparation p = new Preparation();
		selenium = p.setUp(selenium, route, sourceData.getString("test_url"));
	}

	@Test
	public void testLogin() throws Exception {

		ResultSet sourceData = null;
		Connection c = new GetConnection().getConnection("sit");
		// 测试数据行数暂时固定
		String testNo = "tc.or.1";

		// 从测试数据表中按照testNo获取数据
		sourceData = new GetData().getData(c, testNo);

		// 打开首页
		selenium.open("/store/index.html");
		// 利用用户名登录
		Login l = new Login();
		l.login(selenium, sourceData);
		
	}

}
