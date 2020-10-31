package cloudTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import utilities.Constant;

public class SauceTest extends SauceLabBaseTest {

	public void doLogin() {
		driver.get(Constant.URL);
		driver.findElement(By.id("user-name")).sendKeys(Constant.APP_USERNAME);
		driver.findElement(By.id("password")).sendKeys(Constant.APP_PASSWORD);
		driver.findElement(By.id("login-button")).click();
	}

	@Test(priority = 1)
	public void TitleTest() {
		driver.get(Constant.URL);
		String title = driver.getTitle();
		System.out.println("The titile is " + title);
		Assert.assertEquals(title, Constant.APP_TITLE);
	}
	
	@Test(priority = 2)
	public void checkInventoryItemTest() {
		doLogin();
		Assert.assertTrue(driver.findElements(By.cssSelector(".inventory_item")).size() == 6);
	}

	@Test(priority = 3)
	public void checkAddToCartButtonTest() {
		doLogin();
		Assert.assertTrue(driver.findElements(By.xpath("//button[text()='ADD TO CART']")).size() == 6);
	}
	
	@Test(priority = 4)
	public void LogoutTest() {
		doLogin();
		driver.findElement(By.xpath("//button[normalize-space()='Open Menu']")).click();
		driver.findElement(By.xpath("//a[normalize-space()='Logout']")).click();
		WebElement Logo = driver.findElement(By.xpath("//div[@class='login_logo']"));
		Assert.assertTrue(Logo.isDisplayed());
		
		
	}

}
