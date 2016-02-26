package test;

import java.sql.Connection;
import java.sql.ResultSet;

import com.thoughtworks.selenium.Selenium;

import daijie.basic.GetConnection;

/*
 * 方法名：orderBuyAddress 修改订单省市区
 * 参数in： ORDER_ADDR_PROVINCE  省
 * 参数in： ORDER_ADDR_CITY 市
 * 参数in：ORDER_ADDR_AREA 区
 * 参数out：void
 * */
public class AddressChange_pc{
public void addressChange_PC(Selenium selenium, ResultSet sourceData) throws Exception
{
	//获取当前会员的默认地址ID号
		
	String USERNAME = null;
	String ORDER_ADDR_PROVINCE = null;
	String ORDER_ADDR_CITY = null;
	String ORDER_ADDR_AREA = null;
	USERNAME = sourceData.getString("USERNAME");
	ORDER_ADDR_PROVINCE = sourceData.getString("ORDER_ADDR_PROVINCE");
	ORDER_ADDR_CITY = sourceData.getString("ORDER_ADDR_CITY");
	ORDER_ADDR_AREA = sourceData.getString("ORDER_ADDR_AREA");
	
	int i = 1;
    String ID = null;
	Connection conn = new GetConnection().getConnection("sit");
	String userSql3= "select t.ID from MB_ADDR t ,MB_MEMBER k where t.member_id=k.member_id and k.account_name='"+USERNAME+"' and t.is_default='1'";
	java.sql.Statement stmt1 = conn.createStatement();
	ResultSet rs3 = stmt1.executeQuery(userSql3);	
	while (rs3.next() && i != 0) {
		ID = rs3.getString("ID");
		i = 0;//结束循环，只取第一个数据
	}
	System.out.println(ID);
if (ID!=null){
	selenium.setSpeed("2000");
	selenium.click("class=mycheck mycheck_checked inlineleft div_marginright7");
	selenium.click("//div[@onclick='updateAddress("+ID+")']");
}else {
	selenium.click("class=mycheck mycheck_checked inlineleft div_marginright7");
	String AddressId = selenium.getAttribute("class=mycheck mycheck_checked inlineleft div_marginright7@pvalue");
    System.out.println("AddressIdAddressIdAddressId:"+AddressId);
    selenium.click("//div[@onclick='updateAddress("+AddressId+")']");
}
	//输入订单地址，提交
    selenium.setSpeed("2000");
	selenium.click("name=provinceName");
	selenium.click("//li[@value='"+ORDER_ADDR_PROVINCE+"']");
	selenium.click("name=cityName");
	selenium.click("//li[@value='"+ORDER_ADDR_CITY+"']");
	selenium.click("name=districName");
	selenium.click("//li[@value='"+ORDER_ADDR_AREA+"']");

	selenium.click("css=a.btn1.saveAddress > span.bgcolor_edf0e9");
	selenium.waitForPageToLoad("30000");

	

	

}
}

