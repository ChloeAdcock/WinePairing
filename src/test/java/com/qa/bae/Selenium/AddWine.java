package com.qa.bae.Selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import com.qa.bae.Pages.HomeWine;

public class AddWine {
	
	private WebDriver driver;
	
	@Before
	public void setup() {
		System.setProperty(Constants.PROPERTY, Constants.PATH);
		driver = new ChromeDriver();
	}
	
	@Test
	public void test() throws InterruptedException {
		this.driver.get("http://3.11.106.117:8181/WinePairing/index.html");
		HomeWine homePage = PageFactory.initElements(driver, HomeWine.class);
		
		
	}
	
	@After
	public void tearDown() {
		this.driver.close();
	}

}
