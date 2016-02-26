package test;

import com.thoughtworks.selenium.*;

import daijie.admin.AdminLogin;
import daijie.basic.GetData;

import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;

public class CommonLogintest {
	private Selenium selenium;

	@Before
	public void setUp() throws Exception {
	selenium = new DefaultSelenium("localhost", 4444,
			"*firefox D:\\Program Files\\Mozilla Firefox\\firefox.exe",
			"http://sitcas.boe.com:8090/cas/login?service=http%3A%2F%2Fsitscadm.boe.com%2Fsc-support-admin%2Fj_spring_cas_security_check");
	selenium.start();
	}

	@Test
	public void testUntitled() throws Exception {
		selenium.open("/cas/login?service=http%3A%2F%2Fsitscadm.boe.com%2Fsc-support-admin%2Fj_spring_cas_security_check");
		
		Connection c = new GetConnection().getConnection();
		ResultSet sourceData = null;
		// 测试数据行数暂时固定
		String testNo = "tc.or.1";
		// 从测试数据表中按照testNo获取数据
		GetData g = new GetData();
		sourceData = g.getData(c, testNo);
		

		AdminLogin al = new AdminLogin();
		al.adminLogin(selenium, sourceData);
		
	}

}
