package com.qa.bae.Selenium;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.qa.bae.pages.HomeWine;
import com.qa.bae.pages.WinePage;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class AddWine {
	
	private WebDriver driver;
	
	
	private final String NAME = "Wine name";
	private final String GRAPE = "Grape";
	private final String DESCRIPTION = "Wine description";
	private final String TASTINGNOTES = "Tasting notes";
	
	@Before
	public void setup() {
		
		System.setProperty("webdriver.chrome.driver", "chromedriver");
		ChromeOptions options = new ChromeOptions();
		this.driver = new ChromeDriver(options);
		this.driver.manage().window().maximize();
		this.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
	}
	
	@Test
	public void test() throws InterruptedException {
		
		this.driver.get("http://3.11.106.117:8181/WinePairing/index.html");
		
		HomeWine homePage = PageFactory.initElements(driver, HomeWine.class);
		homePage.inputWineName(NAME);
		Thread.sleep(500);		
		homePage.inputWineGrape(GRAPE);
		Thread.sleep(500);
		homePage.inputWineDescription(DESCRIPTION);
		Thread.sleep(500);
		homePage.inputTastingNotes(TASTINGNOTES);
		Thread.sleep(500);
		homePage.clickAdd();
		Thread.sleep(500);
		homePage.goToWinePage();
		Thread.sleep(2000);
		
		WinePage winePage = PageFactory.initElements(driver, WinePage.class);
		winePage.clickLike();
		Thread.sleep(2000);
	}
	
	@After
	public void tearDown() {
		this.driver.close();
	}

}
