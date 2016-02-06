package test;

import java.sql.Connection;

import daijie.basic.GetConnection;

public class WriteOrderResult {
	 
	/*
	 * 方法名：writeresult 将测试结果放入数据库
	 * 参数in：
	 *  TEST_NO 测试用例编码 
	 *  ORDER_NO 测试生成订单号
	 *  ORDER_STATUS 测试生成订单状态
	 *  ORDER_STATUS_NAME 测试生成订单状态名称
	 *  TEST_RESULT 测试结果。1，error  2：成功 
	 *  TEST_RESULT_REASON 测试不通过原因
	 * 参数out：void
	 * */
	public void writeresult(String orderOrigin,int TESULT_ID,String TEST_NO, String ORDER_NO, 
		String ORDER_STATUS, String ORDER_STATUS_NAME, 
		String TEST_RESULT, String TEST_RESULT_REASON) throws Exception{
		
		Connection conn = new GetConnection().getConnection(orderOrigin);
		String insertSql= "insert into SELENIUM_OR_RESULT(TESULT_ID,TEST_NO,ORDER_NO,ORDER_STATUS,ORDER_STATUS_NAME,TEST_RESULT,TEST_RESULT_REASON,CREAT_DATE) VALUES ("+TESULT_ID+",'"+TEST_NO+"','"+ORDER_NO+"','"+ORDER_STATUS+"','"+ORDER_STATUS_NAME+"','"+TEST_RESULT+"','"+TEST_RESULT_REASON+"',sysdate)";
		System.out.println(insertSql);
	    java.sql.Statement stmt = conn.createStatement();
	 	stmt.executeUpdate(insertSql);
		System.out.println("测试结果数据写入完毕！");
		
	}
	
	/*
	 * 方法名：writeresult2 将测试结果放入数据库---测试不成功的数据
	 * 参数in：
	 *  TEST_NO 测试用例编码 
	 *  ORDER_NO 测试生成订单号
	 *  ORDER_STATUS 测试生成订单状态
	 *  ORDER_STATUS_NAME 测试生成订单状态名称
	 *  TEST_RESULT 测试结果。1，error  2：成功 
	 *  TEST_RESULT_REASON 测试不通过原因
	 * 参数out：void
	 * */	
	public void writeresult2(String orderOrigin,int TESULT_ID,String TEST_NO, String TEST_RESULT_REASON) throws Exception{
		
		String TEST_RESULT = "1";
		Connection conn = new GetConnection().getConnection(orderOrigin);
		String insertSql= "insert into SELENIUM_OR_RESULT(TESULT_ID,TEST_NO,TEST_RESULT,TEST_RESULT_REASON,CREAT_DATE) VALUES ("+TESULT_ID+",'"+TEST_NO+"','"+TEST_RESULT+"','"+TEST_RESULT_REASON+"',sysdate)";
		System.out.println(insertSql);
	    java.sql.Statement stmt = conn.createStatement();
	 	stmt.executeUpdate(insertSql);
		System.out.println("测试结果数据写入完毕！");
		System.exit(0);
	
	}
}


