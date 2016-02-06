package daijie.mobile;

import java.sql.ResultSet;
import com.thoughtworks.selenium.Selenium;

public class VatInvoiceM {
	/*
	 * 本方法为手机端订单结算页的发票信息编辑，包括普票和增票 
	 * 输入参数：
	 *         Selenium（selenium测试必备变量）
	 *         ResultSet（某条测试数据记录）
	 * 输出参数：无
	 * @Author DaiJie
	 * @Date 2016-1-30
	 */
	public void fillVatInvoice(Selenium selenium, ResultSet sourceData)
			throws Exception

	{
		// 选择“增值税发票”选项
		selenium.click("id=checkaddedtaxbill");

		selenium.type("id=invoiceCompanyName",sourceData.getString("INVOICE_COMPANY")); 
		selenium.type("id=invoiceRatepayerNo",sourceData.getString("INVOICE_TAXPAYER"));
		selenium.type("id=invoiceRegAddr",sourceData.getString("REGISTRY_ADDRESS"));
		selenium.type("id=invoiceRegPhone",sourceData.getString("INVOICE_TELEPHONE"));
		selenium.type("id=invoiceBank",sourceData.getString("INVOICE_BANK_NAME"));
		selenium.type("id=invoiceAccount",sourceData.getString("INVOICE_BANK_ACCOUNT"));

		// 保存发票信息
		selenium.click("id=invoiceInfoOk");
		// 等待页面加载
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
