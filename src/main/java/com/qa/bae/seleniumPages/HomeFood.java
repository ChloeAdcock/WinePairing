package com.qa.bae.seleniumPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomeFood {

	@FindBy(xpath="/html/body/div/div[2]/form/div[1]/input")
	private WebElement foodName;
	
	@FindBy(xpath="/html/body/div/div[2]/form/div[2]/input")
	private WebElement allergens;
	
	@FindBy(xpath="/html/body/div/div[2]/form/div[3]/input")
	private WebElement foodDescription;
	
	@FindBy(xpath="/html/body/div/div[2]/form/div[4]/button")
	private WebElement addFood;
	
	@FindBy(xpath="/html/body/nav/div/div/ul/li[2]/a")
	private WebElement gotToFood;
	
	public void inputFoodName(String input) {
		this.foodName.sendKeys(input);
	}
	
	public void inputAllergens(String input) {
		this.allergens.sendKeys(input);
	}
	
	public void inputFoodDescription(String input) {
		this.foodDescription.sendKeys(input);
	}
	
	public void clickAdd() {
		this.addFood.click();
	}
	
	public void goToFoodPage() {
		this.gotToFood.click();
	}
}
