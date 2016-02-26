package daijie.mobile;

import java.sql.ResultSet;

import test.GetMaxResultId;
import test.WriteOrderResult;

import com.thoughtworks.selenium.Selenium;

public class MixedOrderM {
	/*
	 * 本方法为手机端组合订单下单功能
	 * 输入参数： Selenium（selenium测试必备变量） 
	 *           sourceData（测试数据）
	 * 输出参数：无
	 * 
	 * @Author DaiJie
	 * 
	 * @Date 2016-2-25
	 */
	public void mixedOrderM(Selenium selenium, ResultSet sourceData)
			throws Exception {

		int productId1 = sourceData.getInt("PRD_ID_1");
		int productNum1 = sourceData.getInt("PRD_NUM_1");
		int productId2 = sourceData.getInt("PRD_ID_2");
		int productNum2 = sourceData.getInt("PRD_NUM_2");
		String TEST_NO = sourceData.getString("TEST_NO");
		String BUY_ADDR_PROVINCE = sourceData.getString("BUY_ADDR_PROVINCE");
		String BUY_ADDR_CITY = sourceData.getString("BUY_ADDR_CITY");
		String BUY_ADDR_AREA = sourceData.getString("BUY_ADDR_AREA");
        int	TESULT_ID = 0;
		
		//获取本次测试结果的ID
		GetMaxResultId getMaxResultId = new GetMaxResultId();
		TESULT_ID = getMaxResultId.getMaxResultId("sit");

		
		//点击ALTA页面 立即购买 按钮
		selenium.setSpeed("1000");
		selenium.click("link=商城");
		selenium.click("id=malltv");
		selenium.click("id=btn_buyImmediately");
		System.out.println("手机端Alta购物界面打开 完毕！");
		
		//选择省市区
		selenium.setSpeed("2000");
        selenium.select("id=dropprovince", "label="+BUY_ADDR_PROVINCE+"");
        selenium.select("id=dropcity", "label="+BUY_ADDR_CITY+"");
        selenium.select("id=droparea", "label="+BUY_ADDR_AREA+"");
        
        System.out.println(productId1);
		//选择商品ID：标准 Alta 挂装---20012   标准 Alta 座装---20009 限量 Alta 挂装---20018  限量 Alta 座装---20016
        if(productId1==20009){
       	 selenium.click("link=标准 Alta 座装");
        }else if(productId1==20012){
       	 selenium.click("link=标准 Alta 挂装");
        }else if(productId1==20016){
       	 selenium.click("link=限量 Alta 座装");
        }else
            selenium.click("link=限量 Alta 挂装");
		
		//选择购买数量
	 	 selenium.type("id=productNum", Integer.toString(productNum1));
		
		// 从结算按钮判断商品销售是否正常，如无货或下架则退出测试
		String billingHintAlta = selenium.getText("xpath=//a[@id='operbutton']");

		if (billingHintAlta.equalsIgnoreCase("到货通知") || billingHintAlta.equalsIgnoreCase("商品已下架")) {
			System.out.println("商品已下架或缺货，退出测试");
			String TEST_RESULT_REASON = "商品1已下架或缺货，退出测试";

			// 记录测试结果
			WriteOrderResult p = new WriteOrderResult();
			p.writeresult2("sit", TESULT_ID, TEST_NO, TEST_RESULT_REASON);

			// 退出当前程序
			System.exit(0);
		}
		
	  		
	  	//点击立即结算
		selenium.click("id=operbutton");
		

		// 继续购买周边产品
		selenium.setSpeed("1000");
		selenium.click("link=网站地图");
		selenium.click("link=周边商品");
		System.out.println("手机端周边产品界面打开 完毕！");
		try {
			Thread.sleep(1500);// 暂停1.5秒后程序继续执行
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//选择产品id
		selenium.click("xpath= //a[@productid='"+productId2+"']");
		selenium.click("id=buyItImmediately");
		
		//选择购买数量
		selenium.type("id=productNum", Integer.toString(productNum2));
		
		//获取SKU价格信息
		String price=selenium.getText("id=priceSum");
		System.out.println("购买商品价格："+price);
		
		// 从结算按钮判断商品销售是否正常，如无货或下架则退出测试
		String billingHintPeri = selenium.getText("xpath=//a[@id='operbutton']");

		if (billingHintPeri.equalsIgnoreCase("到货通知")|| billingHintPeri.equalsIgnoreCase("商品已下架")) {
			System.out.println("商品已下架或缺货，退出测试");
			String TEST_RESULT_REASON = "商品1已下架或缺货，退出测试";

			// 记录测试结果
			WriteOrderResult p = new WriteOrderResult();
			p.writeresult2("sit", TESULT_ID, TEST_NO, TEST_RESULT_REASON);

			// 退出当前程序
			System.exit(0);
		}
			
		
		//点击加入购物车
		selenium.click("id=operbutton");
		selenium.click("id=goCarGoods");
	}
}
