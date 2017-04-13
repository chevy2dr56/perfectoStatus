package com.quantum.accessibilityObjs;

/**
 * Created by uzie on 2/2/17.
 */
public class AccSingleLine {


    int x;
    int y ;
    int width;
    int height;
    String status;
    String msg;
    boolean used= false;

    public  AccSingleLine(String status,String msg,int x,int y ,int width ,int height)
    {
        this.status=status;
        this.msg=msg;
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
    }

    public int getX()
    {
        return x;
    }
    public int getY()
    {
        return y;
    }
    public int getWidth()
    {
        return width;
    }
    public void setY(int y)
    {
         this.y=y;
    }
    public int getHeight()
    {
        return height;
    }
    public String getMsg()
    {
        return msg;
    }

    public String getStatus()
    {
        return status;
    }
    public String getlocationID()
    {
        return String.valueOf(x)+String.valueOf(y)+String.valueOf(width)+String.valueOf(height);
    }

    public void markAsUsed()
    {
        used=true;
    }
}
