package com.qa.bae.seleniumPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FoodPage {

	@FindBy(xpath="/html/body/div/div/button[2]")
	private WebElement likeFood;
	
	@FindBy(xpath="/html/body/div/div/button[1]")
	private WebElement deleteFood;
	
	public void clickLike() {
		this.likeFood.click();
	}
	
	public void clickDelete() {
		this.deleteFood.click();
	}
}
