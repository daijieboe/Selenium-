package miaoqian;

import java.sql.ResultSet;

import com.thoughtworks.selenium.SeleneseTestCase;
import com.thoughtworks.selenium.Selenium;

public class JudgePeriSub extends SeleneseTestCase {
	
	public JudgePeriSub(Selenium s){
		selenium = s;
	}
	
	public void  judgeperisub(Selenium selenium,ResultSet sourceData) throws Exception{
		String TEST_URL = sourceData.getString("TEST_URL");
		assertEquals(TEST_URL+"store/cart/order_confirm!orderSuccess.html",selenium.getLocation());
		System.out.println("当前页面网址为："+TEST_URL+"store/cart/order_confirm!orderSuccess.html");
		System.out.println("进入订单成功页！");
		assertEquals(1,selenium.isElementPresent("xpath=//div[@class='success_right_font1']"));
		System.out.println("订单提交 成功！");
			
		}
	
	public void  judgeperisub2(Selenium selenium,ResultSet sourceData) throws Exception{
		String TEST_URL = sourceData.getString("TEST_URL");
		assertEquals(TEST_URL+"store/cart/order_confirm!orderSuccess.html",selenium.getLocation());
		System.out.println("当前页面网址为："+TEST_URL+"store/cart/order_confirm!orderSuccess.html");
		System.out.println("进入订单成功页！");
		assertEquals(1,selenium.isElementPresent("xpath=//div[@class='success_right_font1']"));
		System.out.println("订单提交 成功！");
			
		}

}
