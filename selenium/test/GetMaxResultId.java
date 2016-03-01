package test;
import java.sql.Connection;
import java.sql.ResultSet;

import daijie.basic.GetConnection;

//获取测试结果表中最后一条ID号
public class GetMaxResultId {
	
	public int getMaxResultId(String TEST_URL) throws Exception{
		
//		int i = 1;
		String sq = null;
		int TESULT_ID = 0;
		Connection conn = new GetConnection().getConnection("sit");
	//	sq = "SELECT * FROM SELENIUM_OR_RESULT ORDER BY TESULT_ID DESC";//取最后一列，待优化
		sq = "SELECT MAX(TESULT_ID) FROM SELENIUM_OR_RESULT";
		java.sql.Statement stmt = conn.createStatement();
		ResultSet testData = stmt.executeQuery(sq);
		testData.next();

		TESULT_ID = testData.getInt("MAX(TESULT_ID)");
		TESULT_ID = TESULT_ID + 1;
		System.out.println("TESULT_ID:"+TESULT_ID);
		return TESULT_ID;
	}
}
