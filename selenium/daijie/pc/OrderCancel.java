package daijie.pc;

import com.thoughtworks.selenium.Selenium;

public class OrderCancel {
	/*
	 * 本方法为PC端订单取消功能 输入参数： Selenium（selenium测试必备变量） orderId（待取消的订单号） 输出参数：无
	 * 
	 * @Author DaiJie
	 * 
	 * @Date 2016-1-30
	 */
	public void orderCancel(Selenium selenium, String orderId) throws Exception

	{
		// 进入“个人中心-订单列表页”
		selenium.click("link=订单");
		// 等待页面加载
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// 进入目标订单的详情页
		selenium.click("link=" + orderId);
		// 等待页面加载
				try {
					Thread.sleep(1500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		
		// 点击取消按钮
		selenium.click("css=div.detailinfo_middle_right > a.btn1 > span");

		// 确认取消
		selenium.click("css=#popup_ok > span.bgcolor_edf0e9");
		selenium.click("css=#popup_ok > span.bgcolor_edf0e9");

	}
}
