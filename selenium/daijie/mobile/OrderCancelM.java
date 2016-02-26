package daijie.mobile;

import com.thoughtworks.selenium.Selenium;

public class OrderCancelM {
	/*
	 * 本方法为PC端订单取消功能 
	 * 输入参数： Selenium（selenium测试必备变量） orderId（待取消的订单号） 
	 * 输出参数：无
	 * @Author DaiJie
	 * @Date 2016-2-25
	 */
	public void orderCancel(Selenium selenium, String orderId) throws Exception

	{
		// 打开主导航，进入个人中心
		selenium.click("css=a.menubtn.");
		selenium.click("id=btnIndividual");
		// 等待页面加载
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// 进入订单列表页
		selenium.click("link=我的订单");
		// 等待页面加载
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// 点击目标订单的取消按钮
		selenium.click("xpath=//a[@orderno='" + orderId + "' and @class='button_2 cancel_order']");

		// 确定并返回首页
		selenium.click("id=confirmOk");
		selenium.click("css=a.homebtn_right");
	}
}
