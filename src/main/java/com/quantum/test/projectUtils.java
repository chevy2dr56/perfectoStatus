package com.quantum.test;


import com.quantum.utils.DeviceUtils;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import static com.qmetry.qaf.automation.core.ConfigurationManager.getBundle;
import static com.quantum.utils.DeviceUtils.getQAFDriver;

/**
 * Created by uzie on 2/2/17.
 */
public class projectUtils {


    public static String getAccessibilityRep() {
        Map<String, Object> params = new HashMap<String, Object>();
//
         String resultStr = (String) getQAFDriver().executeScript("mobile:accessibility:report", params);

         return resultStr;


    } public static String getAccessibilityRepWithFile( String folder,String name) {
        Map<String, Object> params = new HashMap<String, Object>();

        String resultStr = (String) getQAFDriver().executeScript("mobile:accessibility:report", params);
        try {
            PrintWriter out = new PrintWriter( folder + "/" + name );
            out.println( resultStr );
            out.flush();
            out.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        return resultStr;


    }


    public static void resetDevice(RemoteWebDriver d) {
        Map<String, Object> params = new HashMap<String, Object>();

        d.executeScript("mobile:BLE:restart", params);



    }


    public static void getSreenShot(String folder,String name)
    {
        getSreenShot(folder,name,0);
    }
    public static void getSreenShot(String folder,String name,int loop)
    {

            RemoteWebDriver d = getQAFDriver();
            File scrFile = ((TakesScreenshot) d).getScreenshotAs(OutputType.FILE);
            try {
                // Copy paste file at destination folder location
                FileUtils.copyFile(scrFile, new File(folder + "/" + name+".jpg"));
            } catch (IOException e) {
                e.printStackTrace();
            }


             try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


    }



    // am instrument -e coverage true -e coverageFile /sdcard/Pictures/coverage.ec com.mdx.test.containment/.CoverageInstrumentation
    //am broadcast -a com.mdx.test.containment.END_EMMA

    public void executeShell(RemoteWebDriver d , String cmd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("command", cmd);
         String resultStr = (String) d.executeScript("mobile:handset:shell", params);
        System.out.println(resultStr);
    }




    public static String FileToString(String fileLocation)
    {
        String data = null;
        try {
            data = new String(Files.readAllBytes(Paths.get(fileLocation)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
    public static void saveFile(String fileLocation,String text)
    {
        try {
            PrintWriter out = new PrintWriter(fileLocation);
            out.print(text);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
     }

}
