package test;

import java.sql.Connection;
import java.sql.ResultSet;

import com.thoughtworks.selenium.*;

import daijie.basic.GetConnection;
import daijie.basic.GetData;
import daijie.mobile.AddressChangeM;
import daijie.mobile.MixedOrderM;
import daijie.mobile.OrderCancelM;
import daijie.mobile.OrderConfirmM;
import daijie.pc.OrderCancel;

import org.junit.Before;
import org.junit.Test;

public class AltaOrderMobile {
	private Selenium selenium;

	@Before
	public void setUp() throws Exception {
		selenium = new DefaultSelenium("localhost", 4444,
				"*firefox D:\\Program Files\\Mozilla Firefox\\firefox.exe",
				"http://premobile.boe.com");
		selenium.start();
	}

	@Test
	public void testOrderconfirm_m() throws Exception {
		Connection c = new GetConnection().getConnection("sit");
		ResultSet sourceData = null;
		// 测试数据行数暂时固定
		String testNo = "tc.or.3";
		// 从测试数据表中按照testNo获取数据
		GetData g = new GetData();
		sourceData = g.getData(c, testNo);
		
		selenium.open("/mobileStore/index.html#!");
		

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
		}selenium.type("id=loginName", "18701620085");
		selenium.type("id=login_password", "aaaaaaaa");
		selenium.click("id=login_button");
		try {
			Thread.sleep(1500);// 暂停1.5秒后程序继续执行
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		new MixedOrderM().mixedOrderM(selenium, sourceData);
	
		new ShoppingCart().ShoppingCartMobile(selenium);
		try {
			Thread.sleep(5500);// 暂停1.5秒后程序继续执行
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new OrderConfirmM().orderConfirm(selenium, sourceData);
		
		//在订单成功页抓取订单ID值，利用xpath把两个都抓出来
		try {
			Thread.sleep(5500);// 暂停1.5秒后程序继续执行
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String orderId1 = selenium.getText("xpath=(//span[@class='ordercode'])");
		orderId1 = orderId1.substring(3,16);
		System.out.println(" 订单号："+orderId1);
		
		String orderId2 = selenium.getText("xpath=(//span[@class='ordercode'])[2]");
		orderId2 = orderId2.substring(3,16);
		System.out.println(" 订单号："+orderId2);
		
//		//下单结果写入数据库
//		Connection conn = new GetConnection().getConnection("sit");
//		java.sql.Statement stmt = conn.createStatement();
//		String insertSql= "insert into ORDER_RESULT(USERNAME,ORDERID) VALUES ('"+"test"+"','"+orderId1+"')";	
//		stmt.executeUpdate(insertSql);
//		insertSql= "insert into ORDER_RESULT(USERNAME,ORDERID) VALUES ('"+"test"+"','"+orderId2+"')";	
//		stmt.executeUpdate(insertSql);
//		System.out.println("数据写入完毕！");
	}

}
