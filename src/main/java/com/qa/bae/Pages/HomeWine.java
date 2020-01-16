package com.qa.bae.Pages;

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
	
	public void inputWineName(String input) {
		this.wineName.sendKeys(input);
	}
	
	public void inputWineGrape(String input) {
		this.wineGrape.sendKeys(input);
	}
	
	public void searchItem(String item) {
		this.search.sendKeys(item);
	}
	
	public void searchItem(String item) {
		this.search.sendKeys(item);
	}

}
