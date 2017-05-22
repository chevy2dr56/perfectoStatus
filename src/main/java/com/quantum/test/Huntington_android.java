package com.quantum.test;

import com.qmetry.qaf.automation.ui.WebDriverTestCase;
import com.quantum.utils.DeviceUtils;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by uzie on 1/23/17.
 */
public class Huntington_android extends WebDriverTestCase {


   @Test(description="Huntington", groups={"@Huntington_ci2"})
   public void HuntingtonTestv2() {

       DeviceUtils.switchToContext("NATIVE_APP");
       getDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
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
           DeviceUtils.installApp("PRIVATE:huntington/huntington_and.apk",false);
           DeviceUtils.startApp("Huntington", "name");
           getDriver().findElement(By.xpath("//*[@content-desc=\"Accounts1 of 5\"]")).click();

           //press accept

       }

        DeviceUtils.startApp("Huntington", "name");

        //   getDriver().findElement(By.xpath("//*[@value=\"Username\"]")).sendKeys("qajan2017");
        getDriver().findElement(By.xpath("(//*[contains(@content-desc,'Edit Box UsernameDouble tap to edit Username')])[1]")).sendKeys("qajan2017");

        getDriver().findElement(By.xpath("//*[@content-desc=\"Password, secure text field Double tap to edit\"]")).sendKeys("12341234a"); 
        getDriver().findElement(By.xpath("//*[@text=\"Login\"]")).click();
        getDriver().findElement(By.xpath("//*[@text=\"Logout\"]")).click();
        getDriver().findElement(By.xpath("//*[@resource-id=\"android:id/button1\"]")).click();

        getDriver().findElement(By.xpath("(//*[contains(@text,\"Quick Balance\")])[1]")).click();

        DeviceUtils.closeApp("Huntington", "name");

     }//test
}
