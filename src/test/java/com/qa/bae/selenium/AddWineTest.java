package com.qa.bae.selenium;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.bae.seleniumPages.HomeWine;

public class AddWineTest {
	
	private WebDriver driver;
	
	
	private final String NAME = "Wine name";
	private final String GRAPE = "Grape";
	private final String DESCRIPTION = "Wine description";
	private final String TASTINGNOTES = "Tasting notes";
	
	@Before
	public void setup() {
		
		System.setProperty("webdriver.chrome.driver", "chromedriver");
		ChromeOptions options = new ChromeOptions();
		options.setHeadless(true);
		this.driver = new ChromeDriver(options);
		this.driver.manage().window().setSize(new Dimension(1600,700));
		this.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
	}
	
	@Test
	public void test() throws InterruptedException {
		
		this.driver.get("http://3.10.21.253");
		
		HomeWine homePage = PageFactory.initElements(driver, HomeWine.class);
		homePage.inputWineName(NAME);		
		homePage.inputWineGrape(GRAPE);
		homePage.inputWineDescription(DESCRIPTION);
		homePage.inputTastingNotes(TASTINGNOTES);
		homePage.clickAdd();
	}
	
	@After
	public void tearDown() {
		this.driver.close();
	}

}
