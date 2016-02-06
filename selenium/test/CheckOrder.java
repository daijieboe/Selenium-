package test;
import java.sql.ResultSet;
import com.thoughtworks.selenium.SeleneseTestCase;
import com.thoughtworks.selenium.Selenium;

public class CheckOrder extends SeleneseTestCase{
	public CheckOrder(Selenium s)
	{
		selenium = s;
	}
	
	/*
	 * 方法名：checkOrder 订单确认页：付款方式、配送信息、发票的情况
	 * 参数in： 
	 *  DELIVER_DATE  预约配送时间
	 *  PAY_MODE  支付方式：  1:在线支付  0:货到付款
	 *  IS_INVOICE 是否需要发票：1：是，0：否
	 *  IS_VAT_INVOICE 是否增值税发票：1：是，0：否
	 *  INVOICE_COMPANY 公司名称
	 *  INVOICE_TAXPAYER 纳税人识别号
	 *  REGISTRY_ADDRESS 注册地址
	 *  INVOICE_TELEPHONE 注册电话
	 *  INVOICE_BANK_NAME 开户银行
	 *  INVOICE_BANK_ACCOUNT 开户账号
	 *  INVOICE_HEAD  普票：抬头
	 *  INVOICE_CONTENT  普票：内容 
	 *  REMARK 订单备注信息
	 * 参数void
	 * */
	public void checkOrder(Selenium selenium, ResultSet sourceData) throws Exception{
		
		
		
	}
}
