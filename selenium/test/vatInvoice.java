package test;

import com.thoughtworks.selenium.Selenium;

public class vatInvoice {
	/*
	 * 方法名：vatInvoice 填写增值税发票信息
	 * 参数in： company  单位名称
	 * 参数in： taxIdentify 纳税人识别号
	 * 参数in： regAddress 注册地址
	 * 参数in： regGhone 注册电话
	 * 参数in： openBank  开户银行
	 * 参数in： account  银行账户
	 * 参数out：void
	 * */
	
	
	public void fillInvoice(String company,
			String taxIdentify,
			String regAddress,
			String regGhone,
			String openBank,
			String account,
			Selenium selenium) throws Exception

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
}
