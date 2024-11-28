package com.deameyesapps.sparkCoreCode.Cell;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.deameyesapps.GameLib.DrawShape;
import com.deameyesapps.GameLib.DrawText;
import com.deameyesapps.GameLib.GameObject;
import com.deameyesapps.GameLib.MouseClick;
import com.deameyesapps.GameLib.RenderContainer;
import com.deameyesapps.sparkCoreCode.Cell.CellParts.CellPart;

import java.util.ArrayList;
import java.util.List;

public class CellBase extends GameObject
{
    class Segment
    {
        int size;
        int attachPoint;
    }
    class headSegment extends  Segment
    {
        int headAttachPointCenter;
        int headAttachPoint45;
    }
    class tailSegment extends Segment
    {
        int tailAttachPointCenter;
        int getTailAttachPoint45;

        public List<DrawShape> renderSegment()
        {
            List<DrawShape> retVal = new ArrayList<DrawShape>();

            return  retVal;
        }
    }

    List<CellPart> cellParts;
    Segment[] segments;

    int direction;
    int destinationX;
    int destinationY;
    int speed;

    public CellBase()
    {
        this(0,0);
    }

    public CellBase(int x, int y)
    {
        this.cellParts = new ArrayList<CellPart>();
        direction=0;
        segments = new Segment[5];

        for(int i = 0; i < segments.length;i++)
        {
            segments[i] = new Segment();
            segments[i].size = segments.length + 2 - i;
        }

        segments[0] = new headSegment();
        segments[0].size = segments.length + 2;
        segments[segments.length - 1] = new tailSegment();
        segments[segments.length - 1].size = 3;

        this.x = x;
        this.y = y;
//        this.points.add(new Point(x, y));
        this.destinationX = x;
        this.destinationY = y;
        cellParts = new ArrayList<CellPart>();
    }

    @Override
    public void mouseRelease()
    {

    }

    @Override
    public boolean checkClicks(List<MouseClick> clicks) {
        return false;
    }

    @Override
    public boolean render(RenderContainer rc) {
        int xOffset = x;
        int yOffset = y;
        for(int i=0; i < segments.length;i++)
        {
            int x1 = (int)(Math.sin(Math.toRadians(direction))* 10 * segments[i].size);
            int y1 = (int)(Math.cos(Math.toRadians(direction))* 10 * segments[i].size);
            int x2 = (int)(Math.sin(Math.toRadians(direction+ 90))* 10 * segments[i].size);
            int y2 = (int)(Math.cos(Math.toRadians(direction + 90)) * 10 * segments[i].size);
            rc.LowerDraw.add(new DrawShape(DrawShape.Shape.TRIANGLE, Color.SLATE, xOffset + x1,yOffset + y1,
                    xOffset + x2,yOffset + y2,xOffset - x2,yOffset - y2));
            xOffset -= x1;
            yOffset -= y1;
        }
        //render parts:
        for(int i = 0; i < cellParts.size(); i++)
        {
            CellPart temp = cellParts.get(i);
            temp.setAngle(this.direction);
            temp.originX = x;
            temp.originY = y;
            temp.render(rc);
        }

        return  false;
    }

    @Override
    public void dispose() {

    }
}
