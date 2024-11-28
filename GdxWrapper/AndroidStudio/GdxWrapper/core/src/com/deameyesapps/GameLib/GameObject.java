package com.deameyesapps.GameLib;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector3;
import com.deameyesapps.GameLib.DrawShape;
import com.deameyesapps.GameLib.DrawText;
import com.deameyesapps.GameLib.MouseClick;

import java.util.List;

public abstract class GameObject extends AppCore{
    public  int x, y;

    public static Color CHARTREUSE = new Color(0x7fff00ff);

    public int minXOccupied;
    public int minYOccupied;
    public int minZOccupied;
    public int maxXOccupied;
    public int maxYOccupied;
    public int maxZOccupied;
//    public int getX()
//    {
//        return this.points.get(0).x;
//    }
//
//    public  int getY()
//    {
//        return this.points.get(0).y;
//    }
//
//    public  void setX(int x)
//    {
//        this.points.get(0).x = x;
//    }
//
//    public  void setY(int y)
//    {
//        this.points.get(0).y = y;
//    }

    //public  List<Point> points;

    public abstract void mouseRelease();
    public  abstract boolean checkClicks(List<MouseClick> clicks);
    public abstract  boolean render(RenderContainer rc);

    public  static float getDistance(int x1, int y1, int x2, int y2)
    {
        int x3 = x1-x2;
        int y3 = y1 - y2;
        return (float) Math.sqrt(x3 * x3 + y3 * y3 );
    }

    public  static float getDistance3d(float x1, float y1, float z1, float x2, float y2, float z2)
    {
        float x3 = x1-x2;
        float y3 = y1 - y2;
        float z3 = z1 - z2;
        return (float) Math.sqrt(x3 * x3 + y3 * y3 + z3 * z3 );
    }

    public static float getAngle(int x1, int y1, int x2, int y2) {
        float angle = (float) Math.toDegrees(Math.atan2(y1 - y2, x1 - x2));
        if(angle < 0){
            angle += 360;
        }
        return angle;
    }

    public abstract void dispose();
}
