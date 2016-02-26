package test;
 
import java.sql.ResultSet;
import com.thoughtworks.selenium.SeleneseTestCase;
import com.thoughtworks.selenium.Selenium;


public class AltaBuyNow extends SeleneseTestCase{

	public AltaBuyNow(Selenium s)
	{
		selenium = s;
	}
	
	/*
	 * 方法名：altaBuyNow1 （下单类型：1。只是购买单品Alta）PC端--立即购买alta且到达结算页
	 * 参数in： PRD_ID_1 商品1 ID；
	 *         PRD_NUM_1  商品1 ID购买数量；      
	 *         IS_PRESELL_1  商品1 是否预售：1 是，0 否
	 *         PRESELL_DATE_1  商品1 预售日期
	 *         BUY_ADDR_PROVINCE 购物车地址：省
	 *         BUY_ADDR_CITY 购物车地址：市
	 *         BUY_ADDR_AREA 购物车地址：区
	 * 参数out：void
	 * */
	public void altaBuyNow1(Selenium selenium, ResultSet sourceData) throws Exception{
		
		//Connection accessUserData = new GetConnection().getConnection();
		int PRD_ID_1 = sourceData.getInt("PRD_ID_1");
		int PRD_NUM_1 = sourceData.getInt("PRD_NUM_1");
		String IS_PRESELL_1 = sourceData.getString("IS_PRESELL_1");
	    String TEST_NO = sourceData.getString("TEST_NO");
		String BUY_ADDR_PROVINCE = sourceData.getString("BUY_ADDR_PROVINCE");
		String BUY_ADDR_CITY = sourceData.getString("BUY_ADDR_CITY");
		String BUY_ADDR_AREA = sourceData.getString("BUY_ADDR_AREA");
		int	TESULT_ID = 0;
		
		//获取本次测试结果的ID
		GetMaxResultId getMaxResultId = new GetMaxResultId();
		TESULT_ID = getMaxResultId.getMaxResultId();
		
		//点击商城ALTA页
		selenium.setSpeed("2000");
		selenium.click("name=Alta");
		selenium.waitForPageToLoad("30000");
		System.out.println("alta详情页打开 完毕！");
		
		//点击ALTA页面 立即购买 按钮
		selenium.setSpeed("2000");
		selenium.click("css=div[name=\"buyNow\"]");
		
		//选择省市区

		selenium.click("css=div.buy_now_sendto_select");
		selenium.click("xpath=(//ul[@id='dropprovince']/li[@val='"+BUY_ADDR_PROVINCE+"'])");
		selenium.click("xpath=(//ul[@id='dropcity']/li[@val='"+BUY_ADDR_CITY+"'])");
		selenium.click("xpath=(//ul[@id='droparea']/li[@val='"+BUY_ADDR_AREA+"'])");
		selenium.click("//div[@id='buy_now']/div/div[2]/div[2]/div[15]/div[4]/a/span");
		
		System.out.println("下单省市区为："+BUY_ADDR_PROVINCE+BUY_ADDR_CITY+BUY_ADDR_AREA);
		//选择产品id
	    selenium.click("id=" + PRD_ID_1);
	    
	  //选择购买数量。数量要由int型的要转换成string类型的
	    selenium.type("xpath=(//input[@type='text'])[4]", Integer.toString(PRD_NUM_1));
//	    
//	  //判断销售模式是否正确，不正确则返回测试结果
//	    int resultnumber = 1;
//	  	String name1=selenium.getText("xpath=//a[@class='btn1 btn_payfor']");
//	  	if("立即结算".equals(name1)){
//	  		if ("0".equals(IS_PRESELL_1)){
//	  			System.out.println("销售模式是正常销售，测试的是非预售，匹配！");
//	  			System.out.println("立即购买alta，并去购物车结算！");
//	  			resultnumber = 1;
//	  	       }else{ 
//	  		    System.out.println("销售模式是正常销售，测试的是预售，不匹配！直接返回测试结果~");
//	  		    resultnumber = 2;
//	  		  }
//	  	}else if("立即预定".equals(name1)){
//	  		if ("0".equals(IS_PRESELL_1)){
//	  			System.out.println("销售模式是预售，测试的是非预售，不匹配！直接返回测试结果~");
//	  			resultnumber = 2;
//	  		}else{ 
//	  			System.out.println("销售模式是预售，测试的是预售，匹配！");
//	  			System.out.println("立即购买alta，并去购物车结算！");
//	  			resultnumber = 1;
//	  		   }
//	  		}else if("到货通知".equals(name1)){
//	  			System.out.println("目前商品缺货!");
//	  			resultnumber = 3;
//	  		}else {
//	  			System.out.println("目前商品下架!");
//	  			resultnumber = 3;
//	  		}
//	    System.out.println("resultnumber="+resultnumber);  	
//	    
//	  	if(resultnumber ==3){
//	  		System.out.println("商品1已下架或缺货，退出测试");
//	  		String TEST_RESULT_REASON = "商品1已下架或缺货，退出测试";
//	  		WriteOrderResult p = new WriteOrderResult();
//	  		p.writeresult2(TESULT_ID, TEST_NO,TEST_RESULT_REASON);
//	  	}else if (resultnumber ==2){
//	  		System.out.println("商品1销售模式与测试数据不匹配，退出测试");
//	  		String TEST_RESULT_REASON = "商品1销售模式与测试数据不匹配，退出测试";
//	  		WriteOrderResult q = new WriteOrderResult();
//	  		q.writeresult2(TESULT_ID, TEST_NO,TEST_RESULT_REASON);
//	  	}
//	  	
	  	//点击立即结算
		selenium.click("css=a[name=\"addToCart\"] > span.bgcolor_edf0e9");
		selenium.waitForPageToLoad("30000");
			
		//点击购物车结算
		selenium.setSpeed("3000");
	    ShoppingCart shopCart1 = new ShoppingCart();
		shopCart1.ShoppingCartPC(selenium);
			
	}
	
