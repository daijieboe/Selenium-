package daijie.pc;

import java.sql.ResultSet;

import test.GetMaxResultId;
import test.JudgeOrderSub;
import test.ShoppingCart;
import test.WriteOrderResult;

import com.thoughtworks.selenium.Selenium;

import daijie.basic.Login;

public class OrderMixedCancel {
	public void orderMixedCancel(Selenium selenium, ResultSet sourceData) throws Exception {
		
		String TEST_URL = null;
		TEST_URL = sourceData.getString("TEST_URL");
		String orderOrigin = null;
		orderOrigin = TEST_URL.substring(7, 10);
		System.out.println("orderOrigin:" + orderOrigin);

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
		new OrderConfirm().orderConfirm(selenium, sourceData);
		System.out.println("订单提交成功");

		// 判断是否正确跳转到了订单成功页 要是不成功怎么处理？
		new JudgeOrderSub(selenium).judgeordersub(selenium, sourceData);

		// 在订单成功页抓取订单ID值，判断订单类型，并更新付款状态
		String orderId1 = null;
		String orderId2 = null;
		String souceString = null;
		
		souceString = selenium.getText("xpath=//div[@class='inlineleft']");
		orderId1 = souceString.substring(4, 17);
		souceString = selenium.getText("xpath=(//div[@class='inlineleft'])[3]");
		orderId2 = souceString.substring(4, 17);
		System.out.println("组合订单号为：" + orderId1 + " " + orderId2);

		// 在前台将两个订单取消
		OrderCancel oc = new OrderCancel();
		oc.orderCancel(selenium, orderId1);
		oc.orderCancel(selenium, orderId2);

		// 准备将测试结果写入数据库
		String TEST_RESULT = "2";
		String ORDER_STATUS = "91";
		String ORDER_STATUS_NAME = "订单取消";
		String TEST_NO = sourceData.getString("TEST_NO");
		// 备注说明组合订单
		String TEST_RESULT_REASON = "组合订单测试，组合订单号为：" + orderId1 + " " + orderId2;

		GetMaxResultId gmri = new GetMaxResultId();
		WriteOrderResult wr = new WriteOrderResult();
		
		// 写入第一条订单记录
		int TESULT_ID = gmri.getMaxResultId(orderOrigin);
		wr.writeresult(orderOrigin, TESULT_ID, TEST_NO, orderId1,ORDER_STATUS,
				ORDER_STATUS_NAME, TEST_RESULT,TEST_RESULT_REASON);
		System.out.println("测试结果之一写入数据库完成");
	
		// 写入第二条订单记录
		TESULT_ID = gmri.getMaxResultId(orderOrigin);
		wr.writeresult(orderOrigin, TESULT_ID, TEST_NO, orderId2,ORDER_STATUS,
				ORDER_STATUS_NAME, TEST_RESULT,TEST_RESULT_REASON);
		System.out.println("测试结果之二写入数据库完成");
	}
}
