/**
 * 功能描述：PC端、Mobile端购物车结算
 * @author 00003176
 * @version v1.0
 * @Date 2016-01-25
 */
package test;

import com.thoughtworks.selenium.*;

public class ShoppingCart extends SeleneseTestCase{
	
//	public void ShoppingCart(Selenium s)
//	{
//		selenium = s;
//	}
	/**
	 * 功能描述：PC端购物车结算
	 * @param void
	 * @return void
	 * @throw
	 * @see
	 */
	public void ShoppingCartPC(Selenium selenium)throws Exception
	{	
    //购物车结算页面
		
	selenium.click("css=#sub > span");
	System.out.println("购物车立即结算,进入订单信息提交页面!");
	}
	
	/**
	 * 功能描述：Mobile端购物车结算
	 * @param void
	 * @return void
	 * @throw void
	 * @see
	 */
	public void ShoppingCartMobile(Selenium selenium) throws Exception
	{	
    //购物车结算页面
	
		selenium.click("id=settledown");
		System.out.println("购物车立即结算,进入订单信息提交页面!");
	
	}
	
}
