package com.qa.bae.selenium;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.qa.bae.seleniumPages.HomeWine;
import com.qa.bae.seleniumPages.WinePage;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class AddWine {
	
	private WebDriver driver;
	
	
	private final String NAME = "Wine name";
	private final String GRAPE = "Grape";
	private final String DESCRIPTION = "Wine description";
	private final String TASTINGNOTES = "Tasting notes";
	
	public void waitLike() {
		
		new WebDriverWait(this.driver, 10L)
		.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div/button[2]")));
		
	}
	
	public void waitDelete() {
		
		new WebDriverWait(this.driver, 10L)
		.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div/button[1]")));
		
	}
	
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
		
		this.driver.get("http://3.11.106.117:8181/WinePairing/index.html");
		
		HomeWine homePage = PageFactory.initElements(driver, HomeWine.class);
		homePage.inputWineName(NAME);		
		homePage.inputWineGrape(GRAPE);
		homePage.inputWineDescription(DESCRIPTION);
		homePage.inputTastingNotes(TASTINGNOTES);
		homePage.clickAdd();
		Thread.sleep(1000);
		homePage.goToWinePage();
		
		WinePage winePage = PageFactory.initElements(driver, WinePage.class);
		waitLike();
		winePage.clickLike();
		waitDelete();
		winePage.clickDelete();
	}
	
	@After
	public void tearDown() {
		this.driver.close();
	}

}
