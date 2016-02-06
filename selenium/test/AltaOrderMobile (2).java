package test;

import java.sql.Connection;
import java.sql.ResultSet;
import com.thoughtworks.selenium.*;
import daijie.basic.GetData;
import daijie.mobile.AddressChange_m;
import daijie.mobile.OrderConfirm_m;
import org.junit.Before;
import org.junit.Test;

public class AltaOrderMobile {
	private Selenium selenium;

	@Before
	public void setUp() throws Exception {
		selenium = new DefaultSelenium("localhost", 4444,
				"*firefox D:\\Program Files\\Mozilla Firefox\\firefox.exe",
				"http://premobile.boe.com/");
		selenium.start();
	}

	@Test
	public void testUntitled() throws Exception {
		ResultSet sourceData = null;
		Connection c = new GetConnection().getConnection();
		String testNo = "tc.or.1";

		// 从测试数据表中按照testNo获取数据
		GetData g = new GetData();
		sourceData = g.getData(c, testNo);

		selenium.open("/mobileStore/index.html#!mainPage/%257B%257D");
		selenium.click("css=a.menubtn");
		try {
			Thread.sleep(1500);// 暂停1.5秒后程序继续执行
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		selenium.click("link=登录");
		try {
			Thread.sleep(1500);// 暂停1.5秒后程序继续执行
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		selenium.type("id=loginName", "18701620085");
		selenium.type("id=login_password", "aaaaaaaa");
		selenium.click("id=login_button");
		try {
			Thread.sleep(1500);// 暂停1.5秒后程序继续执行
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		selenium.click("css=a.menubtn.");
		try {
			Thread.sleep(1500);// 暂停1.5秒后程序继续执行
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		selenium.click("css=li[name=\"LM_mall\"] > div.menu_item_text");
		try {
			Thread.sleep(1500);// 暂停1.5秒后程序继续执行
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		selenium.click("link=Alta");
		try {
			Thread.sleep(1500);// 暂停1.5秒后程序继续执行
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		selenium.click("id=linkBuy");
		try {
			Thread.sleep(1500);// 暂停1.5秒后程序继续执行
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		selenium.click("id=operbutton");
		try {
			Thread.sleep(1500);// 暂停1.5秒后程序继续执行
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		selenium.click("id=settledown");
		try {
			Thread.sleep(1500);// 暂停1.5秒后程序继续执行
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// ready to change address
		AddressChange_m a = new AddressChange_m();
		a.addressChange(selenium, sourceData);

		// ready to fulfiil the order
		OrderConfirm_m o = new OrderConfirm_m();
		o.orderConfirm(selenium, sourceData);


	}
}
