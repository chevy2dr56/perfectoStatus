package com.quantum.test;

import com.qmetry.qaf.automation.ui.WebDriverTestCase;
import com.quantum.accessibilityObjs.AccFullScreenData;
import com.quantum.utils.DeviceUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by uzie on 1/23/17.
 */
public class Huntington extends WebDriverTestCase {


    @Test(description="Huntington", groups={"@Huntington_ci2"})
    public void HuntingtonTestv2() {

        DeviceUtils.switchToContext("NATIVE_APP");

        try {
            DeviceUtils.closeApp("Huntington", "name");

        }catch (Exception e)
        {
        //nothing

        }
        try {
            DeviceUtils.startApp("Huntington", "name");
        }catch (Exception e){
                   //install the app and reopen
            DeviceUtils.installApp("PRIVATE:huntington/huntington.ipa",false);
            getDriver().findElement(By.xpath("//*[@label=\"Accounts\"]")).click();

        }
        DeviceUtils.startApp("Huntington", "name");
        getDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        //   getDriver().findElement(By.xpath("//*[@value=\"Username\"]")).sendKeys("qajan2017");

        // did not work on iphone 9 added the visible='true'
       // getDriver().findElement(By.xpath("(//UIATextField[@value=\"Username\"])[2]")).sendKeys("qajan2017");

        //works
        try {
            getDriver().findElement(By.xpath("(//UIATextField[@value=\"Username\" and @visible='true'])")).sendKeys("qajan2017");//skipXpath

        }catch (NoSuchElementException e)
        {
            //if not found predss the accept and try again
            getDriver().findElement(By.xpath("//*[@label=\"Accounts\"]")).click();
            getDriver().findElement(By.xpath("(//UIATextField[@value=\"Username\" and @visible='true'])")).sendKeys("qajan2017");

        }

        getDriver().findElement(By.xpath("//UIASecureTextField")).sendKeys("12341234a"); //skipXpath
        getDriver().findElement(By.xpath("//*[@label=\"Login\"]")).click();
        getDriver().findElement(By.xpath("//*[@label=\"Logout\"]")).click();

        //if can't find yes - missed the logout  so press again
        try {
            getDriver().findElement(By.xpath("//*[@label=\"Yes\"]")).click();

        }catch (NoSuchElementException e)        {
            //if not found predss the accept and try again
            getDriver().findElement(By.xpath("//*[@label=\"Logout\"]")).click();
            getDriver().findElement(By.xpath("//*[@label=\"Yes\"]")).click();

        }

        getDriver().findElement(By.xpath("(//*[contains(@label,\"Quick Balance\")])[1]")).click();

        DeviceUtils.closeApp("Huntington", "name");

     }
}
