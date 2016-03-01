package daijie.pc;

import java.sql.ResultSet;

import test.AltaBuyNow;
import test.GetMaxResultId;
import test.JudgeOrderSub;
import test.WriteOrderResult;

import com.thoughtworks.selenium.Selenium;

import daijie.basic.Login;

public class OrderAltaCancel {
	public void orderAltaCancel(Selenium selenium, ResultSet sourceData) throws Exception {
		
		String TEST_URL = sourceData.getString("TEST_URL");
		String orderOrigin = TEST_URL.substring(7, 10);
		System.out.println("orderOrigin:" + orderOrigin);

		// 登录
		Login l = new Login();
		l.login(selenium, sourceData);

		// 判断下单类型，开始下单、购物车结算进入订单提交页面
		System.out.println("开始下单");

		new AltaBuyNow().altaBuyNow1(selenium, sourceData);

		// 订单确认页提交订单
		new OrderConfirm().orderConfirm(selenium, sourceData);
		System.out.println("订单提交成功");

		// 判断是否正确跳转到了订单成功页 要是不成功怎么处理？
		new JudgeOrderSub(selenium).judgeordersub(selenium, sourceData);

		// 在订单成功页抓取订单ID值，判断订单类型，并更新付款状态
		String orderId = null;
		String souceString = null;
		
		souceString = selenium.getText("xpath=//div[@class='inlineleft']");
		orderId = souceString.substring(4, 17);
		System.out.println("订单号为：" + orderId);

		// 在前台将订单取消
		OrderCancel oc = new OrderCancel();
		oc.orderCancel(selenium, orderId);

		// 准备将测试结果写入数据库
		String TEST_RESULT = "2";
		String ORDER_STATUS = "91";
		String ORDER_STATUS_NAME = "订单取消";
		String TEST_NO = sourceData.getString("TEST_NO");
		// 备注说明
		String TEST_RESULT_REASON = "PC端Alta下单取消测试，订单号为：" + orderId;

		GetMaxResultId gmri = new GetMaxResultId();
		WriteOrderResult wr = new WriteOrderResult();
		
		// 写入订单记录
		int TESULT_ID = gmri.getMaxResultId(orderOrigin);
		wr.writeresult(orderOrigin, TESULT_ID, TEST_NO, orderId,ORDER_STATUS,
				ORDER_STATUS_NAME, TEST_RESULT,TEST_RESULT_REASON);
		System.out.println("测试结果写入数据库完成");
	}
}
