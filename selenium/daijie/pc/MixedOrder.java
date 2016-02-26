package daijie.pc;

import java.sql.ResultSet;

import test.GetMaxResultId;
import test.WriteOrderResult;

import com.thoughtworks.selenium.Selenium;

public class MixedOrder {
	/*
	 * 本方法为PC端组合订单下单功能
	 * 输入参数： Selenium（selenium测试必备变量） 
	 *           sourceData（测试数据）
	 * 输出参数：无
	 * 
	 * @Author DaiJie
	 * 
	 * @Date 2016-2-25
	 */
	public void mixedOrder(Selenium selenium, ResultSet sourceData)
			throws Exception {

		// 获取基本测试数据
		String TEST_URL = sourceData.getString("TEST_URL");
		String orderOrigin = TEST_URL.substring(7, 10);
		System.out.println("orderOrigin:" + orderOrigin);

		// --------------------buy some Alta------------------------
		int PRD_ID_1 = sourceData.getInt("PRD_ID_1");
		int PRD_NUM_1 = sourceData.getInt("PRD_NUM_1");
		int PRD_ID_2 = sourceData.getInt("PRD_ID_2");
		int PRD_NUM_2 = sourceData.getInt("PRD_NUM_2");
		String TEST_NO = sourceData.getString("TEST_NO");

		// String PRESELL_DATE_1 = sourceData.getString("PRESELL_DATE_1");
		String BUY_ADDR_PROVINCE = sourceData.getString("BUY_ADDR_PROVINCE");
		String BUY_ADDR_CITY = sourceData.getString("BUY_ADDR_CITY");
		String BUY_ADDR_AREA = sourceData.getString("BUY_ADDR_AREA");
		int TESULT_ID = 0;
		// 获取本次测试结果的ID
		GetMaxResultId getMaxResultId = new GetMaxResultId();
		TESULT_ID = getMaxResultId.getMaxResultId("sit");

		// 点击商城ALTA页
		selenium.click("name=Alta");
		System.out.println("alta详情页打开 完毕！");
		// 等待页面加载
		try {
		Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// 点击ALTA页面 立即购买 按钮
		selenium.click("css=div[name=\"buyNow\"]");
        
		selenium.setSpeed("1000");
		// 选择省市区
		selenium.click("css=div.buy_now_sendto_select");
		selenium.click("xpath=(//ul[@id='dropprovince']/li[@val='" + BUY_ADDR_PROVINCE + "'])");
		selenium.click("xpath=(//ul[@id='dropcity']/li[@val='" + BUY_ADDR_CITY + "'])");
		selenium.click("xpath=(//ul[@id='droparea']/li[@val='" + BUY_ADDR_AREA + "'])");
		selenium.click("//div[@id='buy_now']/div/div[2]/div[2]/div[15]/div[4]/a/span");

		System.out.println("下单省市区为：" + BUY_ADDR_PROVINCE + BUY_ADDR_CITY + BUY_ADDR_AREA);

		// 选择产品id
		selenium.click("id=" + PRD_ID_1);

		// 选择购买数量。注意数量由int转换成string
		selenium.type("xpath=(//input[@type='text'])[4]",
				Integer.toString(PRD_NUM_1));

		// 从结算按钮判断商品销售是否正常，如无货或下架则退出测试
		String billingHintAlta = selenium.getText("xpath=//a[@class='btn1 btn_payfor']");

		if (billingHintAlta.equalsIgnoreCase("到货通知") || billingHintAlta.equalsIgnoreCase("商品已下架")) {
			System.out.println("商品1已下架或缺货，退出测试");
			String TEST_RESULT_REASON = "商品1已下架或缺货，退出测试";

			// 记录测试结果
			WriteOrderResult p = new WriteOrderResult();
			p.writeresult2("sit",TESULT_ID, TEST_NO, TEST_RESULT_REASON);

			// 退出当前程序
			System.exit(0);
		}

		// 点击立即结算
		selenium.click("css=a[name=\"addToCart\"] > span.bgcolor_edf0e9");
		// 等待页面加载
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		// 继续购买周边产品
		selenium.click("link=网站地图");
		selenium.click("xpath=//a[@*='周边产品']");
		selenium.waitForPageToLoad("4000");

		// 选择产品id
		selenium.click("//img[@onclick=\"checkproductinfor('" + PRD_ID_2 + "');\"]");
		System.out.println("产品ID为：" + PRD_ID_2);
		selenium.waitForPageToLoad("30000");

		// 选择省市区
		selenium.click("css=div.buy_now_sendto_select");
		selenium.type("id=dropprovince_text", BUY_ADDR_PROVINCE);
		selenium.type("id=dropcity_text", BUY_ADDR_CITY);
		selenium.type("id=droparea_text", BUY_ADDR_AREA);
		System.out.println("下单省市区为：" + BUY_ADDR_PROVINCE + BUY_ADDR_CITY + BUY_ADDR_AREA);

		// 选择购买数量。数量要由int型的要转换成string类型的
		selenium.type("xpath=//input[@value='1']",Integer.toString(PRD_NUM_2));

		// 从结算按钮判断商品销售是否正常，如无货或下架则退出测试
		String billingHintPeri = selenium.
		    getText("xpath=//div[@class='buy_now_sendto_status']");

		if (billingHintPeri.equalsIgnoreCase("到货通知")|| billingHintPeri.equalsIgnoreCase("商品已下架")) {
			System.out.println("周边商品已下架或缺货，退出测试");
			String TEST_RESULT_REASON = "商品1已下架或缺货，退出测试";

			// 记录测试结果
			WriteOrderResult p = new WriteOrderResult();
			p.writeresult2("sit", TESULT_ID, TEST_NO, TEST_RESULT_REASON);

			// 退出当前程序
			System.exit(0);
		}

		// 点击立即结算
		selenium.click("css=a[name=\"addToCart\"] > span");
		selenium.click("css=a.btn1.div_marginleft20 > span.bgcolor_edf0e9");

	}
}
