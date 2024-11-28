package com.deameyesapps.sparkCoreCode.Cell.CellParts;

import com.deameyesapps.GameLib.GameObject;

//import java.awt.geom.Point2D;
//import java.awt.Point;


public abstract class CellPart extends GameObject {
    int _originAngle;
    boolean _isMouth;
    public int originX;
    public int originY;

    public  int getTranslateX(int relativeAngle, int xIn, int yIn)
    {
        //calculate Location based on offsets and angle
        double tempDistance = getDistance(0,0,xIn,yIn);
        double tempAngle = getAngle(0, 0, xIn, yIn) ;
        tempAngle += relativeAngle;
        if(tempAngle >= 360)
            tempAngle -= 360;
        return (int)(Math.sin(Math.toRadians(tempAngle)) * tempDistance);
    }

    public  int getTranslateX(int relativeAngle)
    {
        //calculate Location based on offsets and angle
        double tempDistance = getDistance(0,0,x,y);
        double tempAngle = getAngle(0, 0, x, y) ;
        tempAngle += relativeAngle;
        if(tempAngle >= 360)
            tempAngle -= 360;
        return (int)(Math.sin(Math.toRadians(tempAngle)) * tempDistance);
    }

    public  int getTranslateY(int relativeAngle, int xIn, int yIn)
    {
        //calculate Location based on offsets and angle
        double tempDistance = getDistance(0,0,xIn,yIn);
        double tempAngle = getAngle(0, 0, xIn, yIn) ;
        tempAngle += relativeAngle;
        if(tempAngle >= 360)
            tempAngle -= 360;
        return (int)(Math.cos(Math.toRadians( tempAngle)) * tempDistance);
    }

    public int getTranslateY(int relativeAngle)
    {
        //calculate Location based on offsets and angle
        double tempDistance = getDistance(0,0,x,y);
        double tempAngle = getAngle(0, 0, x, y) ;
        tempAngle += relativeAngle;
        if(tempAngle >= 360)
            tempAngle -= 360;
        return (int)(Math.cos(Math.toRadians( tempAngle)) * tempDistance);
    }

    public CellPart(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public  boolean isMouth()
    {
        return _isMouth;
    }

    public void setAngle(int angle)
    {
        _originAngle = angle;
    }
}
