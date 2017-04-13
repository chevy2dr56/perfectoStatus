package com.quantum.test;

import com.qmetry.qaf.automation.step.CommonStep;
import com.qmetry.qaf.automation.ui.WebDriverTestCase;
import com.quantum.utils.DeviceUtils;
import org.testng.annotations.Test;

import static com.qmetry.qaf.automation.step.CommonStep.*;

/**
 * Created by uzie on 1/23/17.
 */
public class ciTest extends WebDriverTestCase {


    @Test(description="CI", groups={"@ci"})
    public void CItestfull() {


        getDriver().get("http://nxc.co.il/stab.php");
        getDriver().findElement("restet").waitForPresent(30000);
        getDriver().findElement("restet").click();
        for (int i = 0; i < 2; i++)
        {
            String valtowrite = "CU_"+i;
            getDriver().findElement("string_input").clear();
            getDriver().findElement("string_input").sendKeys(valtowrite);

            getDriver().findElement("update_bt").click();

            String val2 = getDriver().findElement("output_v").getAttribute("value");
            assertTrue(val2.equals(valtowrite),"update filed value is invalid","update OK");

            String i_str= String.valueOf(i);
            assertAttribute("indexVal","value",i_str);

            getDriver().findElement("incise").click();

        }


     }

    @Test(description="CI", groups={"@noci","@JIRA:TEST-2"})
    public void testJira() {


        getDriver().get("http://nxc.co.il/stab.php");
        getDriver().findElement("restet").waitForPresent(30000);
        getDriver().findElement("restet").click();
        for (int i = 0; i < 2; i++)
        {
            String valtowrite = "CU_"+i;
            getDriver().findElement("string_input").clear();
            getDriver().findElement("string_input").sendKeys(valtowrite);

            getDriver().findElement("update_bt").click();

            String val2 = getDriver().findElement("output_v").getAttribute("value");
            assertTrue(val2.equals(valtowrite),"update filed value is invalid","update OK");

            String i_str= String.valueOf(i);
            assertAttribute("indexVal","value",i_str);

            getDriver().findElement("incise").click();

        }


    }
}
