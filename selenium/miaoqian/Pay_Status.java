package miaoqian;
import com.thoughtworks.selenium.*;

import java.sql.Connection;
import java.sql.ResultSet;


public class Pay_Status extends SeleneseTestCase{
	
	public String getOderStatus(String paymentNo) throws Exception

	{
		String PAY_STATUS = null;
		//从数据库 OR_ORDER获取订单的收款单的状态,暂时全部取出 
		Connection conn = new GetConnection().getConnection();
		String orderSql= "select * from PM_PAYMENT where payment_no= '"+paymentNo+"'";
		java.sql.Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(orderSql);	
		// 提前完成“.next()”
		rs.next();
		PAY_STATUS = rs.getString("ORDER_STATUS");
		return PAY_STATUS;
	}
	
	//expectStatus为期望的收款单的状态，
	public String checkStatus(String paymentNo, String expectStatus)	throws Exception 
	{  
		    String payCheck=null;
		
		// 取出数据
		    String statusNow = null;
			statusNow = getOderStatus("status");
			System.out.println("get order status :"+statusNow);

			// 检查订单状态，正确返回值为“0”，错误为“1”
		if (expectStatus.equals(statusNow)) {
			payCheck="0";
			System.out.println(expectStatus+statusNow+"数据库里收款单的状态是正确的");
		} else {
			payCheck="1";
			System.out.println(expectStatus+statusNow+"数据库里收款单的状态是错误的");
		}
		//返回订单状态检查值
		return payCheck;
	}
//	public void testUntitled() throws Exception
//	{
//		checkStatus("P1151200242281","2");
//	}

}








