package test;

import java.sql.ResultSet;


import com.thoughtworks.selenium.SeleneseTestCase;
import com.thoughtworks.selenium.Selenium;

public class IsCancelOrder extends SeleneseTestCase{
    public void isCancelOrder(Selenium selenium,ResultSet sourceData,String ORDER_NO) throws Exception{
    	int ISCANCEL = sourceData.getInt("ISCANCEL");
    	String TEST_URL =null;
		TEST_URL = sourceData.getString("TEST_URL");
		String orderOrigin = null;
		orderOrigin = TEST_URL.substring(7,10);
    	switch (ISCANCEL){
    	case 0:{
    		System.out.println("************不需要测试************");
    		break;
    	}
    	case 1:{
    		System.out.println("************，只有1个订单号，前台取消************");
    		
    		break;
    	}
    	case 2:{
    		System.out.println("************，只有1个订单号，中台取消************");
    		System.out.println("************开始中台取消操作************");
    		System.out.println("取消订单号："+ORDER_NO);
    		OrderCancelBackground y = new OrderCancelBackground();
    		y.orderCancelBackground(selenium, ORDER_NO,orderOrigin);
       		break;
    	}
    	}
    }
}
