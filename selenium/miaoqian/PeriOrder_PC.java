package miaoqian;
//import daijie.mobile.*;
import daijie.admin.*;
import daijie.basic.*;
import daijie.pc.*;

import com.thoughtworks.selenium.*;

import java.sql.Connection;
import java.sql.ResultSet;
//import java.text.SimpleDateFormat;
//import java.text.DateFormat;
//import java.util.Date;

import miaoqian.JudgePeriSub;
//import test.AltaBuyNow;
import test.GetMaxResultId;
import test.UpdateOrderPay;
import test.WriteOrderResult;
import daijie.basic.GetConnection;


public class PeriOrder_PC extends SeleneseTestCase
{
	public void setUp() throws Exception

	{
		ResultSet sourceData = null;
        // 测试数据行数暂时固定
		String testNo = "tc.or.2";
		// 浏览器路径暂时固定
		String route = "*firefox E:\\软件下载\\火狐浏览器\\firefox.exe";
		
		// 从测试数据表中按照testNo获取数据
		Connection c = new GetConnection().getConnection("sit");		
		GetData g = new GetData();
		sourceData = g.getData(c, testNo);
		
		// 根据已获取的数据进行初始化
		Preparation p = new Preparation();
		selenium = p.setUp(selenium,route, sourceData.getString("test_url"));
	}

	
	/*    ********************************************主函数************************************    */
	public void testUntitled() throws Exception

	{	
		//打开 SC的 pre 首页
		selenium.open("/store/index.html");
		System.out.println("打开  SC 首页 完毕！");
		
		ResultSet sourceData = null;
		//获取数据库里的值
		Connection c = new GetConnection().getConnection("sit");
		// 测试数据必须是自己的数据
		String testNo = "tc.or.2";
		
		// 从测试数据表中按照testNo获取测试数据
		GetData g = new GetData();
		sourceData = g.getData(c, testNo);
		System.out.println(sourceData.getString("USERNAME"));
		System.out.println(sourceData.getString("test_no"));
		
		String TEST_URL =null;
		TEST_URL = sourceData.getString("TEST_URL");
		String orderOrigin = null;
		orderOrigin = TEST_URL.substring(7,10);
		System.out.println("orderOrigin:"+orderOrigin);
		
		//准备要获取的测试结果数据
		//sentence below could be deleted
		String TEST_NO = sourceData.getString("TEST_NO");
		String ORDER_NO;
		String ORDER_STATUS;
		String ORDER_STATUS_NAME;
	    String TEST_RESULT = null;
		String TEST_RESULT_REASON = null;
		int TESULT_ID = 0;
		
		//登录
		Login l = new Login();
		l.login(selenium, sourceData);
		
		//判断下单类型，开始下单、购物车结算进入订单提交页面
				System.out.println("判断下单类型，周边开始下单");
				String COMBINE_TYPE = sourceData.getString("COMBINE_TYPE");
				switch (COMBINE_TYPE){
				//下单类型：1、核心商品id1
				case "1":{
					System.out.println("下单类型为单个Alta商品");
			
				}
				//下单类型：2、周边商品id2
				case "2":{
					System.out.println("下单类型为单个周边商品");
					PeriBuy peribuy1 = new PeriBuy(selenium);
					peribuy1.periBuy1(selenium, sourceData);
					break;					
				}
				//下单类型：3、多个核心（商品id1+商品id2）
				case "3":{
					System.out.println("下单类型为2个Alta商品");
					
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
//				selenium.click("css=#addrId20699> div");//确认收货人
//				selenium.waitForPageToLoad("30000");
				OrderConfirm orderconfirm = new OrderConfirm();
				orderconfirm.orderConfirm(selenium, sourceData);
				System.out.println("订单确认 完毕！");
				selenium.waitForPageToLoad("30000");
				
				//判断是否正确跳转到了订单成功页     要是不成功怎么处理？
				JudgePeriSub judge = new JudgePeriSub(selenium);
				judge.judgeperisub(selenium, sourceData);
				
				//在订单成功页抓取订单ID值，判断订单类型，并更新付款状态
				String orderId = null;	
				String orderIdm = null;
				UpdateOrderPay updateorderpay = new UpdateOrderPay();
				
				if("1".equals(COMBINE_TYPE)){
				}     
				else if ("2".equals(COMBINE_TYPE)){
					 orderIdm = selenium.getText("xpath=//div[@class='inlineleft']");
					System.out.println("订单号为"+orderIdm);
					orderId = orderIdm.substring(4,17);	

				     updateorderpay.updateOrderPay(orderId,"1","sit" );
				     System.out.println("新的订单号为"+orderId);
				     TEST_RESULT = "2";
				     System.out.println("更新订单的收款状态");
					
				}else if ("3".equals(COMBINE_TYPE)){
					
				}else if ("4".equals(COMBINE_TYPE)){
					
				}else {
					System.out.println("没进入IF");
				}
				 //测试结果写入数据库        
				//TEST_NO 测试用例编码 ORDER_NO 测试生成订单号 ORDER_STATUS 测试生成订单状态
				//ORDER_STATUS_NAME 测试生成订单状态名称TEST_RESULT 测试结果。1，error  2：成功 
				//TEST_RESULT_REASON 测试不通过原因

				   GetMaxResultId getMaxResultId = new GetMaxResultId();
				   TESULT_ID = getMaxResultId.getMaxResultId("sit");
				   ORDER_NO = orderId;
				   ORDER_STATUS = "30";
				   ORDER_STATUS_NAME = "已支付待派单";
				   
				   System.out.println(""+TESULT_ID+","+TEST_NO+"");

				   
				 //********************************miaoqian add blow sentence***************************************
				   //检查订单的状态
//				   String orderCheck=new Order_status().checkStatus(ORDER_NO, "30");
				//检查收款单的状态     1.待支付  2.已支付   3.取消支付
//				   String paymentNo=null;
//				   String payCheck=new Pay_Status().checkStatus(paymentNo, "30")
				   
				   WriteOrderResult writerestult = new WriteOrderResult();
				   writerestult.writeresult("sit",TESULT_ID, TEST_NO, ORDER_NO, ORDER_STATUS, ORDER_STATUS_NAME, TEST_RESULT, TEST_RESULT_REASON);
				   System.out.println("测试结果写入数据库完成");
				   
				   // 模拟订单流程，打开中台
				   selenium = new DefaultSelenium("localhost", 4444, "*firefox E:\\软件下载\\火狐浏览器\\firefox.exe", "http://sitscadm.boe.com/sc-support-admin");
				   selenium.start();
				   selenium.open("http://sitscadm.boe.com/sc-support-admin");
				   System.out.println("打开中台登录页！");
				   
				   //登录中台
				   AdminLogin adminlogin = new AdminLogin();
				   adminlogin.adminLogin(selenium, sourceData);
				   
				   //开始模拟订单流程
				  	  
				   OrderStatusInfo o = new OrderStatusInfo();
				   o.orderNo=orderId;
				   o.objectStatus=1;
				   o.orderOrigin=orderOrigin;
				   
				   new OrderStatusChange().orderStatusChange(selenium, o);

	}
}
