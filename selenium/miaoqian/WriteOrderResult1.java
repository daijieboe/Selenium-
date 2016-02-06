package miaoqian;
import java.sql.Connection;


public class WriteOrderResult1 {

	/*
	 * 方法名：writeresult1 将测试结果放入数据库
	 * 参数in：
	 *  TEST_NO 测试用例编码 
	 *  ORDER_NO 测试生成订单号
	 *  ORDER_STATUS 测试生成订单状态
	 *  ORDER_STATUS_NAME 测试生成订单状态名称
	 *  TEST_RESULT 测试结果。1，error  2：成功 
	 *  TEST_RESULT_REASON 测试不通过原因
	 * 参数out：void
	 * */
	public void writeresult1(int TESULT_ID,String TEST_NO, String ORDER_NO, 
			String ORDER_STATUS, String ORDER_STATUS_NAME, 
			String TEST_RESULT, String TEST_RESULT_REASON,String CREAT_DATE) throws Exception{
		
		Connection conn = new GetConnection().getConnection();
		System.out.println("订单号为："+ORDER_NO);
		System.out.println("测试结果为："+TEST_RESULT);
		String insertSql= "insert into SELENIUM_OR_RESULT(TESULT_ID,TEST_NO,ORDER_NO,ORDER_STATUS,ORDER_STATUS_NAME,TEST_RESULT,TEST_RESULT_REASON) VALUES ("+TESULT_ID+",'"+TEST_NO+"','"+ORDER_NO+"','"+ORDER_STATUS+"','"+ORDER_STATUS_NAME+"','"+TEST_RESULT+"','"+TEST_RESULT_REASON+"')";
		System.out.println(insertSql);
		java.sql.Statement stmt = conn.createStatement();
		stmt.executeUpdate(insertSql);
		System.out.println("测试结果数据写入完毕！");
	}
}
