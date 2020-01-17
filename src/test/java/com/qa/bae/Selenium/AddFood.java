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

import com.qa.bae.seleniumPages.FoodPage;
import com.qa.bae.seleniumPages.HomeFood;
import com.qa.bae.seleniumPages.HomeWine;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class AddFood {
	
	private WebDriver driver;
	
	
	private final String FOODNAME = "Food name";
	private final String ALLERGENS = "Allergens";
	private final String FOODDESCRIPTION = "Food description";
	
	private final String WINENAME = "Wine name";
	private final String GRAPE = "Grape";
	private final String WINEDESCRIPTION = "Wine description";
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
		
		HomeWine wineHomePage = PageFactory.initElements(driver, HomeWine.class);
		wineHomePage.inputWineName(WINENAME);
		Thread.sleep(500);		
		wineHomePage.inputWineGrape(GRAPE);
		Thread.sleep(500);
		wineHomePage.inputWineDescription(WINEDESCRIPTION);
		Thread.sleep(500);
		wineHomePage.inputTastingNotes(TASTINGNOTES);
		Thread.sleep(500);
		wineHomePage.clickAdd();
		Thread.sleep(500);
		
		HomeFood homePage = PageFactory.initElements(driver, HomeFood.class);
		homePage.inputFoodName(FOODNAME);
		Thread.sleep(500);		
		homePage.inputAllergens(ALLERGENS);
		Thread.sleep(500);
		homePage.inputFoodDescription(FOODDESCRIPTION);
		Thread.sleep(500);
		homePage.clickAdd();
		Thread.sleep(500);
		homePage.goToFoodPage();
		Thread.sleep(2000);
		
		FoodPage foodPage = PageFactory.initElements(driver, FoodPage.class);
		foodPage.clickLike();
		Thread.sleep(2000);
		foodPage.clickDelete();
		Thread.sleep(2000);
	}
	
	@After
	public void tearDown() {
		this.driver.close();
	}

}