	/*
	 * 方法名：altaBuyNow2 （下单类型：1。只是购买单品Alta）手机端立即购买alta且到达结算页
	 * 参数in： PRD_ID_1 商品1 ID；
	 *         PRD_NUM_1  商品1 ID购买数量；      
	 *         IS_PRESELL_1  商品1 是否预售：1 是，0 否
	 *         PRESELL_DATE_1  商品1 预售日期
	 *         BUY_ADDR_PROVINCE 购物车地址：省
	 *         BUY_ADDR_CITY 购物车地址：市
	 *         BUY_ADDR_AREA 购物车地址：区
	 * 参数out：void
	 * */
	public void altaBuyNow2(Selenium selenium, ResultSet sourceData) throws Exception{
		
		
		int PRD_ID_1 = sourceData.getInt("PRD_ID_1");
		int PRD_NUM_1 = sourceData.getInt("PRD_NUM_1");
		String IS_PRESELL_1 = sourceData.getString("IS_PRESELL_1");
		String TEST_NO = sourceData.getString("TEST_NO");
		String BUY_ADDR_PROVINCE = sourceData.getString("BUY_ADDR_PROVINCE");
		String BUY_ADDR_CITY = sourceData.getString("BUY_ADDR_CITY");
		String BUY_ADDR_AREA = sourceData.getString("BUY_ADDR_AREA");
        int	TESULT_ID = 0;
		
		//获取本次测试结果的ID
		GetMaxResultId getMaxResultId = new GetMaxResultId();
		TESULT_ID = getMaxResultId.getMaxResultId();

		
		//点击ALTA页面 立即购买 按钮
		selenium.setSpeed("2000");
		selenium.click("link=商城");
		selenium.click("id=malltv");
		selenium.click("id=btn_buyImmediately");
		System.out.println("手机端Alta购物界面打开 完毕！");
		
		//选择省市区
		selenium.setSpeed("2000");
        selenium.select("id=dropprovince", "label="+BUY_ADDR_PROVINCE+"");
        selenium.select("id=dropcity", "label="+BUY_ADDR_CITY+"");
        selenium.select("id=droparea", "label="+BUY_ADDR_AREA+"");
        
        
		//选择商品ID：标准 Alta 挂装---20012   标准 Alta 座装---20009 限量 Alta 挂装---20018  限量 Alta 座装---20016
        if(PRD_ID_1==20009){
       	 selenium.click("link=标准 Alta 座装");
        }else if(PRD_ID_1==20012){
       	 selenium.click("link=标准 Alta 挂装");
        }else if(PRD_ID_1==20016){
       	 selenium.click("link=限量 Alta 座装");
        }else
            selenium.click("link=限量 Alta 挂装");
		
		//选择购买数量
	 	 selenium.type("id=productNum", Integer.toString(PRD_NUM_1));
		
		//判断销售模式是否正确
	 	int resultnumber = 1;
		String name1=selenium.getText("xpath=//a[@id='operbutton']");
		if("立即结算".equals(name1)){
	  		if ("0".equals(IS_PRESELL_1)){
	  			System.out.println("销售模式是正常销售，测试的是非预售，匹配！");
	  			System.out.println("立即购买alta，并去购物车结算！");
	  			resultnumber = 1;
	  	       }else{ 
	  		    System.out.println("销售模式是正常销售，测试的是预售，不匹配！直接返回测试结果~");
	  		    resultnumber = 2;
	  		  }
	  	}else if("立即预定".equals(name1)){
	  		if ("0".equals(IS_PRESELL_1)){
	  			System.out.println("销售模式是预售，测试的是非预售，不匹配！直接返回测试结果~");
	  			resultnumber = 2;
	  		}else{ 
	  			System.out.println("销售模式是预售，测试的是预售，匹配！");
	  			System.out.println("立即购买alta，并去购物车结算！");
	  			resultnumber = 1;
	  		   }
	  		}else if("到货通知".equals(name1)){
	  			System.out.println("目前商品缺货!");
	  			resultnumber = 3;
	  		}else {
	  			System.out.println("目前商品下架!");
	  			resultnumber = 3;
	  		}
	  	
	  	if(resultnumber ==3){
	  		System.out.println("商品1已下架或缺货，退出测试");
	  		String TEST_RESULT_REASON = "商品1已下架或缺货，退出测试";
	  		WriteOrderResult p = new WriteOrderResult();
	  		p.writeresult2(TESULT_ID, TEST_NO,TEST_RESULT_REASON);
	  	}else if (resultnumber ==2){
	  		System.out.println("商品1销售模式与测试数据不匹配，退出测试");
	  		String TEST_RESULT_REASON = "商品1销售模式与测试数据不匹配，退出测试";
	  		WriteOrderResult q = new WriteOrderResult();
	  		q.writeresult2(TESULT_ID, TEST_NO,TEST_RESULT_REASON);
	  	}
		
	  		
	  	//点击立即结算
		selenium.setSpeed("2000");
		selenium.click("id=operbutton");
			
		//点击购物车结算
		selenium.setSpeed("3000");
	    ShoppingCart shopCart2 = new ShoppingCart();
		shopCart2.ShoppingCartMobile(selenium);
			
	}	
	
