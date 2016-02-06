package test;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;

public class Preparation{

	// 输入：
	// 输出：
	public Selenium setUp(Selenium selenium,String explorerRoute, String testUrl) throws Exception

	{
		selenium = new DefaultSelenium("localhost", 4444, explorerRoute, testUrl);
		selenium.start();
		return selenium;
	}
}
