package miaoqian;
import java.sql.ResultSet;

import com.thoughtworks.selenium.SeleneseTestCase;
import com.thoughtworks.selenium.Selenium;

import test.ShoppingCart; 


public class PeriBuy extends SeleneseTestCase{
	
	public PeriBuy(Selenium s)
	{
		selenium = s;
	}
	
	/*
	 * 方法名：periBuy （下单类型：2。只是购买周边产品）购买周边产品且到达结算页
	 * 参数in： PRD_ID_1 商品1 ID；
	 *         PRD_NUM_1  商品1 ID购买数量；      
	 *         IS_PRESELL_1  商品1 是否预售：1 是，0 否
	 *         PRESELL_DATE_1  商品1 预售日期
	 *         BUY_ADDR_PROVINCE 购物车地址：省
	 *         BUY_ADDR_CITY 购物车地址：市
	 *         BUY_ADDR_AREA 购物车地址：区
	 * 参数out：void
	 * */
	
	public void periBuy1(Selenium selenium, ResultSet sourceData) throws Exception{
		
		//Connection accessUserData = new GetConnection().getConnection();
		int PRD_ID_1 = sourceData.getInt("PRD_ID_1");
		int PRD_NUM_1 = sourceData.getInt("PRD_NUM_1");
		String IS_PRESELL_1 = sourceData.getString("IS_PRESELL_1");
		System.out.println("预售信息为"+IS_PRESELL_1);
	//	String PRESELL_DATE_1 = sourceData.getString("PRESELL_DATE_1");
		String BUY_ADDR_PROVINCE = sourceData.getString("BUY_ADDR_PROVINCE");
		String BUY_ADDR_CITY = sourceData.getString("BUY_ADDR_CITY");
		String BUY_ADDR_AREA = sourceData.getString("BUY_ADDR_AREA");
		
	        	//点击周边商品页面
				selenium.open("/store/order/order.html");
				selenium.click("name=mall");
				selenium.click("name=Peri_Commodity");
				selenium.waitForPageToLoad("30000");				
			
				//选择产品id
				selenium.click("//img[@onclick=\"checkproductinfor('"+PRD_ID_1+"');\"]");
				System.out.println("产品ID为："+PRD_ID_1);
				selenium.waitForPageToLoad("30000");
				
	        	//选择省市区						
				selenium.click("css=div.buy_now_sendto_select");
				selenium.type("id=dropprovince_text", BUY_ADDR_PROVINCE);
			    selenium.type("id=dropcity_text", BUY_ADDR_CITY);
				selenium.type("id=droparea_text", BUY_ADDR_AREA);
				System.out.println("下单省市区为："+BUY_ADDR_PROVINCE+BUY_ADDR_CITY+BUY_ADDR_AREA);
				
				  //选择购买数量。数量要由int型的要转换成string类型的
			    selenium.type("xpath=(//input[@type='text'])[4]", Integer.toString(PRD_NUM_1));
			    
			  //判断销售模式是否正确，不正确则返回测试结果
			    String state=selenium.getText("xpath=//div[@class='buy_now_sendto_status']");
			  	if("有货".equals(state))
			  	{
			  		System.out.println("销售模式为:有货，正常销售!");
			  		if (IS_PRESELL_1=="0"){
			  			System.out.println("销售模式是正常销售，测试的是非预售，匹配！");
			  	       }else{ 
			  		    System.out.println("销售模式是正常销售，测试的是预售，不匹配！直接返回测试结果~");			  		    
			    }
			  	}else if("立即预定".equals(state)){
			  		if (IS_PRESELL_1=="0"){
			  			System.out.println("销售模式是预售，测试的是非预售，不匹配！直接返回测试结果~");
			  		}else{ 
			  			System.out.println("销售模式是预售，测试的是预售，匹配！");
			  		   }
			  		}else if("到货通知".equals(state)){
			  			System.out.println("目前商品缺货!");
			  		}else {
			  			System.out.println("目前商品下架!");
			  		}
				
				//点击立即结算
				selenium.click("css=a[name=\"addToCart\"] > span");
				selenium.click("css=a.btn1.div_marginleft20 > span.bgcolor_edf0e9");
				selenium.waitForPageToLoad("30000");
				selenium.open("/store/cart/cart.html");
				//*********************miaoqian  delete blow sentence*********************************
//				selenium.click("css=#sub > span");
				selenium.waitForPageToLoad("30000");
				
				
				//点击购物车结算
//				selenium.open("/store/cart/cart.html");
	
//				selenium.click("css=div.text_datepicker_btn");
//				selenium.click("link=29");
				selenium.setSpeed("3000");
			    ShoppingCart shopCart1 = new ShoppingCart();
				shopCart1.ShoppingCartPC(selenium);
			}
	
	

//	/*
//	 * 方法名：periBuy2 （下单类型：2。只是购买周边产品）手机端 购买周边产品且到达结算页
//	 * 参数in： PRD_ID_1 商品1 ID；
//	 *         PRD_NUM_1  商品1 ID购买数量；      
//	 *           IS_PRESELL_1  商品1 是否预售：1 是，0 否
//	 *         PRESELL_DATE_1  商品1 预售日期
//	 *        BUY_ADDR_PROVINCE 购物车地址：省
//	          BUY_ADDR_CITY 购物车地址：市
//	          BUY_ADDR_AREA 购物车地址：区
//	 *参数out：void
//	 
	