	/*
	 * 方法名：altaBuyNow3 （下单类型：3。购买2种Alta）PC端--立即购买alta且到达结算页
	 * 参数in： PRD_ID_1 商品1 ID；
	 *         PRD_NUM_1  商品1 ID购买数量；      
	 *         IS_PRESELL_1  商品1 是否预售：1 是，0 否
	 *         PRESELL_DATE_1  商品1 预售日期
	 *         PRD_ID_2 商品2 ID；
	 *         PRD_NUM_2  商品2 ID购买数量；      
	 *         IS_PRESELL_2  商品2 是否预售：1 是，0 否
	 *         PRESELL_DATE_2  商品2 预售日期
	 *         BUY_ADDR_PROVINCE 购物车地址：省
	 *         BUY_ADDR_CITY 购物车地址：市
	 *         BUY_ADDR_AREA 购物车地址：区
	 * 参数out：void
	 * */
	public void altaBuyNow3(Selenium selenium, ResultSet sourceData) throws Exception{
		
		//Connection accessUserData = new GetConnection().getConnection();
		int PRD_ID_1 = sourceData.getInt("PRD_ID_1");
		int PRD_NUM_1 = sourceData.getInt("PRD_NUM_1");
		String IS_PRESELL_1 = sourceData.getString("IS_PRESELL_1");
		int PRD_ID_2 = sourceData.getInt("PRD_ID_2");
		int PRD_NUM_2 = sourceData.getInt("PRD_NUM_2");
		String IS_PRESELL_2 = sourceData.getString("IS_PRESELL_2");		
		String TEST_NO = sourceData.getString("TEST_NO");
		
	//	String PRESELL_DATE_1 = sourceData.getString("PRESELL_DATE_1");
		String BUY_ADDR_PROVINCE = sourceData.getString("BUY_ADDR_PROVINCE");
		String BUY_ADDR_CITY = sourceData.getString("BUY_ADDR_CITY");
		String BUY_ADDR_AREA = sourceData.getString("BUY_ADDR_AREA");
		int	TESULT_ID = 0;
		//获取本次测试结果的ID
		GetMaxResultId getMaxResultId = new GetMaxResultId();
		TESULT_ID = getMaxResultId.getMaxResultId();
		
		//点击商城ALTA页
		selenium.setSpeed("2000");
		selenium.click("name=Alta");
		selenium.waitForPageToLoad("30000");
		System.out.println("alta详情页打开 完毕！");
		
		//点击ALTA页面 立即购买 按钮
		selenium.setSpeed("2000");
		selenium.click("css=div[name=\"buyNow\"]");
		
		//选择省市区

		selenium.click("css=div.buy_now_sendto_select");
		selenium.click("xpath=(//ul[@id='dropprovince']/li[@val='"+BUY_ADDR_PROVINCE+"'])");
		selenium.click("xpath=(//ul[@id='dropcity']/li[@val='"+BUY_ADDR_CITY+"'])");
		selenium.click("xpath=(//ul[@id='droparea']/li[@val='"+BUY_ADDR_AREA+"'])");
		selenium.click("//div[@id='buy_now']/div/div[2]/div[2]/div[15]/div[4]/a/span");
		
		System.out.println("下单省市区为："+BUY_ADDR_PROVINCE+BUY_ADDR_CITY+BUY_ADDR_AREA);
		//选择产品id
	    selenium.click("id=" + PRD_ID_1);
	    
	  //选择购买数量。数量要由int型的要转换成string类型的
	    selenium.type("xpath=(//input[@type='text'])[4]", Integer.toString(PRD_NUM_1));
	    
	  //判断销售模式是否正确，不正确则返回测试结果
	    int resultnumber = 1;
	  	String name1=selenium.getText("xpath=//a[@class='btn1 btn_payfor']");
	  	if("立即结算".equals(name1)){
	  		if ("0".equals(IS_PRESELL_1)){
	  			System.out.println("销售模式是正常销售，测试的是非预售，匹配！");
	  			System.out.println("立即购买alta，并去购物车结算！");
	  			resultnumber = 1;
	  	       }else{ 
	  		    System.out.println("销售模式是正常销售，测试的是预售，不匹配！直接返回测试结果~");
	  		    resultnumber = 2;
	  		  }
	  	}else if("立即预定".equals(name1)){
	  		if ("0".equals(IS_PRESELL_1)){
	  			System.out.println("销售模式是预售，测试的是非预售，不匹配！直接返回测试结果~");
	  			resultnumber = 2;
	  		}else{ 
	  			System.out.println("销售模式是预售，测试的是预售，匹配！");
	  			System.out.println("立即购买alta，并去购物车结算！");
	  			resultnumber = 1;
	  		   }
	  		}else if("到货通知".equals(name1)){
	  			System.out.println("目前商品缺货!");
	  			resultnumber = 3;
	  		}else {
	  			System.out.println("目前商品下架!");
	  			resultnumber = 3;
	  		}
	  	
	  	if(resultnumber ==3){
	  		System.out.println("商品1已下架或缺货，退出测试");
	  		String TEST_RESULT_REASON = "商品1已下架或缺货，退出测试";
	  		WriteOrderResult p = new WriteOrderResult();
	  		p.writeresult2(TESULT_ID, TEST_NO,TEST_RESULT_REASON);
	  	}else if (resultnumber ==2){
	  		System.out.println("商品1销售模式与测试数据不匹配，退出测试");
	  		String TEST_RESULT_REASON = "商品1销售模式与测试数据不匹配，退出测试";
	  		WriteOrderResult q = new WriteOrderResult();
	  		q.writeresult2(TESULT_ID, TEST_NO,TEST_RESULT_REASON);
	  	}
	  		
	  	//点击立即结算
		selenium.click("css=a[name=\"addToCart\"] > span.bgcolor_edf0e9");
		selenium.waitForPageToLoad("30000");
		
		//继续购物，选择第二种商品
		System.out.println("点击继续购物，开始选购第二种Alta商品");
		selenium.click("css=div.main_buy2_container > a.btn1 > span");
		
		//进入商城ALTA页
		selenium.setSpeed("2000");
		selenium.click("name=Alta");
		selenium.waitForPageToLoad("30000");
		System.out.println("alta详情页打开 完毕！");
		
		//点击ALTA页面 立即购买 按钮
		selenium.setSpeed("2000");
		selenium.click("css=div[name=\"buyNow\"]");
		
		//选择省市区

		selenium.click("css=div.buy_now_sendto_select");
		selenium.click("xpath=(//ul[@id='dropprovince']/li[@val='"+BUY_ADDR_PROVINCE+"'])");
		selenium.click("xpath=(//ul[@id='dropcity']/li[@val='"+BUY_ADDR_CITY+"'])");
		selenium.click("xpath=(//ul[@id='droparea']/li[@val='"+BUY_ADDR_AREA+"'])");
		selenium.click("//div[@id='buy_now']/div/div[2]/div[2]/div[15]/div[4]/a/span");
		
		System.out.println("下单省市区为："+BUY_ADDR_PROVINCE+BUY_ADDR_CITY+BUY_ADDR_AREA);
		//选择产品id
	    selenium.click("id=" + PRD_ID_2);
	    
	  //选择购买数量。数量要由int型的要转换成string类型的
	    selenium.type("xpath=(//input[@type='text'])[4]", Integer.toString(PRD_NUM_2));
	    
	  //判断销售模式是否正确，不正确则返回测试结果
	    int resultnumber1 = 1;
	  	String name2=selenium.getText("xpath=//a[@class='btn1 btn_payfor']");
	  	if("立即结算".equals(name2)){
	  		if ("0".equals(IS_PRESELL_2)){
	  			System.out.println("销售模式是正常销售，测试的是非预售，匹配！");
	  			System.out.println("立即购买alta，并去购物车结算！");
	  			resultnumber1 = 1;
	  	       }else{ 
	  	    	System.out.println("销售模式是正常销售，测试的是预售，不匹配！直接返回测试结果~");
  		    	resultnumber1 = 2;
	  		  }
	  	}else if("立即预定".equals(name1)){
	  		if ("0".equals(IS_PRESELL_2)){
	  			System.out.println("销售模式是预售，测试的是非预售，不匹配！直接返回测试结果~");
	  			resultnumber1 = 2;
	  		}else{ 
	  			System.out.println("销售模式是预售，测试的是预售，匹配！");
	  			System.out.println("立即购买alta，并去购物车结算！");
	  			resultnumber1 = 1;
	  		   }
	  		}else if("到货通知".equals(name1)){
	  			System.out.println("目前商品缺货!");
	  			resultnumber1 = 3;
	  		}else {
	  			System.out.println("目前商品下架!");
	  			resultnumber1 = 3;
	  		}
	  		
	 	if(resultnumber1 ==3){
	  		System.out.println("商品2已下架或缺货，退出测试");
	  		String TEST_RESULT_REASON = "商品2已下架或缺货，退出测试";
	  		WriteOrderResult p = new WriteOrderResult();
	  		p.writeresult2(TESULT_ID, TEST_NO,TEST_RESULT_REASON);
	  	}else if (resultnumber1 ==2){
	  		System.out.println("商品2销售模式与测试数据不匹配，退出测试");
	  		String TEST_RESULT_REASON = "商品2销售模式与测试数据不匹配，退出测试";
	  		WriteOrderResult q = new WriteOrderResult();
	  		q.writeresult2(TESULT_ID, TEST_NO,TEST_RESULT_REASON);
	  	}
	  	
	  	//点击立即结算
		selenium.click("css=a[name=\"addToCart\"] > span.bgcolor_edf0e9");
		selenium.waitForPageToLoad("30000");
		
		
		//点击购物车结算
		selenium.setSpeed("3000");
	    ShoppingCart shopCart1 = new ShoppingCart();
		shopCart1.ShoppingCartPC(selenium);
			
	}
	
	
	/*
	 * 方法名：altaBuyNow4 （下单类型：3。购买2种Alta）手机端立即购买alta且到达结算页
	 * 参数in： PRD_ID_1 商品1 ID；
	 *         PRD_NUM_1  商品1 ID购买数量；      
	 *         IS_PRESELL_1  商品1 是否预售：1 是，0 否
	 *         PRESELL_DATE_1  商品1 预售日期
	 *         PRD_ID_2 商品2 ID；
	 *         PRD_NUM_2  商品2 ID购买数量；      
	 *         IS_PRESELL_2  商品2 是否预售：1 是，0 否
	 *         PRESELL_DATE_2  商品2 预售日期
	 *         BUY_ADDR_PROVINCE 购物车地址：省
	 *         BUY_ADDR_CITY 购物车地址：市
	 *         BUY_ADDR_AREA 购物车地址：区
	 * 参数out：void
	 * */
	public void altaBuyNow4(Selenium selenium, ResultSet sourceData) throws Exception{
			
		int PRD_ID_1 = sourceData.getInt("PRD_ID_1");
		int PRD_NUM_1 = sourceData.getInt("PRD_NUM_1");
		String IS_PRESELL_1 = sourceData.getString("IS_PRESELL_1");
		int PRD_ID_2 = sourceData.getInt("PRD_ID_2");
		int PRD_NUM_2 = sourceData.getInt("PRD_NUM_2");
		String IS_PRESELL_2 = sourceData.getString("IS_PRESELL_2");
		
		String BUY_ADDR_PROVINCE = sourceData.getString("BUY_ADDR_PROVINCE");
		String BUY_ADDR_CITY = sourceData.getString("BUY_ADDR_CITY");
		String BUY_ADDR_AREA = sourceData.getString("BUY_ADDR_AREA");
		
		String TEST_NO = sourceData.getString("TEST_NO");
        int	TESULT_ID = 0;
		
		//获取本次测试结果的ID
		GetMaxResultId getMaxResultId = new GetMaxResultId();
		TESULT_ID = getMaxResultId.getMaxResultId();
		
		//点击ALTA页面 立即购买 按钮
		selenium.setSpeed("2000");
		selenium.click("link=商城");
		selenium.click("id=malltv");
		selenium.click("id=btn_buyImmediately");
		System.out.println("手机端Alta购物界面打开 完毕！");
		
		//选择省市区
		selenium.setSpeed("2000");
        selenium.select("id=dropprovince", "label="+BUY_ADDR_PROVINCE+"");
        selenium.select("id=dropcity", "label="+BUY_ADDR_CITY+"");
        selenium.select("id=droparea", "label="+BUY_ADDR_AREA+"");
                
		//选择商品ID：标准 Alta 挂装---20012   标准 Alta 座装---20009 限量 Alta 挂装---20018  限量 Alta 座装---20016
        if(PRD_ID_1==20009){
       	 selenium.click("link=标准 Alta 座装");
        }else if(PRD_ID_1==20012){
       	 selenium.click("link=标准 Alta 挂装");
        }else if(PRD_ID_1==20016){
       	 selenium.click("link=限量 Alta 座装");
        }else
            selenium.click("link=限量 Alta 挂装");
		
		//选择购买数量
	 	 selenium.type("id=productNum", Integer.toString(PRD_NUM_1));
		
		//判断销售模式是否正确
	 	
	 	int resultnumber = 1;
		String name1=selenium.getText("xpath=//a[@id='operbutton']");
		if("立即结算".equals(name1)){
	  		if ("0".equals(IS_PRESELL_1)){
	  			System.out.println("销售模式是正常销售，测试的是非预售，匹配！");
	  			System.out.println("立即购买alta，并去购物车结算！");
	  			resultnumber = 1;
	  	       }else{ 
	  		    System.out.println("销售模式是正常销售，测试的是预售，不匹配！直接返回测试结果~");
	  		    resultnumber = 2;
	  		  }
	  	}else if("立即预定".equals(name1)){
	  		if ("0".equals(IS_PRESELL_1)){
	  			System.out.println("销售模式是预售，测试的是非预售，不匹配！直接返回测试结果~");
	  			resultnumber = 2;
	  		}else{ 
	  			System.out.println("销售模式是预售，测试的是预售，匹配！");
	  			System.out.println("立即购买alta，并去购物车结算！");
	  			resultnumber = 1;
	  		   }
	  		}else if("到货通知".equals(name1)){
	  			System.out.println("目前商品缺货!");
	  			resultnumber = 3;
	  		}else {
	  			System.out.println("目前商品下架!");
	  			resultnumber = 3;
	  		}
	  	
		  	if(resultnumber ==3){
		  		System.out.println("商品1已下架或缺货，退出测试");
		  		String TEST_RESULT_REASON = "商品1已下架或缺货，退出测试";
		  		WriteOrderResult p = new WriteOrderResult();
		  		p.writeresult2(TESULT_ID, TEST_NO,TEST_RESULT_REASON);
		  	}else if (resultnumber ==2){
		  		System.out.println("商品1销售模式与测试数据不匹配，退出测试");
		  		String TEST_RESULT_REASON = "商品1销售模式与测试数据不匹配，退出测试";
		  		WriteOrderResult q = new WriteOrderResult();
		  		q.writeresult2(TESULT_ID, TEST_NO,TEST_RESULT_REASON);
		  	}
		
	  		
	  	//点击立即结算
		selenium.setSpeed("2000");
		selenium.click("id=operbutton");
			
		//继续购物，选择第二种Alta商品
		selenium.click("css=a.homebtn_right");
		selenium.setSpeed("2000");
		selenium.click("link=商城");
		selenium.click("id=malltv");
		selenium.click("id=btn_buyImmediately");
		System.out.println("手机端Alta购物界面打开 完毕！");
		
		//选择省市区
		selenium.setSpeed("2000");
        selenium.select("id=dropprovince", "label="+BUY_ADDR_PROVINCE+"");
        selenium.select("id=dropcity", "label="+BUY_ADDR_CITY+"");
        selenium.select("id=droparea", "label="+BUY_ADDR_AREA+"");
        
        
		//选择商品ID：标准 Alta 挂装---20012   标准 Alta 座装---20009 限量 Alta 挂装---20018  限量 Alta 座装---20016
        if(PRD_ID_2==20009){
       	 selenium.click("link=标准 Alta 座装");
        }else if(PRD_ID_2==20012){
       	 selenium.click("link=标准 Alta 挂装");
        }else if(PRD_ID_2==20016){
       	 selenium.click("link=限量 Alta 座装");
        }else
            selenium.click("link=限量 Alta 挂装");
		
		//选择购买数量
	 	 selenium.type("id=productNum", Integer.toString(PRD_NUM_2));
		
		//判断销售模式是否正确
	 	
	 	int resultnumber2 = 1;
		String name2=selenium.getText("xpath=//a[@id='operbutton']");
		if("立即结算".equals(name2)){
	  		if ("0".equals(IS_PRESELL_2)){
	  			System.out.println("销售模式是正常销售，测试的是非预售，匹配！");
	  			System.out.println("立即购买alta，并去购物车结算！");
	  			resultnumber2 = 1;
	  	       }else{ 
	  		    System.out.println("销售模式是正常销售，测试的是预售，不匹配！直接返回测试结果~");
	  		    resultnumber2 = 2;
	  		  }
	  	}else if("立即预定".equals(name2)){
	  		if ("0".equals(IS_PRESELL_2)){
	  			System.out.println("销售模式是预售，测试的是非预售，不匹配！直接返回测试结果~");
	  			resultnumber2 = 2;
	  		}else{ 
	  			System.out.println("销售模式是预售，测试的是预售，匹配！");
	  			System.out.println("立即购买alta，并去购物车结算！");
	  			resultnumber2 = 1;
	  		   }
	  		}else if("到货通知".equals(name2)){
	  			System.out.println("目前商品缺货!");
	  			resultnumber2 = 3;
	  		}else {
	  			System.out.println("目前商品下架!");
	  			resultnumber2 = 3;
	  		}
	  	
	  	if(resultnumber2 == 3){
	  		System.out.println("商品2已下架或缺货，退出测试");
	  		String TEST_RESULT_REASON = "商品2已下架或缺货，退出测试";
	  		WriteOrderResult p = new WriteOrderResult();
	  		p.writeresult2(TESULT_ID, TEST_NO,TEST_RESULT_REASON);
	  	}else if (resultnumber2 == 2){
	  		System.out.println("商品2销售模式与测试数据不匹配，退出测试");
	  		String TEST_RESULT_REASON = "商品2销售模式与测试数据不匹配，退出测试";
	  		WriteOrderResult q = new WriteOrderResult();
	  		q.writeresult2(TESULT_ID, TEST_NO,TEST_RESULT_REASON);
	  	}
		
	  		
	  	//点击立即结算
		selenium.setSpeed("2000");
		selenium.click("id=operbutton");
		
		
		//点击购物车结算
		selenium.setSpeed("3000");
	    ShoppingCart shopCart2 = new ShoppingCart();
		shopCart2.ShoppingCartMobile(selenium);
			
	}	
	
}
