package com.quantum.test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by uzie on 2/3/17.
 */
public class graphicUtils {

    public static void drowRec(String file,int x,int y ,int width ,int height)
    {
        drowRec( file, x, y , width , height, null);

    }


    public static void drowRec(String file,int x,int y ,int width ,int height,String numerToAdd)
    {
        File imageFile = new File(file);
        BufferedImage img = null;
        try {
            img = ImageIO.read(imageFile);
            Graphics2D graph = img.createGraphics();

            graph.setColor(Color.red);

            BasicStroke stroke = new BasicStroke(5); //5 pixels wide
            graph.setStroke(stroke);

            graph.drawRect(x, y, width, height);
            if (numerToAdd !=null)
            {
                graph.setFont(new Font( "SansSerif", Font.BOLD, 30 ));
                graph.drawString(numerToAdd,x,y);
            }
            ImageIO.write(img, "jpg", new File(file));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }



}
