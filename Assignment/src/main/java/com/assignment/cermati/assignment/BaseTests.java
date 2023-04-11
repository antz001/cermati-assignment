package com.assignment.cermati.assignment;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTests 
{
	WebDriver driver = new ChromeDriver();
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	
	//Application URL
	String appUrl = "http://www.ebay.com";
		
	@BeforeMethod
	public void Init()
	{
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get(appUrl);
	}
	
	@AfterMethod
	public void CleanUp()
	{
		driver.quit();
	}
}