package com.quantum.test;

import com.qmetry.qaf.automation.ui.WebDriverTestCase;
import com.quantum.accessibilityObjs.AccFullScreenData;
import com.quantum.accessibilityObjs.AccPageData;
import com.quantum.accessibilityObjs.PrintPage;
import com.quantum.utils.DeviceUtils;
import org.testng.annotations.Test;

/**
 * Created by uzie on 1/23/17.
 */
public class accessibilityTest extends WebDriverTestCase {


    @Test(description="accessability", groups={"@accessability"})
    public void accessibilityTest() {

        DeviceUtils.startApp("eBay", "name");
        DeviceUtils.switchToContext("NATIVE_APP");

        getDriver().findElement("ebayApp.search.field").waitForPresent(20);

        //System.out.println(projectUtils.getAccessibilityRep());
       // AccPageData a = new AccPageData(projectUtils.getAccessibilityRep("/Users/uzie/Documents/accessability","ebayAccess.xml"));
         //projectUtils.getSreenShot("/Users/uzie/Documents/accessability","ebayAccess",3);
        AccFullScreenData accScrren = new AccFullScreenData(projectUtils.getAccessibilityRep(),"EbaySearchScreen");

        //   for (AccSingleLine l :lines)
     //   {
     //       graphicUtils.drowRec("/Users/uzie/Documents/accessability/ebayAccess.jpg",l.getX(),l.getY(),l.getWidth(),l.getHeight());
     //   }
    //    getDriver().findElement("ebayApp.search.field").click();





     }
}
