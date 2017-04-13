package com.quantum.test;

import com.qmetry.qaf.automation.ui.WebDriverTestCase;
import com.quantum.utils.DeviceUtils;
import com.quantum.utils.ConsoleUtils;

 import org.openqa.selenium.By;
import org.testng.annotations.Test;
import com.quantum.generic.moreActions;

import java.util.concurrent.TimeUnit;

import static com.qmetry.qaf.automation.step.CommonStep.assertAttribute;
import static com.qmetry.qaf.automation.step.CommonStep.get;

/**
 * Created by uzie on 1/23/17.
 */
public class anz extends WebDriverTestCase {


    @Test(description="CI", groups={"@anzci","@interOp"})
    public void anz_tarnsfer() {


        getDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);


        try
        {

            DeviceUtils.closeApp("com.anz.android.gomoney","identifier");

        }catch (Exception ex)
        {
            //already close
        }

        DeviceUtils.startApp("com.anz.android.gomoney","identifier");
        DeviceUtils.switchToContext("NATIVE_APP");

        getDriver().findElement(By.xpath("//*[@resource-id='com.anz.android.gomoney:id/authentication_title_text']"));
        pressPIN();
        getDriver().findElement(By.xpath("//*[@content-desc=\"Open menu\"]")).click();
          getDriver().findElement(By.xpath("//*[@text=\"Transfer\"]")).click();
          getDriver().findElement(By.xpath("//*[@resource-id=\"com.anz.android.gomoney:id/tranfer_amount\"]")).click();


         m_01();
         c1();


       //  moreActions.callMe("02157DF2A1B46C22");
        String deviceName =  ConsoleUtils.getDeviceName(getDriver().getCapabilities());
        System.out.println("************"+deviceName);

        moreActions.callMe(deviceName);


          //wait for the call
       //  getDriver().findElement(By.xpath("//*[@resource-id=\"com.android.incallui:id/popup_call_reject\"]"));
        sleep(10000);

        c2();

        sleep(5000);

        DeviceUtils.switchToContext("VISUAL");
        getDriver().findElement(By.linkText("reject")).click();
        DeviceUtils.switchToContext("NATIVE_APP");





        // validate
        getDriver().findElement(By.xpath(" //*[@text=\"Transfer Successful\"]"));

    }

    @Test(description="CI", groups={"@anzci","@commit"})
    public void anz_tarnsfer_no_call() {


        DeviceUtils.switchToContext("NATIVE_APP");
        getDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);



        getDriver().findElement(By.xpath("//*[@resource-id='com.anz.android.gomoney:id/authentication_title_text']"));
            pressPIN();



        getDriver().findElement(By.xpath("//*[@content-desc=\"Open menu\"]")).click();
        getDriver().findElement(By.xpath("//*[@text=\"Transfer\"]")).click();
        getDriver().findElement(By.xpath("//*[@resource-id=\"com.anz.android.gomoney:id/tranfer_amount\"]")).click();


        m_01();

        getDriver().findElement(By.xpath("//*[@resource-id=\"com.anz.android.gomoney:id/tranfer_amount_tranfer_btn\"]")).click();
        // CONFIRM
         getDriver().findElement(By.xpath("//*[@resource-id=\"android:id/button1\"]")).click();



        // validate
        getDriver().findElement(By.xpath(" //*[@text=\"Transfer Successful\"]"));

    }

     private void pressPIN()

     {
         DeviceUtils.touch("50%,60%");
         DeviceUtils.touch("70%,50%");
         DeviceUtils.touch("70%,50%");
         DeviceUtils.touch("50%,80%");
     }



    private void m_01()

    {
        DeviceUtils.touch("10%,95%");
        DeviceUtils.touch("20%,70%");
        sleep(1000);

    }


    private void c1()

    {
        DeviceUtils.touch("70%,50%");

    }
    private void c2()

    {

        DeviceUtils.touch("70%,60%");
    }
    private void confirm_transfer()

    {
        DeviceUtils.touch("70%,50%");
         sleep(2000);
        DeviceUtils.touch("70%,60%");


        //   getDriver().findElement(By.xpath("//*[@resource-id=\"com.anz.android.gomoney:id/tranfer_amount_tranfer_btn\"]")).click();
        // CONFIRM
        // getDriver().findElement(By.xpath("//*[@resource-id=\"android:id/button1\"]")).click();


    }

    private void sleep(int ms)

    {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {

            e.printStackTrace();
        }
    }
}
