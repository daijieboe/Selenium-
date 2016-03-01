package test;

import com.thoughtworks.selenium.*;

public class OrderCancelBackground extends SeleneseTestCase{
	
	public void orderCancelBackground(Selenium selenium, String ORDER_NO, String orderOrigin){
		
		System.out.println("*************判断平台环境，开始取消订单************！");
		if ("sit".equals(orderOrigin)){
		//	selenium.open("http://sitcas.boe.com:8090/cas/login?service=http%3A%2F%2Fsitscadm.boe.com%2Fsc-support-admin%2Fj_spring_cas_security_check");
		    
			System.out.println("打开SIT中台订单查询处理页！");
			selenium.click("css=a.selected");
			try {
			Thread.sleep(1000);
		    } catch (InterruptedException e) {
			e.printStackTrace();
		    }
		}else if ("pre".equals(orderOrigin)){
			
			selenium.setSpeed("2000");
		//	selenium.click("css=a.selected");
			selenium.open("http://precas.boe.com:8090/cas/login?service=http%3A%2F%2Fprescadm.boe.com%2Fsc-support-admin%2Fj_spring_cas_security_check");
			//输入用户名和密码
			
			selenium.type("id=username", "00003176");
			selenium.type("id=password", "kinglion823");
			//点击登录按钮
			selenium.click("name=submit");
			
			System.out.println("打开PRE中台订单查询处理页！");
			try {
			Thread.sleep(1000);
		    } catch (InterruptedException e) {
			e.printStackTrace();
		    }
		}
		
		// 打开订单管理一级菜单
		System.out.println("点击订单管理菜单！");
		selenium.click("css=#102 > a > p");
		System.out.println("点击订单管理菜单完毕！");
		// 等待页面加载
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// 打开订单状态回传模拟界面
		System.out.println("点击订单查询处理菜单START！");
		selenium.click("link=订单查询处理");
		selenium.click("link=订单查询处理");
		selenium.click("link=订单查询处理");
		System.out.println("点击订单查询处理菜单OVER！");
		// 等待页面加载
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//输入订单号，查询
		selenium.type("id=orderNum", ORDER_NO);
		selenium.click("id=searchButton");
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//点击取消按钮
		selenium.click("link=取消");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		selenium.select("id=cancelReasonDialog", "label=信息填写错误，重新购买");
		selenium.click("xpath=//button[@type='button']");
		System.out.println("****************订单取消成功***************");
	}
}
