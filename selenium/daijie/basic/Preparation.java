package daijie.basic;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;

public class Preparation{
	/*
	 * 本方法为测试脚本执行前的准备工作，包括浏览器路径设定，测试url设定等
	 * 输入参数：
	 *          Selenium（selenium测试必备变量）
	 *          String explorerRoute（浏览器路径）
	 *          String testUrl（测试url）
	 * 输出参数：
	 *          Selenium（将Selenium返回）
	 * @Author DaiJie
	 * @Date 2016-1-28
	 * */
	public Selenium setUp(Selenium selenium,String explorerRoute, String testUrl) throws Exception{
		//设定浏览器路径、测试url
		selenium = new DefaultSelenium("localhost", 4444, explorerRoute, testUrl);
		
		//启动
		selenium.start();
		
		//将selenium返回
		return selenium;
	}
}
