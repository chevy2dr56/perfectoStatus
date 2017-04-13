package com.quantum.accessibilityObjs;

import com.quantum.test.projectUtils;
import com.quantum.utils.DeviceUtils;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.LinkedList;

/**
 * Created by uzie on 2/6/17.
 */
public class AccFullScreenData {

    LinkedList<AccPageData> pages = new LinkedList() ;
    int numberOdScrool ;//TODO: replace scroll untill no chnage in the report
    String workDir = "/Users/uzie/Documents/accessability";

    public AccFullScreenData(String xml, String pageName) {
        //get Driver and manage the
        // screenShot get Report + scroll
        for (int numberScrools = 0; numberScrools <= 3; numberScrools++) {
            String ScreenShotName = pageName + "_" + numberScrools;
            projectUtils.getSreenShot(workDir, ScreenShotName);
            String pageRep = projectUtils.getAccessibilityRep();
            AccPageData page = new AccPageData(pageRep,workDir+"/"+ScreenShotName);
            pages.add(page);
            DeviceUtils.swipe("30%,95%", "30%,5%");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //scroll all the way to top of the screen
        for (int numberScrools = 0; numberScrools <= 3; numberScrools++)
        {
            DeviceUtils.swipe("30%,5%", "30%,95%");

        }



        //PRINT THE Pages
        for (AccPageData p :pages)
        {
            new PrintPage(p.getLines(),p.getImage());
        }


        System.out.println("*****"+ pages.size());

    }
}
