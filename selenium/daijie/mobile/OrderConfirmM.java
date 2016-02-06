package daijie.mobile;

import java.sql.ResultSet;
import com.thoughtworks.selenium.Selenium;

public class OrderConfirmM {
	/*
	 * 本方法为填写手机端订单结算页的信息（配送地址、配送日期、支付方式、发票信息、订单备注），并进行订单提交
	 * 输入参数：
	 *          Selenium（selenium测试必备变量）
	 *          ResultSet（某条测试数据记录）
	 * 输出参数：无
	 * @Author DaiJie
	 * @Date 2016-2-1
	 * */
	public void orderConfirm(Selenium selenium, ResultSet sourceData) throws Exception {
		{
			//修改配送地址
			new AddressChangeM().addressChange(selenium, sourceData);
			
			// 预约配送日期
			selenium.click("id=deliverDate");
			// 等待日期组件加载
			try {
				Thread.sleep(1500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//选择日期
			selenium.click("link=" + sourceData.getString("DELIVER_DATE"));

			// 是否选择货到付款
			if (sourceData.getString("PAY_MODE").equalsIgnoreCase("0")) {
				selenium.click("xpath=(//span[@name='paytype'])[2]");
			}

			// 是否需要发票
			if (sourceData.getString("IS_INVOICE").equalsIgnoreCase("1")) {
				selenium.click("css=span.icondetailright > a > img");
				// 等待页面加载
				try {
					Thread.sleep(1500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				// 是否需要开增值税专用发票
				if (sourceData.getString("IS_VAT_INVOICE")
						.equalsIgnoreCase("1")) {
					// 调用增票开票方法
					VatInvoiceM v = new VatInvoiceM();
					v.fillVatInvoice(selenium, sourceData);
				}
				// 开普票
				else {
					selenium.type("id=invoiceTitle", sourceData.getString("INVOICE_HEAD"));
					selenium.click("id=invoiceInfoOk");
					// 等待页面加载
					try {
						Thread.sleep(1500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}

			// 添加订单备注
			selenium.click("css=#orderMemo > div.info_item2_container > span.icondetailright > a > img");
			// 等待页面加载
			try {
				Thread.sleep(1500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			//填写备注并保存
			selenium.type("id=orderMemoText",sourceData.getString("REMARK"));
			selenium.click("id=orderMemoOk");
			// 等待页面加载
			try {
				Thread.sleep(1500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			// 提交订单
			selenium.click("id=confirmOrderOk");
		}
	}
}
