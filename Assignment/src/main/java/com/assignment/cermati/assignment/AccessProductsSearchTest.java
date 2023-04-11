package com.assignment.cermati.assignment;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AccessProductsSearchTest extends BaseTests 
{
	//Data
	String searchData = "MacBook";
	String searchCategory = "Computers/Tablets & Networking";
	
	@Test
	/*
	 * Go to www.ebay.com
	 * Type any search string in the search bar. For example: MacBook.
	 * Change the Search category. For example: Computers/Tablets & Networking and click Search.
	 * Verify that the page loads completely.
	 * Verify that the first result name matches with the search string.
	 */
	public void AccessProductViaSearchTest()
	{
		driver.findElement(By.id("gh-ac")).sendKeys(searchData);
		Select option = new Select(driver.findElement(By.cssSelector(".gh-sb")));
		option.selectByVisibleText(searchCategory);
		driver.findElement(By.id("gh-btn")).click();
		
		//Validating if page loaded completely
		wait.until((ExpectedCondition<Boolean>) driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
		String valueText = driver.findElement(By.cssSelector("#srp-river-results > ul > li:nth-child(2) > div > div.s-item__info.clearfix > a > div > span > span")).getText();
		
		//Assertion
		Assert.assertTrue(valueText.contains(searchData)); 
	}
}