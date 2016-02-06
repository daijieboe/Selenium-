package miaoqian;
import com.thoughtworks.selenium.*;

import java.sql.Connection;
import java.sql.ResultSet;

import test.GetConnection;

/*参数说明：
 * orderNo：订单号
 * objectStatus：目标订单状态，包括1（在线支付待签收）、2（货到付款扣款完成）、3（签收成功）
 * orderOrigin：订单来源，包括sit和pre
 * */

public class Order_status extends SeleneseTestCase{
	
	public String getOderStatus(String ORDER_NO) throws Exception

	{
		String ORDER_STATUS = null;
		//从数据库 OR_ORDER获取订单的收款单的状态,暂时全部取出 
		Connection conn = new GetConnection().getConnection();
		String orderSql= "select * from SELENIUM_OR_RESULT where ORDER_NO= '"+ORDER_NO+"'";
		java.sql.Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(orderSql);	
		// 提前完成“.next()”
		rs.next();
		ORDER_STATUS = rs.getString("ORDER_STATUS");
		return ORDER_STATUS;
	}
	
	//expectStatus为期望的订单的状态，
	//订单状态：10待支付 30待派单  40待出库  50待发货  60待签收  70已签收  90订单成功  81取消待退款  91取消订单  92订单关闭
		public String checkStatus(String ORDER_NO, String expectStatus)	throws Exception 
		{
			String orderCheck=null;
			
			// 取出数据
			String statusNow = null;
			statusNow = getOderStatus(ORDER_NO);
			System.out.println("get order status :"+statusNow);

			// 检查订单状态，正确返回值为“0”，错误为“1”
			if (expectStatus.equals(statusNow)) {
				orderCheck="0";
				System.out.println(expectStatus+statusNow+"数据库里订单的状态是正确的");
			} else {
				orderCheck="1";
				System.out.println(expectStatus+statusNow+"数据库里订单的状态是错误的");
			}
			
		//返回订单状态检查值
			return orderCheck;
		}
	
//		public void testUntitled() throws Exception
//		{
//			checkStatus(ORDER_NO,"30");
//		}

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