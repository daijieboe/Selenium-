package test;

import com.thoughtworks.selenium.*;
import daijie.basic.GetConnection;
import java.sql.Connection;
import java.sql.ResultSet;

public class Open extends SeleneseTestCase

{

	public void setUp() throws Exception

	{
		selenium = new DefaultSelenium("localhost", 4444, "*firefox D:\\Program Files\\Mozilla Firefox\\firefox.exe", "http://prestore.boe.com/");
//		selenium1 = new DefaultSelenium("localhost", 4444, "*iexplore C:\\Program Files\\Internet Explorer\\iexplore.exe", "http://prestore.boe.com/");

		selenium.start();

	}
	
	/*
	 * 方法名：userGet 会员登录数据读取
	 * 参数in：void
	 * 参数out：ResultSet
	 * */
	public ResultSet userGet() throws Exception

	{
		//从数据库获取登录名和密码,暂时全部取出
		Connection conn = new GetConnection().getConnection("sit");
		String userSql= "select * from SELENUIM_TEST_LOGIN where username='13910932698'";
		java.sql.Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(userSql);	
		return rs;
	}
	
	/*
	 * 方法名：userLoad 会员登录
	 * 参数in：userName 用户名
	 * 参数in：userPwd 密码
	 * 参数out：void
	 * */
	public void userLoad(String userName,String userPwd) throws Exception

	{
		selenium.click("link=登录");
		selenium.waitForPageToLoad("30000");
		selenium.click("css=div.text_default");
		selenium.type("id=username", userName);
		selenium.type("id=password", userPwd);
		selenium.click("css=span.bgcolor_f7f7f7");
		selenium.click("id=sendLogin");
		selenium.waitForPageToLoad("30000");
		System.out.println("会员登录 完毕！");		
	}
	
	/*
	 * 方法名：altaBuyNow 立即购买alta且到达结算页
	 * 参数in： productId 购买的alta产品id
	 * 参数out：void
	 * */
	public void altaBuyNow(String productId,String buyNum) throws Exception

	{
		//点击alta页面 立即购买 按钮
		selenium.click("css=div[name=\"buyNow\"]");
		
		//选择产品id
		selenium.click("id=" + productId);
		
//		//选择省市区，按照编号
//		selenium.click("id=dropprovince_sel");
//		selenium.click("xpath=(//li[@onclick='dropprovince_li(this)'])[" +4+"]");
//		selenium.click("xpath=(//li[@onclick='dropcityclick(this)'])[" +4+"]");
//		selenium.click("xpath=(//li[@onclick='dropareaclick(this)'])[" +4+"]");
//		selenium.click("//div[@id='buy_now']/div/div[2]/div[2]/div[15]/div[4]/a/span");
		
		//选择购买数量
		selenium.type("xpath=(//input[@type='text'])[4]", buyNum);
		
		//点击立即结算
		selenium.click("css=a[name=\"addToCart\"] > span.bgcolor_edf0e9");
		selenium.waitForPageToLoad("30000");
		
		//点击去结算按钮
		selenium.click("css=#sub > span");
		selenium.waitForPageToLoad("30000");
	}
	
	/*
	 * 方法名：companyInvoice 填写增值税专用发票信息
	 * 参数in： company  单位名称
	 * 参数in： taxIdentify 纳税人识别号
	 * 参数in： regAddress 注册地址
	 * 参数in： regGhone 注册电话
	 * 参数in： openBank  开户银行
	 * 参数in： account  银行账户
	 * 参数out：void
	 * */
	public void companyInvoice(String company,
			String taxIdentify,
			String regAddress,
			String regGhone,
			String openBank,
			String account) throws Exception

	{
		selenium.click("id=companyInvoice");
		selenium.type("id=company", company);  // 单位名称
		selenium.type("id=taxIdentify", taxIdentify);//纳税人识别号
		selenium.type("id=regAddress", regAddress);//注册地址
		selenium.type("id=regGhone", regGhone);//注册电话
		selenium.type("id=openBank",openBank);//开户银行
		selenium.type("id=account", account);//银行账户
		selenium.click("css=div.divsavebillinfo.btnsavebillinfo");//点击保存按钮
	}

