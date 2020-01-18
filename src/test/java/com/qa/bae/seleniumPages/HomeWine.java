package com.qa.bae.seleniumPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomeWine {
	
	@FindBy(xpath="/html/body/div/div[1]/form/div[1]/input")
	private WebElement wineName;
	
	@FindBy(xpath="/html/body/div/div[1]/form/div[2]/input")
	private WebElement wineGrape;
	
	@FindBy(xpath="/html/body/div/div[1]/form/div[3]/input")
	private WebElement wineDescription;
	
	@FindBy(xpath="/html/body/div/div[1]/form/div[4]/input")
	private WebElement wineTastingNotes;
	
	@FindBy(xpath="/html/body/div/div[1]/form/button")
	private WebElement addWine;
	
	@FindBy(xpath="/html/body/nav/div/div/ul/li[3]/a")
	private WebElement gotToWine;

	
	public void inputWineName(String input) {
		this.wineName.sendKeys(input);
	}
	
	public void inputWineGrape(String input) {
		this.wineGrape.sendKeys(input);
	}
	
	public void inputWineDescription(String input) {
		this.wineDescription.sendKeys(input);
	}
	
	public void inputTastingNotes(String input) {
		this.wineTastingNotes.sendKeys(input);
	}
	
	public void clickAdd() {
		this.addWine.click();
	}
	
	public void goToWinePage() {
		this.gotToWine.click();
	}
}
