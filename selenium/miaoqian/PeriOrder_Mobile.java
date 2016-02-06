package miaoqian;
import daijie.admin.*;
import daijie.basic.*;
import daijie.mobile.OrderConfirm_m;
//import daijie.pc.*;
//
//import java.util.Date;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;

import com.thoughtworks.selenium.*;

//import org.openqa.selenium.WebElement;
import java.sql.Connection;
import java.sql.ResultSet;

import test.GetConnection;
import test.GetMaxResultId;
import test.UpdateOrderPay;
import test.WriteOrderResult;

public class PeriOrder_Mobile extends SeleneseTestCase {

	public void setUp() throws Exception

	{
		ResultSet sourceData = null;
        // 测试数据行数暂时固定
		String testNo = "tc.or.3";
		// 浏览器路径暂时固定
		String route = "*firefox E:\\软件下载\\火狐浏览器\\firefox.exe";
		
		// 从测试数据表中按照testNo获取数据
		Connection c = new GetConnection().getConnection();		
		GetData g = new GetData();
		sourceData = g.getData(c, testNo);
		
		// 根据已获取的数据进行初始化
		Preparation p = new Preparation();
		selenium = p.setUp(selenium,route, sourceData.getString("test_url"));
	}
	
	/*
	 * 方法名：testUntitled 下单测试入口
	 * 参数in：void
	 * 参数out：void
	 * */
	
	public void testUntitled() throws Exception{
		selenium.open("/mobileStore/index.html#!");
		System.out.println("打开  手机端首页 ！");
		
		ResultSet sourceData = null;
		Connection c = new GetConnection().getConnection();
		// 测试数据行数暂时固定
		String testNo = "tc.or.3";
		
		// 从测试数据表中按照testNo获取测试数据
				GetData g = new GetData();
				sourceData = g.getData(c, testNo);				

				String TEST_URL =null;
				TEST_URL = sourceData.getString("TEST_URL");
				String orderOrigin = null;
				orderOrigin = TEST_URL.substring(7,10);
				System.out.println("orderOrigin:"+orderOrigin);
				
				//准备要获取的测试结果数据
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
					System.out.println("下单类型为单个周边商品"+COMBINE_TYPE);
					PeriBuy periBuy2 = new PeriBuy(selenium);
					periBuy2.periBuy2(selenium, sourceData);
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
				System.out.println("开始编辑订单提交页面~");
				OrderConfirm_m orderconfirm1 = new OrderConfirm_m();
				orderconfirm1.orderConfirm(selenium, sourceData);
				System.out.println("订单确认 完毕！");
				
				//判断是否下单成功
				
				//在订单成功页抓取订单ID值，判断订单类型，并更新付款状态
//				String orderId = null;	
//				UpdateOrderPay updateorderpay = new UpdateOrderPay();
//				
//				String orderIdm2 = selenium.getText("class=ordercode");
//				orderId = orderIdm2.substring(3,16);
//				System.out.println(" 订单号："+orderId);				
//			     updateorderpay.updateOrderPay(orderId);
//			     System.out.println("新的订单号为"+orderId);
//			     TEST_RESULT = "2";
//			     System.out.println("if yu ju");
				//在订单成功页抓取订单ID值，判断订单类型，并更新付款状态
				
				UpdateOrderPay updateorderpay = new UpdateOrderPay();
				
				String orderId = selenium.getText("class=ordercode");
				orderId = orderId.substring(3,16);
				System.out.println("订单号为："+orderId);

				
				if("1".equals(COMBINE_TYPE)){
				}     
				else if ("2".equals(COMBINE_TYPE)){				
//					String orderIdm2 = selenium.getText("class=ordercode");
//					orderId = orderIdm2.substring(3,16);
//					System.out.println(" 订单号："+orderId);
					
				     updateorderpay.updateOrderPay(orderId,"1");
				     System.out.println("新的订单号为"+orderId);
				     TEST_RESULT = "2";
				     System.out.println("if yu ju");
					
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
				   TESULT_ID = getMaxResultId.getMaxResultId();
				   ORDER_NO = orderId;
				   ORDER_STATUS = "30";
				   ORDER_STATUS_NAME = "已支付待派单";
				   
				   System.out.println(""+TESULT_ID+","+TEST_NO+"");
				   
//				   java.sql.Date d = new java.sql.Date(System.currentTimeMillis());
//				   
//				   SimpleDateFormat sdf = new SimpleDateFormat ("yyyy/MM/dd HH:mm:ss");
//				   Date dd = new Date();
//				   String now = sdf.format(dd);
				   
//				   java.sql.Timestamp CREAT_DATE =new java.sql.Timestamp(dd.getTime());
//				   String dd =sdf.format(d);
//				   System.out.println(dd);
//				   Date ddd;CREAT_DATE
//				   ddd = sdf.parse(dd);
//				   System.out.println(now);
				   
				   WriteOrderResult writerestult = new WriteOrderResult();
				   writerestult.writeresult(TESULT_ID, TEST_NO, ORDER_NO, ORDER_STATUS, ORDER_STATUS_NAME, TEST_RESULT, TEST_RESULT_REASON);
				   System.out.println("测试结果写入数据库完成");
				   
					  // 模拟订单流程，打开中台
				   selenium = new DefaultSelenium("localhost", 4444, "*firefox E:\\软件下载\\火狐浏览器\\firefox.exe", "http://sitscadm.boe.com/sc-support-admin");
				   selenium.start();
				   selenium.open("http://sitscadm.boe.com/sc-support-admin");
				   System.out.println("打开中台登录页！订单号为："+orderId);
				   
				 //登录中台
				   AdminLogin adminlogin = new AdminLogin();
				   adminlogin.adminLogin(selenium, sourceData);
				   //开始模拟订单流程
//				   OrderStatusInfo o = null;
//				   OrderStatusChange orderstatuschange = new OrderStatusChange();
//				   orderstatuschange.orderStatusChange(selenium, o);
				   
				   OrderStatusInfo o = new OrderStatusInfo();
				   o.orderNo=orderId;
				   o.objectStatus=1;
				   o.orderOrigin=orderOrigin;
				   
				   new OrderStatusChange().orderStatusChange(selenium, o);
	}
}
