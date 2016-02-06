package daijie.mobile;

import java.sql.ResultSet;
import com.thoughtworks.selenium.Selenium;

public class AddressChangeM {
	/*
	 * 本方法为手机端订单结算页的配送地址编辑 
	 * 输入参数：
	 *         Selenium（selenium测试必备变量）
	 *         ResultSet（某条测试数据记录）
	 * 输出参数：无
	 * @Author DaiJie
	 * @Date 2016-1-30
	 */
	public void addressChange(Selenium selenium, ResultSet sourceData) throws Exception {
		// 进入地址列表
		selenium.click("css=span.icondetail > img");
		// 等待页面加载
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		// 修改首个配送地址
		selenium.click("css=a.btnedit > img");
		// 等待页面加载
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// 姓名和电话固定不变
		selenium.type("id=userName", "隔壁老王");
		selenium.type("id=mobile", "13399998888");
		// 配送省市区数据
		selenium.select("id=dropprovince",sourceData.getString("ORDER_ADDR_PROVINCE"));
		// 等待市区数据加载
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		selenium.select("id=dropcity", sourceData.getString("ORDER_ADDR_CITY"));
		selenium.select("id=droparea", sourceData.getString("ORDER_ADDR_AREA"));
		// 详细地址和邮编
		selenium.type("id=address_detail", "中国人民解放军日本军区司令部西门对面煎饼摊");
		selenium.type("id=postcode", "111111");
		// 保存
		selenium.click("id=modify_address_btn");
		// 等待页面加载
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//返回订单结算页
		selenium.click("link=返回");
		// 等待页面加载
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}
