package com.qa.bae.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WinePage {
	
	@FindBy(xpath="/html/body/div/div[3]/button[2]")
	private WebElement likeWine;
	
	public void clickLike() {
		this.likeWine.click();
	}

}
