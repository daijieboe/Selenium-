package daijie.basic;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetDataTrue {
	/*
	 * 方法名：getData 获取订单测试用例数据
	 * 参数in：TEST_NO 测试用例编码（查询表条件）
	 * 参数out：ResultSet
	 * 其他说明：由于固定使用sit环境的SELENIUM_OR_DATA表，故数据表名暂不做参数化处理
	 * @Author DaiJie
	 * @Date 2016-1-28
	 * */
	public List<Map<String, Object>> getData(Connection conn,String testNo) throws Exception{
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object>  m = null;
		ResultSet testData = null;
		String tableName = "SELENIUM_OR_DATA";
		String querySql= null;
		
		//利用表名和测试数据序号testNo进行查询
		querySql = "select * from " + tableName +" where TEST_NO = '" + testNo + "'";
		java.sql.Statement stmt = conn.createStatement();
		testData = stmt.executeQuery(querySql);
		ResultSetMetaData rsData = testData.getMetaData();
		while (testData.next()) {
			m = new HashMap<String, Object>();
			for (int i = 0; i < rsData.getColumnCount(); i++) {
				m.put(rsData.getColumnName(i), testData.getObject(i));
			}
			list.add(m);
			//TEST_URL = testData.getString("TEST_URL");
		}
		//提前完成“.next()”
		//testData.next();
		testData.close();
		stmt.close();
		conn.close();
		return list;
	}

}
