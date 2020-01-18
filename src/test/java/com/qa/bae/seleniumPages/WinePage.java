package com.qa.bae.seleniumPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WinePage {
	
	@FindBy(xpath="/html/body/div/div[3]/button[2]")
	private WebElement likeWine;
	
	@FindBy(xpath="/html/body/div/div[3]/button[1]")
	private WebElement deleteWine;
	
	public void clickLike() {
		this.likeWine.click();
	}
	
	public void clickDelete() {
		this.deleteWine.click();
	}
}
