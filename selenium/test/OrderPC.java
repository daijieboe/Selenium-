package test;

import daijie.admin.*;
import daijie.basic.*;
import daijie.pc.*;
import com.thoughtworks.selenium.*;

import java.sql.Connection;
import java.sql.ResultSet;


public class OrderPC extends SeleneseTestCase

{

	public void setUp() throws Exception

	{
			selenium = new DefaultSelenium("localhost", 4444,
					"*firefox D:\\Program Files\\Mozilla Firefox\\firefox.exe",
					"http://sitstore.boe.com");
			selenium.start();
	}
	
	/*
	 * 方法名：testUntitled 下单测试入口
	 * 参数in：void
	 * 参数out：void
	 * */
	public void testUntitled() throws Exception

	{
		//打开 pre sc首页
		selenium.open("/store/index.html");
		System.out.println("打开  sc首页 完毕！");
	//	String prefix_1 = "sit";
		ResultSet sourceData = null;
		Connection c = new GetConnection().getConnection("sit");
		// 测试数据行数暂时固定
		String testNo = "tc.or.1";

		// 从测试数据表中按照testNo获取测试数据
		GetData g = new GetData();
		sourceData = g.getData(c, testNo);
	    String PAY_MODE = sourceData.getString("PAY_MODE");
		String TEST_URL =null;
		TEST_URL = sourceData.getString("TEST_URL");
		String orderOrigin = null;
		orderOrigin = TEST_URL.substring(7,10);
		System.out.println("orderOrigin:"+orderOrigin);
        
		//准备要获取的测试结果数据
		String TEST_NO = sourceData.getString("TEST_NO");
		String ORDER_NO;
		String ORDER_STATUS=null;
		String ORDER_STATUS_NAME;
	    String TEST_RESULT = null;
		String TEST_RESULT_REASON = null;
		int TESULT_ID = 0;
		//登录
		Login l = new Login();
		l.login(selenium, sourceData);
		
		//判断下单类型，开始下单、购物车结算进入订单提交页面
		System.out.println("判断下单类型，开始下单");
		String COMBINE_TYPE = sourceData.getString("COMBINE_TYPE");
		switch (COMBINE_TYPE){
		//下单类型：1、核心商品id1
		case "1":{
			System.out.println("下单类型为单个Alta商品");
			AltaBuyNow altabuynow1 = new AltaBuyNow();
			altabuynow1.altaBuyNow1(selenium, sourceData);
			break;
		}
		//下单类型：2、周边商品id2
		case "2":{
			System.out.println("下单类型为单个周边商品");
			
		}
		//下单类型：3、多个核心（商品id1+商品id2）
		case "3":{
			System.out.println("下单类型为2个Alta商品");
			AltaBuyNow altabuynow3 = new AltaBuyNow();
			altabuynow3.altaBuyNow3(selenium, sourceData);
			break;
		}
		//下单类型：4、多个周边（商品di1+商品id2）
		case "4":{
			System.out.println("下单类型为2个周边商品");
		}
		//下单类型：5、组合核心（商品id1）+周边（商品id2）
		case "5":{
			System.out.println("下单类型为组合订单");
		}
		default:{
			System.out.println("下单类型不符合，无法测试！");
		}
		}

		//订单确认页：付款方式、配送信息、填写发票	、提交订单
		OrderConfirm orderconfirm = new OrderConfirm();
		orderconfirm.orderConfirm(selenium, sourceData);
		System.out.println("订单确认 完毕！");
		selenium.waitForPageToLoad("30000");
		
		//判断是否正确跳转到了订单成功页     要是不成功怎么处理？
		JudgeOrderSub judge = new JudgeOrderSub(selenium);
		judge.judgeordersub(selenium, sourceData);
				
		//在订单成功页抓取订单ID值，判断订单类型，并更新付款状态
		
		String orderId = null;
		String orderId1 = null;
		UpdateOrderPay updateorderpay = new UpdateOrderPay();
	    orderId1 = selenium.getText("xpath=//div[@class='inlineleft']");
		orderId = orderId1.substring(4,17);
		System.out.println("订单号为："+orderId);
		if("1".equals(COMBINE_TYPE)){
	

		     updateorderpay.updateOrderPay(orderId,PAY_MODE,orderOrigin);
		     TEST_RESULT = "2";
		     System.out.println("只下1个Alta");
		}     
		else if ("2".equals(COMBINE_TYPE)){
			
		}else if ("3".equals(COMBINE_TYPE)){		 
		     updateorderpay.updateOrderPay(orderId,PAY_MODE,orderOrigin);
		     TEST_RESULT = "2";
		     System.out.println("下2个Alta");
			
		}else if ("4".equals(COMBINE_TYPE)){
			
		}else {
			System.out.println("下单类型为组合订单");
		}
        //测试结果写入数据库        
		//TEST_NO 测试用例编码 ORDER_NO 测试生成订单号 ORDER_STATUS 测试生成订单状态
		//ORDER_STATUS_NAME 测试生成订单状态名称TEST_RESULT 测试结果。1，error  2：成功 
		//TEST_RESULT_REASON 测试不通过原因

	   GetMaxResultId getMaxResultId = new GetMaxResultId();
	   TESULT_ID = getMaxResultId.getMaxResultId(orderOrigin);
	   ORDER_NO = orderId;
	   if("0".equals(PAY_MODE)){
		   ORDER_STATUS = "20";//部分支付
	   }else {
		   ORDER_STATUS = "30";//已支付
	   }
	   ORDER_STATUS_NAME = "已支付待派单";
	   System.out.println("支付状态为："+ORDER_STATUS);
	   System.out.println(""+TESULT_ID+","+TEST_NO+","+ORDER_STATUS+"");
	   	   
	   WriteOrderResult writerestult = new WriteOrderResult();
	   writerestult.writeresult(orderOrigin,TESULT_ID, TEST_NO, ORDER_NO, ORDER_STATUS, ORDER_STATUS_NAME, TEST_RESULT, TEST_RESULT_REASON);
	   System.out.println("测试结果写入数据库完成");
	   // 模拟订单流程，打开中台

	   selenium.open("http://sitscadm.boe.com/sc-support-admin");
	   System.out.println("打开中台登录页！");
	  
	   //登录中台
	   AdminLogin adminlogin = new AdminLogin();
	   adminlogin.adminLogin(selenium, sourceData);
	   
	   //开始模拟订单流程
	  	  
	   OrderStatusInfo o = new OrderStatusInfo();
	   o.orderNo="11";
	   o.objectStatus=1;
	   o.orderOrigin="sit";
	   
	   new OrderStatusChange().orderStatusChange(selenium, o);
       
	   
	  //判断是否需要取消订单 
	   System.out.println("*******************！");
	   IsCancelOrder m = new IsCancelOrder();
	   m.isCancelOrder(selenium,sourceData,"123");
	   
	   
	}

	



}