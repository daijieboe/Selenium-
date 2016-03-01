package test;

import com.thoughtworks.selenium.*;
//import java.lang.*;
//import java.sql.Connection;
//import java.sql.Driver;
//import java.sql.ResultSet;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.support.ui.Select;

public class repair1 extends SeleneseTestCase

{

	public void setUp() throws Exception

	{
		selenium = new DefaultSelenium("localhost", 4444, "*firefox C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe", "http://sitmobile.boe.com/mobileStore/index.html#!");
		selenium.start();

	}
	

	/*
	 * 方法名：testUntitled 测试入口
	 * 参数in：void
	 * 参数out：void
	 * */	
	public void testUntitled() throws Exception

	{	
		//打开 Mobile-sit首页
		
		
		System.out.println("Open the page Mobile-sit");
		selenium.open("http://sitmobile.boe.com/mobileStore/index.html#!repairOrder/%257B%257D");
		System.out.println("OK,Access to page REPAIR,waiting......");
		
		//选择产品类型
		selenium.click("id=select_type");
		selenium.select("id=select_type", "value=20005");
		System.out.println("Choosing the product type......");
		selenium.setSpeed("3000");
		selenium.focus("id=select_pro");
		selenium.click("id=select_pro");
		selenium.select("id=select_pro", "value=ALTA5503");
		System.out.println("Chooseing the SKU");
		selenium.type("id=roCon", "qweqweqwe");
		selenium.click("id=repairOrder_btn");
		System.out.println("Inputting the repair description : qweqweqwe ");
		
		selenium.setSpeed("3000");
		selenium.type("roName","liq"); 
		System.out.println("Your name is liq......");
		selenium.type("id=roPhone", "13555555555");
		System.out.println("Your phone is 13555555555......");
		selenium.select("id=RO_dropprovince", "label=北京");
		System.out.println("Your address is located in beijing......");
		selenium.select("id=RO_dropcity", "label=北京郊区");
		System.out.println("Your address is located in the suburb of beijing......");
		selenium.select("id=RO_droparea", "label=密云县");
		System.out.println("Your address is located in the city of miyun ......");
		selenium.type("id=roAddress", "rtydsghsgfsfg");
		System.out.println("Your address is rtydsghsgfsfg......");
		selenium.type("id=roCode","678678");
		System.out.println("Your address code is 678678 ......");
	
		selenium.click("id=repairOrder_btn1");
		System.out.println("Next step ......");
		selenium.click("id=consultation_date");
		selenium.click("link=15");
		System.out.println("Choose the date ......");
		selenium.click("id=select_time");
		selenium.select("id=select_time", "label=10:30-12:30");
		System.out.println("Choose the time ......");
		selenium.click("css=img.icon_mark");
		selenium.click("id=repairOrder_btn2");
		System.out.println("Submit ......");
		System.out.println("Service order submission completed！");
		//判断是否成功提交维修信息
		assertEquals("http://sitmobile.boe.com/mobileStore/index.html#!repairOrder/%257B%257D",selenium.getLocation());
		System.out.println("Congratulations!");
		System.out.println("The service repair order is completed!Please wait for our staff to come!!");
		
		
	}
}
