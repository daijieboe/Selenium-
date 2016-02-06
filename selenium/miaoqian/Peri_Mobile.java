package miaoqian;
import com.thoughtworks.selenium.*;

import java.sql.Connection;
import java.sql.ResultSet;

public class Peri_Mobile extends SeleneseTestCase{
	
	public void setUp() throws Exception 
	{
		selenium = new DefaultSelenium("localhost", 4444,"*firefox E:\\软件下载\\火狐浏览器\\firefox.exe","http://premobile.boe.com/");
		selenium.start();
	}

	public ResultSet userGet1() throws Exception

	{
		//从数据库获取登录名和密码,暂时全部取出
		Connection conn = new GetConnection().getConnection();
		String userSql= "select * from SELENUIM_TEST_LOGIN where username='18945055228'";
		java.sql.Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(userSql);	
		return rs;
	}
	
	/*
	 * 方法名：orderTypeGet 订单类型读取
	 * 参数in：String orderId
	 * 参数out：ResultSet
	 * */
	public ResultSet orderTypeGet(String orderId) throws Exception
	{
		//取出订单类型
		Connection conn2 = new GetConnection().getConnection();
		String userSql2= "select * from OR_ORDER t where t.order_no='"+orderId+"'";
		java.sql.Statement stmt2 = conn2.createStatement();
		ResultSet ordertype = stmt2.executeQuery(userSql2);	
		return ordertype;
	}
	
	/*
	 * 方法名：userLoad 会员登录
	 * 参数in：userName 用户名
	 * 参数in：userPwd 密码
	 * 参数out：void
	 * */
	public void userLoad(String userName,String userPwd) throws Exception

	{
		selenium.click("css=a.menubtn ");
		selenium.setSpeed("1000");
		selenium.click("link=登录");
		selenium.type("id=loginName", userName);
		selenium.type("id=login_password", userPwd);
		selenium.click("id=login_button");
		System.out.println("会员登录 完毕！");		
	}

	/*
	 * 方法名：Peri 周边商品且到达结算页
	 * 参数in： productId 购买的周边Peri产品id
	 * 参数out：void
	 * */
	public void PeriM(String productId,String buyNum) throws Exception

	{
		//点击周边商品页面
		selenium.click("css=a.menubtn");
		selenium.click("css=div.menu_item_btn.menu_item_btn_add");
		selenium.click("link=周边产品");
		System.out.println("手机端周边产品界面打开 完毕！");
		
		//选择产品id
		selenium.click("xpath= //a[@productid='"+productId+"']");
		selenium.click("id=buyItImmediately");
		
		//选择购买数量
		selenium.type("id=productNum", buyNum);
		
		//获取SKU价格信息
		String price=selenium.getText("id=priceSum");
		System.out.println("购买商品价格："+price);
		
		//获取销售模式
		String state=selenium.getText("xpath=//div[@id='availableStockInfo']");
	//	System.out.println("该SKU的销售模式为："+state+".(有货---立即结算；预售---立即预定；下架---商品已下架；无货---到货通知)");
		if("有货".equals(state))
		{
			System.out.println("销售模式为:有货，正常销售!");
	    }
		else if("立即预定".equals(state))
		{
			System.out.println("销售模式为:预售!"); 
		}
		else if("到货通知".equals(state))
		{
			System.out.println("销售模式为:缺货!");
		}
		else 
		{
			System.out.println("销售模式为:下架!");
		}
			
		
		//点击加入购物车
		selenium.setSpeed("2000");
		selenium.click("id=operbutton");

		//点击去结算按钮
		selenium.setSpeed("2000");
		selenium.click("id=goCarGoods");
		selenium.click("id=settledown");
	}
	
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
			String orderMemoText) throws Exception

	{
//		// div class="mycheck mycheck_checked" addrid="20604"
//		selenium.click("css=span.icondetail > img");
//		selenium.click("xpath=//div[@addrid='20604']");//确认收货人
//		selenium.click("id=confirmAddr");
//		selenium.waitForPageToLoad("30000");
	
		//预约配送地址填写
		selenium.click("id=deliverDate");
		selenium.click("link=" + expectDate);
				
		if(downLinePay)
		{
			selenium.click("id=downline");//货到付款的情况
		}else 
		{
			selenium.click("css=span[name=\"paytype\"]");//在线支付的情况
		}
		
		if(askForInvoice)
		{
			selenium.click("css=span.icondetailright > a > img");//点击需要发票
			
			if(IncreaseInvoice)  //需要开增值税专用发票
			{
				companyInvoice("1","2","3","4","5","6");//增票的开票信息
			}
			else  //开普票
			{
				selenium.type("id=invoiceTitle", invoiceTitle);//普票抬头
				selenium.click("id=invoiceInfoOk");
			}
		}
		selenium.click("css=#orderMemo > div.info_item2_container > span.icondetailright > a > img");//点击订单备注按钮
		selenium.click("id=orderMemoText");
		selenium.type("id=orderMemoText", orderMemoText);//订单备注
		System.out.println("备注信息："+orderMemoText);
		selenium.click("id=orderMemoOk");
	}
	
	
	public void writeOrderId(String userName,String writeOrderId) throws Exception

	{
		Connection conn = new GetConnection().getConnection();
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
		String userName = "18945055228";
		String userPwd = null;
		ResultSet rs = null;
	//	String ot = null;
		int i = 1;
		
		//打开 pre sc首页
		selenium.open("/mobileStore/index.html#!");
		System.out.println("打开 pre 手机端首页 完毕！");
		
		// 暂时先随便取一个用户名密码。
		rs = userGet1();
		while (rs.next() && i != 0) {
			userName = rs.getString("username");
			userPwd = rs.getString("pwd");
			i = 0;//结束循环，只取第一个数据
		}
		System.out.println("获取用户名：" + userName + ",密码" + userPwd);
		
		//用户登录操作
		userLoad(userName,userPwd);  	// 判断是否需要登录selenium.isElementPresent("xpath=//a[@name='login']")
		
		//买周边商品，选择数量，并结算
		PeriM("20028","1");
		System.out.println("买周边产品，并结算 完毕！");
		
		//订单确认页,普票的情况
		checkOrder("30",false,true,false,"发票-京东方","测试订单备注-自动化测试");
		System.out.println("订单确认 完毕！");
		
		//点击提交订单按钮
		selenium.click("id=confirmOrderOk");
				System.out.println("订单提交 完毕！");
				String name2=selenium.getText("id=orderSuccPayment");
				if("去付款".equals(name2)){
					System.out.println("订单提交 成功！");
				}
				
				//在订单成功页抓取订单ID值，非组合订单的情况
				String orderId = selenium.getText("class=ordercode");
				orderId = orderId.substring(3,16);
				System.out.println("用户名："+userName+" 订单号："+orderId);
				
				//下单结果写入数据库
				writeOrderId(userName,orderId);
				Thread.sleep(10000);
	}

}
