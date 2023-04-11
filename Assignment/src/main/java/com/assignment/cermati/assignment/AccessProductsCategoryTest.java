package com.assignment.cermati.assignment;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import org.testng.*;

public class AccessProductsCategoryTest extends BaseTests 
{
	//Data
	String searchCategory = "Cell phones & accessories";
	String shopCategory = "Cell Phones & Smartphones";
	String fromPrice = "0.00";
	String toPrice = "600.00";
	
	@Test
	/* Go to www.ebay.com
	 * Navigate to Search by category > Electronics > Cell Phones & accessories
	 * After the page loads, click Cell Phones & Smart phones in the left hand side navigation section.
	 * Now, click – See All (appears under “Shop by brand” or “Shop by Network”).
	 * Add 3 filters - screen size, Price and Item location appearing in the pop-up and click apply.
	 * Verify that the filter tags are applied.*/
	public void AccessProductViaCategory() throws InterruptedException
	{
		JavascriptExecutor jS = (JavascriptExecutor)driver;
		
		driver.findElement(By.id("gh-shop-a")).click();
		driver.findElement(By.linkText(searchCategory)).click();
		driver.findElement(By.linkText(shopCategory)).click();
		
		driver.findElement(By.cssSelector("#mainContent > div:nth-child(1) > section.b-module > div.b-carousel__header.sameline > div.b-carousel__seeall")).click();
		String lblScreenSize = selectFilterAndGetLabel(By.xpath("//*[text()='Screen Size']"), By.cssSelector(".x-overlay-sub-panel__col > div > span > label > span"));
		driver.findElement(By.cssSelector(".x-overlay-sub-panel__col > div > span")).click();
		
		jS.executeScript("document.querySelector('.x-overlay__wrapper--left').scrollBy(0, 600)");
		
		//Apply filters
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#c3-mainPanel-price > span")));
		driver.findElement(By.cssSelector("#c3-mainPanel-price > span")).click();
		driver.findElement(By.cssSelector(".x-textrange__input--from")).sendKeys(fromPrice);
		driver.findElement(By.cssSelector(".x-textrange__input--to")).sendKeys(toPrice);
		
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#c3-mainPanel-location > span")));
		driver.findElement(By.cssSelector("#c3-mainPanel-location > span")).click();
		String lblLocation = selectFilterAndGetLabel(By.xpath("//*[@id='c3-subPanel-location_Worldwide']/span/span/input"), By.xpath("//*[@id='c3-subPanel-location_Worldwide']/span/label/span"));
		
		//Click on Apply button
		driver.findElement(By.cssSelector(".btn.btn--primary")).click();
		
		//Assertions
		driver.findElement(By.cssSelector(".brm__item--applied")).click();		
		AssertOutcome(By.cssSelector(".x-flyout--open > div > ul > li:nth-child(1)"), lblScreenSize);
		AssertOutcome(By.cssSelector(".x-flyout--open > div > ul > li:nth-child(2)"), "$"+fromPrice + " to " + "$"+toPrice);
		AssertOutcome(By.cssSelector(".x-flyout--open > div > ul > li:nth-child(3)"), lblLocation);
	}
	
	//Private Methods
	private String selectFilterAndGetLabel(By byElement, By byLabel)
	{
		driver.findElement(byElement).click();
		String lblText = driver.findElement(byLabel).getText();
		return lblText;
	}
	
	private void AssertOutcome(By byElement, String expValue)
	{
		Assert.assertTrue(driver.findElement(byElement).getText().contains(expValue));	
	}
}