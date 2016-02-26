package daijie.pc;

import daijie.basic.GetConnection;
import daijie.basic.GetData;
import daijie.basic.Login;
import daijie.basic.Preparation;

import com.thoughtworks.selenium.*;

import java.sql.Connection;
import java.sql.ResultSet;

import test.GetMaxResultId;
import test.JudgeOrderSub;
import test.ShoppingCart;
import test.UpdateOrderPay;
import test.WriteOrderResult;

public class OrderPC extends SeleneseTestCase

{

	public void setUp() throws Exception {
		ResultSet sourceData = null;
		// 测试数据行数暂时固定
		String testNo = "tc.or.7";
		// 浏览器路径暂时固定
		String route = "*firefox D:\\Program Files\\Mozilla Firefox\\firefox.exe";

		// 从测试数据表中按照testNo获取数据
		Connection c = new GetConnection().getConnection("sit");
		GetData g = new GetData();
		sourceData = g.getData(c, testNo);

		// 根据已获取的数据进行初始化
		Preparation p = new Preparation();
		selenium = p.setUp(selenium, route, sourceData.getString("test_url"));
	}

	/*
	 * 方法名：testUntitled 下单测试入口 参数in：void 参数out：void
	 */
	public void testUntitled() throws Exception

	{
		// 打开sc首页
		selenium.open("/store/index.html");
		System.out.println("打开  sc首页 完毕！");
		// String prefix_1 = "sit";
		ResultSet sourceData = null;
		Connection c = new GetConnection().getConnection("sit");
		// 测试数据行数暂时固定
		String testNo = "tc.or.7";

		// 从测试数据表中按照testNo获取测试数据
		GetData g = new GetData();
		sourceData = g.getData(c, testNo);
		String PAY_MODE = sourceData.getString("PAY_MODE");
		String TEST_URL = null;
		String combineType = sourceData.getString("COMBINE_TYPE");
		TEST_URL = sourceData.getString("TEST_URL");
		String orderOrigin = null;
		orderOrigin = TEST_URL.substring(7, 10);
		System.out.println("orderOrigin:" + orderOrigin);

		// 准备要获取的测试结果数据
		String TEST_NO = sourceData.getString("TEST_NO");
		String ORDER_NO;
		String ORDER_STATUS = null;
		String ORDER_STATUS_NAME;
		String TEST_RESULT = null;
		String TEST_RESULT_REASON = null;
		int TESULT_ID = 0;
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

		updateorderpay.updateOrderPay(orderId1 + " + " + orderId2, PAY_MODE, orderOrigin);
		TEST_RESULT = "2";
		System.out.println("下单类型为组合订单");

		// 测试结果写入数据库
		// TEST_NO 测试用例编码 ORDER_NO 测试生成订单号 ORDER_STATUS 测试生成订单状态
		// ORDER_STATUS_NAME 测试生成订单状态名称TEST_RESULT 测试结果。1，error 2：成功
		// TEST_RESULT_REASON 测试不通过原因
//
//		GetMaxResultId getMaxResultId = new GetMaxResultId();
//		TESULT_ID = getMaxResultId.getMaxResultId(orderOrigin);
//		ORDER_NO = orderId1;
//		if (PAY_MODE.equals("0")) {
//			ORDER_STATUS = "20";// 部分支付
//		} else {
//			ORDER_STATUS = "30";// 已支付
//		}
//		ORDER_STATUS_NAME = "已支付待派单";
//		System.out.println("支付状态为：" + ORDER_STATUS);
//		System.out.println("" + TESULT_ID + "," + TEST_NO + "," + ORDER_STATUS
//				+ "");
//
//		WriteOrderResult writerestult = new WriteOrderResult();
//		writerestult.writeresult(orderOrigin, TESULT_ID, TEST_NO, ORDER_NO,
//				ORDER_STATUS, ORDER_STATUS_NAME, TEST_RESULT,
//				TEST_RESULT_REASON);
//		System.out.println("测试结果写入数据库完成");

	}

}