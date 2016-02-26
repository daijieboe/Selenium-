package daijie.pc;

import java.sql.ResultSet;

import test.JudgeOrderSub;
import test.ShoppingCart;
import test.UpdateOrderPay;

import com.thoughtworks.selenium.Selenium;

import daijie.basic.Login;

public class OrderAltaCancel {
	public void orderAltaCancel(Selenium selenium, ResultSet sourceData) throws Exception {
		
		String PAY_MODE = sourceData.getString("PAY_MODE");
		String TEST_URL = null;
		TEST_URL = sourceData.getString("TEST_URL");
		String orderOrigin = null;
		orderOrigin = TEST_URL.substring(7, 10);
		System.out.println("orderOrigin:" + orderOrigin);

		// 准备要获取的测试结果数据
		// String TEST_NO = sourceData.getString("TEST_NO");
		// String ORDER_NO;
		// String ORDER_STATUS = null;
		// String ORDER_STATUS_NAME;
		 String TEST_RESULT = null;
		// String TEST_RESULT_REASON = null;
		// int TESULT_ID = 0;

		// 登录
		Login l = new Login();
		l.login(selenium, sourceData);

		// 判断下单类型，开始下单、购物车结算进入订单提交页面
		System.out.println("开始下单");

		new MixedOrder().mixedOrder(selenium, sourceData);

		// 去购物车结算
		selenium.open("/store/cart/cart.html");
		new ShoppingCart().ShoppingCartPC(selenium);

		// 订单确认页提交订单
		OrderConfirm orderconfirm = new OrderConfirm();
		orderconfirm.orderConfirm(selenium, sourceData);
		System.out.println("订单确认 完毕！");
		selenium.waitForPageToLoad("30000");

		// 判断是否正确跳转到了订单成功页 要是不成功怎么处理？
		JudgeOrderSub judge = new JudgeOrderSub(selenium);
		judge.judgeordersub(selenium, sourceData);

		// 在订单成功页抓取订单ID值，判断订单类型，并更新付款状态

		String orderId1 = null;
		String orderId2 = null;
		String souceString = null;
		UpdateOrderPay updateorderpay = new UpdateOrderPay();
		souceString = selenium.getText("xpath=//div[@class='inlineleft']");
		orderId1 = souceString.substring(4, 17);
		souceString = selenium.getText("xpath=(//div[@class='inlineleft'])[3]");
		orderId2 = souceString.substring(4, 17);

		System.out.println("组合订单号为：" + orderId1 + " " + orderId2);

		updateorderpay.updateOrderPay(orderId1 + " + " + orderId2, PAY_MODE,
				orderOrigin);
		TEST_RESULT = "2";
		System.out.println("下单类型为组合订单");
	}
}
