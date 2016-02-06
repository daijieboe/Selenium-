package miaoqian;
import com.thoughtworks.selenium.*;
import java.sql.Connection;
import java.sql.ResultSet;


public class peri extends SeleneseTestCase{
	
	public void setUp() throws Exception 
	{
		selenium = new DefaultSelenium("localhost", 4444,"*firefox E:\\软件下载\\火狐浏览器\\firefox.exe","http://prestore.boe.com/");
		selenium.start();
	}
	
	public ResultSet userGet1() throws Exception

	{
		//从数据库获取登录名和密码,暂时全部取出
		Connection conn = new ConnectPRE ().connectPRE ();
		String userSql= "select * from SELENUIM_TEST_LOGIN where username='boesc2014@163.com'";
		java.sql.Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(userSql);	
		return rs;
	}
	
	public void userLoad1(String userName,String userPwd) throws Exception {
		selenium.open("/store/index.html");
		selenium.click("link=登录");
		selenium.waitForPageToLoad("30000");
		selenium.click("id=weibo");
		selenium.waitForPageToLoad("30000");
		selenium.type("id=userId", userName);
		selenium.type("id=passwd",userPwd);
		selenium.click("css=a.WB_btn_login.formbtn_01");
	}
	
	/*
	 * 方法名：Peri 周边商品且到达结算页
	 * 参数out：void
	 * */
	public void Peri(String productId,String buyNum) throws Exception

	{
		selenium.waitForPageToLoad("30000");
		//点击周边商品页面
		selenium.open("/store/order/order.html");
		selenium.click("name=mall");
		selenium.click("name=Peri_Commodity");
		selenium.waitForPageToLoad("30000");
		
		//选择产品id
		selenium.click("//img[@onclick=\"checkproductinfor('"+productId+"');\"]");
		selenium.waitForPageToLoad("30000");
		//选择产品JBL onbeat Rumble（橙色）
		//selenium.click("//img[@onclick=\"checkproductinfor('20030');\"]");
		
//		//选择省市区，按照编号
//		selenium.click("id=dropprovince_sel");
//		selenium.click("xpath=(//li[@onclick='dropprovince_li(this)'])[" +4+"]");
//		selenium.click("xpath=(//li[@onclick='dropcityclick(this)'])[" +4+"]");
//		selenium.click("xpath=(//li[@onclick='dropareaclick(this)'])[" +4+"]");
//		selenium.click("//div[@id='buy_now']/div/div[2]/div[2]/div[15]/div[4]/a/span");
		
		
		//选择购买数量
		selenium.type("xpath=(//input[@type='text'])[4]", buyNum);
		
		//获取SKU价格信息
	//	<div class="fontsize_28 div_h60">RMB 0.03</div>
		String price1=selenium.getText("xpath=//div[@class='fontsize_28 div_h60']");
		System.out.println("购买商品价格："+price1);
//		
//		//获取销售模式
				String state=selenium.getText("xpath=//div[@class='buy_now_sendto_status']");
//				System.out.println("该SKU的销售模式为："+state+".(有货---立即结算；预售---立即预定；下架---商品已下架；无货---到货通知)");
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
				
		//点击立即结算
		selenium.click("css=a[name=\"addToCart\"] > span");
		selenium.click("css=a.btn1.div_marginleft20 > span.bgcolor_edf0e9");
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
		selenium.click("css=#addrId20699> div");//确认收货人
		selenium.waitForPageToLoad("30000");
		
		//预约配送地址填写
		selenium.click("xpath=//div[@class='text_datepicker'] / input");
		selenium.click("link=" + expectDate);
		
		
		if(downLinePay)
		{
			selenium.click("id=downline");//货到付款的情况
		}else 
		{
			selenium.click("id=online");//在线支付的情况
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
		selenium.click("id=remark");
		selenium.type("id=remark", remark);//订单备注
		System.out.println("备注信息："+remark);
	}
	
	
	
	/*
	 * 方法名：writeOrderId 将成功的订单编号写入测试用例内
	 * 参数in：userName 用户名    writeOrderId 下单单号
	 * 参数out：void
	 * */
	public void writeOrderId(String userName,String writeOrderId) throws Exception

	{   		
		Connection conn = new ConnectPRE ().connectPRE ();
		String insertSql= "insert into ORDER_RESULT(USERNAME,ORDERID) VALUES ('"+userName+"','"+writeOrderId+"')";
	//	   String sql="insert into (表名)(列名1,列明2) values(?,?)";
		java.sql.Statement stmt = conn.createStatement();
		stmt.executeUpdate(insertSql);
		System.out.println("数据写入完毕！");
	}
	
	
	public void testUntitled() throws Exception

	{
		String userName = "boesc2014@163.com";
		String userPwd = null;
		ResultSet rs = null;
		int i = 1;
		
		//打开 pre sc首页
		selenium.open("/store/index.html");
		System.out.println("打开 pre sc首页 完毕！");
		
		// 暂时先随便取一个用户名密码。
		rs = userGet1();
		while (rs.next() && i != 0) {
			
			userName = rs.getString("username");
		//	System.out.println("获取到的用户名" + userName);	
			userPwd = rs.getString("pwd");
		//	System.out.println("获取到的密码 " + userPwd);				
			i = 0;//结束循环，只取第一个数据
		}
		System.out.println("获取用户名：" + userName + ",密码" + userPwd);
		userLoad1(userName,userPwd);  
		

		//买周边商品，选择数量，并结算
		Peri("20028","1");
		System.out.println("买周边产品，并结算 完毕！");
		
		//订单确认页,普票的情况
				checkOrder("30",false,true,false,"发票-京东方","测试订单备注-自动化测试");
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

