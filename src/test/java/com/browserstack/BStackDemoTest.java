package com.browserstack;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class BStackDemoTest extends SeleniumTest {

    static String username = "agarwal.harshnu@gmail.com";
	static String password = "Harsh@1202";
	
    @Test(priority=1)
    public void validBSLogin() {
    	driver.get("https://www.browserstack.com/users/sign_in");
    	
//        driver.findElement(By.xpath("//*[@id=\"accept-cookie-notification\"]")).click();
//        driver.findElement(By.xpath("//*[@id=\"primary-menu\"]/li[5]/a")).click();
        driver.findElement(By.id("user_email_login")).sendKeys(username);
        driver.findElement(By.id("user_password")).sendKeys(password);
        driver.findElement(By.id("user_submit")).click();

    }
    

    
    
    @Test(priority=2)
    public void validOpenLiveDashboard() {
    	driver.get("https://live.browserstack.com/dashboard");
    	
        String expectedTitle = "Dashboard";
        String actualTitle = driver.getTitle();
        System.out.println("Title: " + actualTitle);
        assert actualTitle.equals(expectedTitle);
    }
    
    @Test(priority=3)
    public void validOpenBrowserFromLive() {
    	driver.findElement(By.xpath("//*[@id=\"platform-list-react\"]/div/div[2]/div/div[2]/div[3]/div[1]/div[1]/div/div")).click();

    }
    
    @Test(priority=4)
    public void invalidBSLogin() {
    	driver.close();
    	driver.get("https://www.browserstack.com/users/sign_in");
    	
        driver.findElement(By.xpath("//*[@id=\"accept-cookie-notification\"]")).click();
//        driver.findElement(By.xpath("//*[@id=\"primary-menu\"]/li[5]/a")).click();
        driver.findElement(By.id("user_email_login")).sendKeys(username);
        driver.findElement(By.id("user_password")).sendKeys("1234");
        driver.findElement(By.id("user_submit")).click();

    }
    
    @Test(priority=5)
    public void invalidOpenLiveDashboard() {
        driver.findElement(By.xpath(" /html/body/div[1]/header/div/div/nav/ul[1]/li[1]/button")).click();
        driver.findElement(By.xpath("/html/body/div[1]/header/div/div/nav/ul[1]/li[1]/div[2]/div/div[1]/ul[1]/li[2]/a/div[1]")).click();
        String expectedTitle = "Rashboard";
        String actualTitle = driver.getTitle();
        System.out.println("Title: " + actualTitle);
        assert actualTitle.equals(expectedTitle);
    }
    
    
    @Test(priority=6)
    public void invalidOpenBrowserFromLive() {
    	String actualTitle = driver.findElement(By.xpath("//*[@id=\"platform-list-react\"]/div/div[2]/div/div[2]/div[3]/div[1]/div[1]/div/div")).getText();
    	driver.findElement(By.xpath("//*[@id=\"platform-list-react\"]/div/div[2]/div/div[2]/div[3]/div[1]/div[1]/div/div")).click();
 
        String expectedTitle = "113";
        System.out.println("Title: " + actualTitle);
        assert actualTitle.equals(expectedTitle);
    }
}
