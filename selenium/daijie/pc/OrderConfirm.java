package daijie.pc;

import java.sql.ResultSet;

import test.AddressChange_pc;

import com.thoughtworks.selenium.Selenium;

public class OrderConfirm {
	/*
	 * 本方法为填写PC端订单结算页的信息（配送日期、支付方式、发票信息、订单备注），并进行订单提交
	 * 输入参数：
	 *          Selenium（selenium测试必备变量）
	 *          ResultSet（某条测试数据记录）
	 * 输出参数：无
	 * @Author DaiJie
	 * @Date 2016-1-30
	 * */
	public void orderConfirm(Selenium selenium, ResultSet sourceData)
			throws Exception {
		{
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			//编辑配送地址信息
			new AddressChange_pc().addressChange_PC(selenium,sourceData);
			
			// 预约配送日期填写
			selenium.click("xpath=//div[@class='text_datepicker'] / input");
			selenium.click("link=" + sourceData.getString("DELIVER_DATE"));

			// 是否货到付款
			if (sourceData.getString("PAY_MODE").equalsIgnoreCase("0")) {
				selenium.click("id=downline");
			}

			// 如果需要发票
			if (sourceData.getString("IS_INVOICE").equalsIgnoreCase("1")) {
				selenium.click("id=askForInvoice");

				// 是否需要开增值税专用发票
				if (sourceData.getString("IS_VAT_INVOICE").equalsIgnoreCase("1")) {
					// 调用增票开票方法
					VatInvoice v = new VatInvoice();
					v.fillVatInvoice(selenium, sourceData);
				}
				// 开普票
				else {
					selenium.type("id=invoiceTitle",
							sourceData.getString("INVOICE_HEAD"));
					selenium.click("css=div.divsavebillinfo.btnsavebillinfo");
				}
			}else{
				//选择不需要发票并保存
				selenium.click("id=onInvoice");
				selenium.click("css=div.divsavebillinfo.btnsavebillinfo");
			}

			// 添加订单备注
			selenium.click("css=div..div_h42 > div.openclosecircle");
			selenium.type("id=remark", sourceData.getString("REMARK"));
			
			// 提交订单
			selenium.click("css=#sub > span");
		}
	}

}