	/*
	 * 方法名：checkOrder 订单确认页-普通发票，不包含提交订单
	 * 参数in： expectDate  预约配送时间
	 * 参数in： downLinePay 是否货到付款   true:货到付款  false：在线支付
	 * 参数in： askForInvoice 是否需要发票  true：需要发票 false：不需要发票
	 * 参数in：  IncreaseInvoice 是否开增票  true：是，开增票 false：否，普票
	 * 参数in： invoiceTitle  普票发票抬头
	 * 参数in： remark  订单备注信息
	 * 参数out：void
	 * */
	public void checkOrder(String expectDate,
			boolean downLinePay,
			boolean askForInvoice,
			boolean IncreaseInvoice,
			String invoiceTitle,
			String remark) throws Exception

	{
		selenium.click("css=#addrId20528 > div");//确认收货人
		selenium.waitForPageToLoad("30000");
		
		//预约配送地址填写
		selenium.click("xpath=//div[@class='text_datepicker'] / input");
		selenium.click("link=" + expectDate);
		
		if(downLinePay)
		{
			selenium.click("id=downline");//货到付款的情况
		}
		
		if(askForInvoice)
		{
			selenium.click("id=askForInvoice");//点击需要发票
			
			if(IncreaseInvoice)  //需要开增值税专用发票
			{
				companyInvoice("1","2","3","4","5","6");//增票的开票信息
			}
			else  //开普票
			{
				selenium.type("id=invoiceTitle", invoiceTitle);//普票抬头
				selenium.click("css=div.divsavebillinfo.btnsavebillinfo");
			}
		}
		
		selenium.click("css=div..div_h42 > div.openclosecircle");//点击订单备注按钮
		selenium.type("id=remark", remark);//订单备注
	}
	
	/*
	 * 方法名：writeOrderId 将成功的订单编号写入测试用例内
	 * 参数in：userName 用户名    writeOrderId 下单单号
	 * 参数out：void
	 * */
	public void writeOrderId(String userName,String writeOrderId) throws Exception

	{
		Connection conn = new GetConnection().getConnection("sit");
		String insertSql= "insert into ORDER_RESULT(USERNAME,ORDERID) VALUES ('"+userName+"','"+writeOrderId+"')";
	
		java.sql.Statement stmt = conn.createStatement();
		stmt.executeUpdate(insertSql);
		System.out.println("数据写入完毕！");
	}
	
	/*
	 * 方法名：testUntitled 下单测试入口
	 * 参数in：void
	 * 参数out：void
	 * */
	public void testUntitled() throws Exception

	{
		String userName = "13910932698";
		String userPwd = null;
		ResultSet rs = null;
		int i = 1;
		
		//打开 pre sc首页
		selenium.open("/store/index.html");
		System.out.println("打开 pre sc首页 完毕！");
		
		// 暂时先随便取一个用户名密码。
		rs = userGet();
		while (rs.next() && i != 0) {
			userName = rs.getString("username");
			userPwd = rs.getString("pwd");
			i = 0;//结束循环，只取第一个数据
		}
		System.out.println("获取用户名：" + userName + ",密码" + userPwd);
		
		//用户登录操作
		userLoad(userName,userPwd);  	// 判断是否需要登录selenium.isElementPresent("xpath=//a[@name='login']")
		
		//alta详情页 
		selenium.click("name=Alta");
		selenium.waitForPageToLoad("30000");
		System.out.println("alta详情页打开 完毕！");
		
		//立即购买alta，并结算
		altaBuyNow("20016","1");
		System.out.println("立即购买alta，并结算 完毕！");
		
		//订单确认页,货到付款、普票的情况
		checkOrder("30",true,true,false,"发票-京东方","测试订单备注-自动化测试");
		System.out.println("订单确认 完毕！");
		
		//点击提交订单按钮
		selenium.click("id=sub");
		System.out.println("订单提交 完毕！");
		selenium.waitForPageToLoad("30000");
		
		//判断是否正确跳转到了订单成功页
		assertEquals("http://prestore.boe.com/store/cart/order_confirm!orderSuccess.html",
				selenium.getLocation());
		System.out.println("进入订单成功页！");
		assertEquals(1,selenium.isElementPresent("xpath=//div[@class='success_right_font1']"));
		System.out.println("订单提交 成功！");
		
		//在订单成功页抓取订单ID值，非组合订单的情况
		String orderId = selenium.getText("xpath=//div[@class='inlineleft']");
		orderId = orderId.substring(4,17);
		System.out.println("用户名："+userName+" 订单号："+orderId);
		
		//下单结果写入数据库
		writeOrderId(userName,orderId);
		Thread.sleep(10000);	
	}
}