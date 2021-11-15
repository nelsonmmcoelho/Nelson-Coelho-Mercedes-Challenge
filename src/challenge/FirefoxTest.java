package challenge;

import graphql.Assert;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;

public class FirefoxTest {

    /* run with Firefox */

    public static void main(String args[]) throws Exception {

        WebDriver driver = new FirefoxDriver(); //instantiate Firefox driver
        Actions action = new Actions(driver);
        driver.get("https://www.mercedes-benz.co.uk"); //open URL
        driver.manage().window().maximize();           //maximize window
        WebDriverWait wait  = new WebDriverWait(driver, Duration.ofSeconds(5));  //instantiante wait variable
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//iframe[contains(@id,'userlike-frame')]"))); //wait for coookie popup to appear
        WebElement cookies = driver.findElement(By.xpath("//iframe[contains(@id,'userlike-frame')]")); //locate cookie popup frame
        driver.switchTo().frame(cookies); //focus on cookies popup frame

        //assertain the existence of button "Agree to all". Unfortunately, I couldn't locate this button and so I couldn't close the cookies popup.
        Assert.assertNotNull(driver.findElement(By.xpath("//button[contains(@class,'wb-button--accept-all')]")));

        /* from here now this code wasn't tested because of the cookie popup issue



         */

        driver.switchTo().defaultContent(); //focus on default content

        //find hatchback image and select it
        WebElement hatchback = driver.findElement(By.cssSelector("button.dh-io-vmos_1RKkS"));
        hatchback.findElements(By.cssSelector("button.dh-io-vmos_1RKkS")).get(0).click();

        //mouse hover A Class Model
        WebElement hoverCar= driver.findElement(By.xpath("/html/body/div[1]/div/div/div/dh-io-vmos/div//div/div/div/div/div/div/div[4]/section/div/div/div[1]/div/a/div/div[3]/div/div[4]/img"));
        action.moveToElement(hoverCar).build().perform();

        //press 'Build your car' after waiting that it appears
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[1]/div/div/div/dh-io-vmos/div//div/div/div/div/div/div/div[4]/section/div/div/div[1]/div/wb-popover/ul/li[2]/a\n")));
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div/dh-io-vmos/div//div/div/div/div/div/div/div[4]/section/div/div/div[1]/div/wb-popover/ul/li[2]/a\n")).click();

        Thread.sleep(9000);  //wait 9 seconds for the page to load up

        //Scroll down to find the checkbox
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,190)", "");

        driver.findElement(By.xpath("//label[text()=' Diesel ']")).click(); //click in 'Diesel' checkbox

        takeScreenshot(driver, "c:\\challenge_screenshot.png"); //takes a screenshot and saves it the destination

        /*missing code after taking the sreenshot
          compare the values of the cars filtered and arrange them by order
          Take the highest and lowest '£' values and save them to a file


         */

        driver.quit();

    }

    public static void takeScreenshot(WebDriver thedriver, String pathFile) throws Exception{
        TakesScreenshot scrShot =((TakesScreenshot)thedriver); //convert web driver object to take the screenshot
        File SrcFile=scrShot.getScreenshotAs(OutputType.FILE); //call getScreenshotAs method to create image file
        File DestFile=new File(pathFile); //Move image file to new location
        //Copy file at destination
        FileUtils.copyFile(SrcFile, DestFile);

    }

}
