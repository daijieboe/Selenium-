package test;
import com.thoughtworks.selenium.*;

import daijie.basic.GetConnection;
import java.sql.Connection;
import java.sql.ResultSet;


public class Order_status extends SeleneseTestCase{
	
	public ResultSet getOderStatus(String orderNo) throws Exception

	{
		//从数据库 OR_ORDER获取订单的收款单的状态,暂时全部取出 
		Connection conn = new GetConnection().getConnection("sit");
		String orderSql= "select * from OR_ORDER where order_no= '"+orderNo+"'";
		java.sql.Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(orderSql);	
		return rs;
	}
	
	//expectStatus为期望的订单的状态，
		public void checkStatus(String orderNo, String expectStatus)	throws Exception 
		{
			
			String statusNow = null;
			int i = 1;
			ResultSet rs = getOderStatus(orderNo);
			
			// 取出数据
			while (rs.next() && i != 0) {
				statusNow = rs.getString("status");
				System.out.println("get order status :"+statusNow);
				i = 0;// 结束循环，只取第一个数据
			}

			if (expectStatus.equals(statusNow)) {
				System.out.println(expectStatus+statusNow+"数据库里订单的状态是正确的");
			} else {
				System.out.println(expectStatus+statusNow+"数据库里订单的状态是错误的");
			}
		}
	
		public void testUntitled() throws Exception
		{
			checkStatus("S114100020143","81");
		}

}

/*
 * 方法名：updateOrderState 通过数据库将成功的订单状态更改写入测试用例内
 * 参数in：    writeOrderId 下单单号   orderState 订单状态
 * 参数out：void
 * */
//订单状态：10待支付 30待派单  40待出库  50待发货  60待签收  70已签收  90订单成功  81取消待退款  91取消订单  92订单关闭（
//public void updateOrderState(String ORDER_NO,String STATUS) throws Exception
//
//{   		
//	Connection conn = new ConnectPRE ().connectPRE ();
//	String updateSql= "update OR_ORDER set(STATUS) =,VALUES ('"+ORDER_NO+"','"+STATUS+"')";
////	 String sql="update (表名) set  (列名1)=?,列明2=? where (列名)=？";//注意要有where条件 
//	java.sql.Statement stmt = conn.createStatement();
//	stmt.executeUpdate(updateSql);
//	System.out.println("数据写入完毕！");
//}