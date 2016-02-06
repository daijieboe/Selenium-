package daijie.basic;

import java.sql.Connection;
import java.sql.ResultSet;

public class GetData {
	/*
	 * 方法名：getData 获取订单测试用例数据
	 * 参数in：TEST_NO 测试用例编码（查询表条件）
	 * 参数out：ResultSet
	 * 其他说明：由于固定使用sit环境的SELENIUM_OR_DATA表，故数据表名暂不做参数化处理
	 * @Author DaiJie
	 * @Date 2016-1-28
	 * */
	public ResultSet getData(Connection conn,String testNo) throws Exception{
		ResultSet testData = null;
		String tableName = "SELENIUM_OR_DATA";
		String querySql= null;
		
		//利用表名和测试数据序号testNo进行查询
		querySql = "select * from " + tableName +" where TEST_NO = '" + testNo + "'";
		java.sql.Statement stmt = conn.createStatement();
		testData = stmt.executeQuery(querySql);
	
		//提前完成“.next()”
		testData.next();

		return testData;
	}

}
