package com.quantum.accessibilityObjs;

import com.quantum.test.graphicUtils;
import com.quantum.test.projectUtils;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Set;

import static com.quantum.test.projectUtils.FileToString;

/**
 * Created by uzie on 2/3/17.
 */
public class PrintPage {


    int screenHi = 2550;
    String repName;
    String imageName;

    String repTemplateLocation="/Users/uzie/Documents/accessability/rep.template"; //TODO add to external paramater

    HashMap<String,LinkedList<AccSingleLine>>repData = new HashMap<>();
    LinkedList<ErrorMsg>  msgs = new  LinkedList<ErrorMsg>();

    public PrintPage(LinkedList<AccSingleLine> lines,String screenName)
    {
        repName = screenName+".html";
        imageName=screenName;
        for (AccSingleLine l :lines)
        {
            LinkedList<AccSingleLine> isLine=  repData.get(l.getlocationID());
            if (isLine == null)
            {
                LinkedList<AccSingleLine> newLine= new LinkedList<AccSingleLine>();
                newLine.add(l);
                repData.put(l.getlocationID(),newLine);
            }
            else
            {
                isLine.add(l);
            }


        }

        // darwin the line and the message
            int imageIndex = 1;
            boolean addIndex = false;
            Set<String> keys = repData.keySet();
            for (String key : keys) {
                LinkedList<AccSingleLine> linesInSameLocation = repData.get(key);
                System.out.println("Value of " + key + " is: " + linesInSameLocation.size());
                for (AccSingleLine line : linesInSameLocation) {
                    if (key.equals("0000")) {
                        if (!line.used)
                        {
                            msgs.add(new ErrorMsg(-1, line.getStatus(), line.getMsg()));
                            line.markAsUsed();
                        }

                    } else if (line.getY()+line.getHeight() < screenHi) {
                        if (!line.used) {

                            graphicUtils.drowRec(screenName + ".jpg", line.getX(), line.getY(), line.getWidth(), line.getHeight(),String.valueOf(imageIndex));
                            msgs.add(new ErrorMsg(imageIndex, line.getStatus(), line.getMsg()));
                            addIndex=true;
                            line.markAsUsed();
                        }

                    } else {
                        //scroll and reduce the screen size
                        line.setY(line.getY() - screenHi);

                    }

                }
                if (addIndex )
                {
                    imageIndex++;
                    addIndex=false;
                }


        }

        //loop over images and create html report
        //read template
        String rep = projectUtils.FileToString(repTemplateLocation);

        String GlobalError = "<table border=\"0\">";
        String errHTMLTab = "<table border=\"0\">";

        for (ErrorMsg msg:msgs)
        {
            if (msg.number>0) {
                errHTMLTab = errHTMLTab + "<tr class=\"" + getErrClass(msg.stat) + "\">";
                errHTMLTab = errHTMLTab + "<td>" + msg.number + "</td><td>" + msg.stat + "</td><td>" + msg.msg + "</td>";
                errHTMLTab = errHTMLTab + "</tr>";
            }else
            {
                // generic command no x,y > no element on screen
                GlobalError = GlobalError + "<tr class=\"" + getErrClass(msg.stat) + "\">";
                GlobalError = GlobalError + "<td>" + msg.stat + "</td><td>" + msg.msg + "</td>";
                GlobalError = GlobalError + "</tr>";
            }
        }

        errHTMLTab = errHTMLTab+"</table>";
        GlobalError = GlobalError+"</table>";

        // template &1 image &2 name &3 err table
        String repHTML = String.format(rep,imageName+ ".jpg",imageName,GlobalError,errHTMLTab );
        projectUtils.saveFile( repName,repHTML);

    }


    public String getErrClass(String val)
    {
        String rc ="w3-container w3-white";
         switch (val) {
            case "WARNING":
                rc ="w3-container w3-yellow";
                break;
            case "ERROR":
                rc ="w3-container w3-red";
                break;
            case "INFO":
                rc ="w3-container w3-white";
                break;
          }
        return rc;

    }
}
