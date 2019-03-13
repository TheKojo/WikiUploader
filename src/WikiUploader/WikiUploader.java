package WikiUploader;

import java.io.File;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;


public class WikiUploader {



  public static void main(String[] args) throws InterruptedException {
	String start = "073MS.png";
	Boolean started = false;
	String username = "KojoBot0";
	String password = "word1Pass";
	String email = "tofuka@weave.email";
	File iconDir = new File("src/input/Icons/");
	File[] icons = iconDir.listFiles();
	System.setProperty("webdriver.chrome.driver","src/chromedriver.exe");
	WebDriver driver = new ChromeDriver();
	JavascriptExecutor jsExecutor = (JavascriptExecutor)driver;
	String homeURL = "https://p-spectrum.fandom.com/";
	//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	
	//Login
    driver.get("https://www.fandom.com/signin");
    driver.findElement(By.id("loginUsername")).clear();
    driver.findElement(By.id("loginUsername")).sendKeys(username);
    driver.findElement(By.id("loginPassword")).clear();
    driver.findElement(By.id("loginPassword")).sendKeys(password);
    driver.findElement(By.id("loginSubmit")).click();
    Thread.sleep(3000);
    
	for (int i = 0; i<icons.length; i++) {
		String filename = icons[i].getName();
		if (filename.equals(start) || started){
			started = true;
		    //Upload
		    driver.get("https://p-spectrum.fandom.com/wiki/File:"+filename);
		    driver.findElement(By.linkText("File History")).click();
		    Thread.sleep(500);
		    
		    WebElement newUpLink = driver.findElement(By.linkText("Upload a new version of this file"));
		    jsExecutor.executeScript("arguments[0].scrollIntoView(true);", newUpLink);
		    jsExecutor.executeScript("window.scrollBy(0,-250)", "");
		    driver.findElement(By.linkText("Upload a new version of this file")).click();
		    driver.findElement(By.id("wpUploadFile")).click();
		    driver.findElement(By.id("wpUploadFile")).clear();
		    driver.findElement(By.id("wpUploadFile")).sendKeys("C:\\Users\\asant\\Documents\\WikiUploader\\src\\Input\\Icons\\"+filename);//("C:\\fakepath\\001MS.png");
		    WebElement uploadLink = driver.findElement(By.name("wpUpload"));
		    jsExecutor.executeScript("arguments[0].scrollIntoView(true);", uploadLink);
		    jsExecutor.executeScript("window.scrollBy(0,-250)", "");
		    driver.findElement(By.name("wpUpload")).click();
		}

	}
    driver.close();
  }

}
