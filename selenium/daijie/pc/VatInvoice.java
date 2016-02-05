package daijie.pc;

import java.sql.ResultSet;

import com.thoughtworks.selenium.Selenium;

public class VatInvoice {
	/*
	 * 本方法为PC端订单结算页的发票信息编辑，包括普票和增票 
	 * 输入参数：
	 *         Selenium（selenium测试必备变量）
	 *         ResultSet（某条测试数据记录）
	 * 输出参数：无
	 * @Author DaiJie
	 * @Date 2016-1-30
	 */
	public void fillVatInvoice(Selenium selenium, ResultSet sourceData)throws Exception

	{
		// 选择“增值税发票”选项
		selenium.click("id=companyInvoice");
		
		// 读取数据，写入之
		selenium.type("id=company", sourceData.getString("INVOICE_COMPANY")); 
		selenium.type("id=taxIdentify",sourceData.getString("INVOICE_TAXPAYER"));
		selenium.type("id=regAddress", sourceData.getString("REGISTRY_ADDRESS"));
		selenium.type("id=regGhone", sourceData.getString("INVOICE_TELEPHONE"));
		selenium.type("id=openBank", sourceData.getString("INVOICE_BANK_NAME"));
		selenium.type("id=account",sourceData.getString("INVOICE_BANK_ACCOUNT"));
		
		// 保存发票信息
		selenium.click("css=div.divsavebillinfo.btnsavebillinfo");
		
	}
}
