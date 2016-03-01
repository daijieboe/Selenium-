package test;

import java.sql.Connection;

import daijie.basic.GetConnection;

public class UpdateOrderPay {
	/*
	 * 方法名：updateOrderPay 后台修改订单付款及待派单状态
	 * 参数in：orderId
	 * 参数out：void
	 * */
	public void updateOrderPay(String orderId,String PAY_MODE,String orderOrigin) throws Exception

	{
		Connection conn = new GetConnection().getConnection("sit");
		String insertSql2= "update OR_ORDER t SET t.LOCK_FLAG=' ' where t.order_no='"+orderId+"'";
		String insertSql3= "update OR_ORDER t SET t.STATUS='30' where t.order_no='"+orderId+"'";
		String insertSql4= "update OR_ORDER t SET t.PAY_STATUS='20' where t.order_no='"+orderId+"'";
		String insertSql5= "update OR_ORDER t SET t.PAY_STATUS='30' where t.order_no='"+orderId+"'";
		String insertSql6= "UPDATE PM_PAYMENT t SET t.Status='2' WHERE T.ORDER_ID='"+orderId+"' AND T.PAYMENT_MODE_ID=1";
		String insertSql7= "UPDATE PM_PAYMENT t SET t.Status='2' WHERE T.ORDER_ID='"+orderId+"'";
		System.out.println(insertSql3);
		System.out.println(insertSql4);	
		System.out.println(insertSql6);
		
		java.sql.Statement stmt = conn.createStatement();
		if("0".equals(PAY_MODE)){
		System.out.println("****************PAY_MODE is:" + PAY_MODE);
		stmt.executeUpdate(insertSql2);
		stmt.executeUpdate(insertSql3);
		stmt.executeUpdate(insertSql4);
		stmt.executeUpdate(insertSql6);
		}else{
		stmt.executeUpdate(insertSql2);
		stmt.executeUpdate(insertSql3);
		stmt.executeUpdate(insertSql5);
		stmt.executeUpdate(insertSql7);
		}
		System.out.println("设置订单状态、付款状态完成！");
	}
}