	public void periBuy2(Selenium selenium, ResultSet sourceData) throws Exception{
		
		int PRD_ID_1 = sourceData.getInt("PRD_ID_1");
		int PRD_NUM_1 = sourceData.getInt("PRD_NUM_1");
		String IS_PRESELL_1 = sourceData.getString("IS_PRESELL_1");
		
		String BUY_ADDR_PROVINCE = sourceData.getString("BUY_ADDR_PROVINCE");
		String BUY_ADDR_CITY = sourceData.getString("BUY_ADDR_CITY");
		String BUY_ADDR_AREA = sourceData.getString("BUY_ADDR_AREA");
		
//		//点击商城ALTA页
//		selenium.open("/mobileStore/index.html#!");
//		System.out.println("打开 手机端首页 完毕！");
		
		//点击周边商品页面
		System.out.println("打开  完毕！");
		selenium.click("css=a.menubtn");
		selenium.click("css=div.menu_item_btn.menu_item_btn_add");
		selenium.click("link=周边产品");
		System.out.println("手机端周边产品界面打开 完毕！");		
			
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//选择商品ID：JBL onbeat Rumble（橙色）---20028   ONYX音乐行星（白色）---20008 AURA翡翠音响（白色）---20006  AURA翡翠音响（黑色）---20005  
		//音箱（黑色BLK）20029     20031电视
		selenium.click("xpath= //a[@productid='"+PRD_ID_1+"']");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		selenium.click("id=buyItImmediately");
				
		//选择省市区
		selenium.setSpeed("3000");
        selenium.select("id=dropprovince", "label="+BUY_ADDR_PROVINCE+"");
        selenium.select("id=dropcity", "label="+BUY_ADDR_CITY+"");
        selenium.select("id=droparea", "label="+BUY_ADDR_AREA+"");
//		selenium.setSpeed("3000");
//		selenium.select("id=dropprovince", "label=北京");
//        selenium.select("id=dropcity", "label=北京市区");
//        selenium.select("id=droparea", "label=东城区");
//				
		//选择购买数量
	 	 selenium.type("id=productNum", Integer.toString(PRD_NUM_1));
			    
			  //判断销售模式是否正确，不正确则返回测试结果
			    int i = 0;
			    String state=selenium.getText("xpath=//div[@id='availableStockInfo']");
			  	if("有货".equals(state)){
			  		if (IS_PRESELL_1=="0"){
			  			System.out.println("销售模式是正常销售，测试的是非预售，匹配！");
			  			i = 1;
			  	       }else{ 
			  		    System.out.println("销售模式是正常销售，测试的是预售，不匹配！直接返回测试结果~");			  		    
			  		  }
			  	}else if("立即预定".equals(state)){
			  		if (IS_PRESELL_1=="0"){
			  			System.out.println("销售模式是预售，测试的是非预售，不匹配！直接返回测试结果~");
			  		}else{ 
			  			System.out.println("销售模式是预售，测试的是预售，匹配！");
			  		    i = 1;
			  		   }
			  		}else if("到货通知".equals(state)){
			  			System.out.println("目前商品缺货!");
			  		}else {
			  			System.out.println("目前商品下架!");
			  		}
				System.out.println("i="+i);	
				
				//点击加入购物车
				selenium.setSpeed("2000");
				selenium.click("id=operbutton");
				selenium.click("id=goCarGoods");
				
				//点击购物车结算
				selenium.setSpeed("3000");
			    ShoppingCart shopCart2 = new ShoppingCart();
				shopCart2.ShoppingCartMobile(selenium);
			}
	
}
