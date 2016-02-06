package daijie.admin;

import com.thoughtworks.selenium.*;

public class OrderStatusChange {
	/*
	 * 本方法为中台Alta订单状态更新
	 * 输入参数：
	 *         Selenium（selenium测试必备变量）
	 *         OrderStatusInfo（待更新的订单信息）
	 * 输出参数：无 
	 * 实现效果：对SIT/PRE的在线支付/货到付款订单更新至签收前的状态，以及签收完成的状态
	 * @Author DaiJie
	 * @Date 2016-1-28
	 */
	public void orderStatusChange(Selenium selenium, OrderStatusInfo o) {
        //等待页面加载
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// 打开订单管理一级菜单
		selenium.click("css=#102 > a > p");
		// 等待页面加载
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// 打开订单状态回传模拟界面
		selenium.click("link=订单状态回传模拟");
		// 等待页面加载
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		selenium.selectFrame("mainFrame");

		// 对SIT的订单进行处理
		if (o.orderOrigin.equalsIgnoreCase("sit")) {
			selenium.click("id=sit");

			// 根据目标订单状态选择不同的方法
			switch (o.objectStatus) {
			// 在线支付订单变为待签收
			case 1: {
				selenium.type("name=statusback.orderno", o.orderNo);
				selenium.select("id=status", "label=接单(WMS_ACCEPT)");
				selenium.click("id=searchButton");
				selenium.waitForPageToLoad("30000");
				selenium.select("id=status", "label=派送(TMS_DELIVERING)");
				selenium.click("id=searchButton");
				selenium.waitForPageToLoad("30000");
				break;
			}
			// 货到付款订单变为待签收并扣款
			case 2: {
				selenium.type("name=statusback.orderno", o.orderNo);
				selenium.select("id=status", "label=接单(WMS_ACCEPT)");
				selenium.click("id=searchButton");
				selenium.waitForPageToLoad("30000");
				selenium.select("id=status", "label=派送(TMS_DELIVERING)");
				selenium.click("id=searchButton");
				selenium.waitForPageToLoad("30000");
				selenium.select("id=status", "label=扣款成功(COD_SUCCESS)");
				selenium.click("id=searchButton");
				selenium.waitForPageToLoad("30000");
				break;
			}
			// 订单签收成功
			case 3: {
				selenium.type("name=statusback.orderno", o.orderNo);
				selenium.select("id=status", "label=签收成功(TMS_SIGN)");
				selenium.click("id=searchButton");
				selenium.waitForPageToLoad("30000");
				break;
			}
			}
			// 对PRE的订单进行处理
		} else if (o.orderOrigin.equalsIgnoreCase("pre")) {
			selenium.click("id=pre");

			// 根据目标订单状态选择不同的方法
			switch (o.objectStatus) {
			// 在线支付订单变为待签收
			case 1: {
				selenium.type("name=statusback.orderno", o.orderNo);
				selenium.select("id=status", "label=接单(WMS_ACCEPT)");
				selenium.click("id=searchButton");
				selenium.waitForPageToLoad("30000");
				selenium.select("id=status", "label=派送(TMS_DELIVERING)");
				selenium.click("id=searchButton");
				selenium.waitForPageToLoad("30000");
				break;
			}
			// 货到付款订单变为待签收并扣款
			case 2: {
				selenium.type("name=statusback.orderno", o.orderNo);
				selenium.select("id=status", "label=接单(WMS_ACCEPT)");
				selenium.click("id=searchButton");
				selenium.waitForPageToLoad("30000");
				selenium.select("id=status", "label=派送(TMS_DELIVERING)");
				selenium.click("id=searchButton");
				selenium.waitForPageToLoad("30000");
				selenium.select("id=status", "label=扣款成功(COD_SUCCESS)");
				selenium.click("id=searchButton");
				selenium.waitForPageToLoad("30000");
				break;
			}
			// 订单签收成功
			case 3: {
				selenium.type("name=statusback.orderno", o.orderNo);
				selenium.select("id=status", "label=签收成功(TMS_SIGN)");
				selenium.click("id=searchButton");
				selenium.waitForPageToLoad("30000");
				break;
			}
			}
		}
	}

}
