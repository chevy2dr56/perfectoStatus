package com.quantum.generic;

import com.qmetry.qaf.automation.ui.WebDriverTestBase;
import com.qmetry.qaf.automation.ui.webdriver.QAFExtendedWebDriver;

import java.util.HashMap;

/**
 * Created by uzie on 2/27/17.
 */
public class moreActions {

    public static void type(String t) {
        HashMap params = new HashMap();
        params.put("text", Integer.valueOf(t));

        getQAFDriver().executeScript("mobile:typetext", new Object[]{params});
    }

    public static void callMe(String n) {
        HashMap params = new HashMap();
        params.put("to.handset", String.valueOf(n));

        getQAFDriver().executeScript("mobile:gateway:call", new Object[]{params});
    }

    public static QAFExtendedWebDriver getQAFDriver() {
        return (new WebDriverTestBase()).getDriver();
    }

}
