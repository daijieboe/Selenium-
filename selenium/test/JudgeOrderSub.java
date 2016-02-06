package test;

import java.sql.ResultSet;
import com.thoughtworks.selenium.SeleneseTestCase;
import com.thoughtworks.selenium.Selenium;

public  class JudgeOrderSub extends SeleneseTestCase {
	public JudgeOrderSub(Selenium s){
		selenium = s;
	}

	public void  judgeordersub(Selenium selenium,ResultSet sourceData) throws Exception{
        String TEST_NO = sourceData.getString("TEST_NO");
		String TEST_URL =null;
		TEST_URL = sourceData.getString("TEST_URL").substring(7,10);

		int	TESULT_ID = 0;
		
		//获取本次测试结果的ID
		GetMaxResultId getMaxResultId = new GetMaxResultId();
		TESULT_ID = getMaxResultId.getMaxResultId(TEST_URL);
		
		String URL = selenium.getLocation();
		
		String t = "orderSuccess";
		if (URL.contains(t)){
		System.out.println("订单提交成功，进入订单成功页！");
		}else{
		System.out.println("订单提交失败，返回测试结果并退出测试！");
		String TEST_RESULT_REASON = "订单提交失败，返回测试结果并退出测试！";
		
		GetMaxResultId getMaxResultId1 = new GetMaxResultId();
		TESULT_ID = getMaxResultId1.getMaxResultId(TEST_URL);
		
  		WriteOrderResult q = new WriteOrderResult();
  		q.writeresult2(TEST_URL,TESULT_ID, TEST_NO,TEST_RESULT_REASON);
		System.exit(0);
		}
		
	}
}
