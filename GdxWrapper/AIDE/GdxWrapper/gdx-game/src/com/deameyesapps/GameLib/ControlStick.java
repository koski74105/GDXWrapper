package com.deameyesapps.GameLib;

import com.badlogic.gdx.graphics.Color;
//import com.badlogic.gdx.graphics.g3d.ModelInstance;

import java.util.List;

public class ControlStick extends GameObject {
    public enum StickType{
        STANDARD, TRIGGER, RELEASE
    }

    int X;
    int Y;
    public int Direction;
    public int Velocity;
    int AnalogPositionX;
    int AnalogPositionY;
    int Size;
    int criticalActionSize;
    Color BackColor;
    Color ForeColor;
    Color CriticalColor;
    int KnobSize;
    int MouseIndex;
    StickType stickType;

    @Override
    public void mouseRelease() {

    }

    @Override
    public boolean checkClicks(List<MouseClick> clicks) {
        boolean stillClicked = false;
        for(int i = 0; i < clicks.size();i++)
        {
            MouseClick temp = clicks.get(i);
            int tempSize = Size;
            if(MouseIndex == -1 && temp.X >= X - tempSize && temp.Y >= Y - tempSize && temp.X <= X + tempSize && temp.Y <= Y + tempSize)
            {
                MouseIndex = temp.Index;
            }

            if(temp.Index == MouseIndex)
            {
                stillClicked = true;
                int tempX = temp.X - X;
                int tempY = temp.Y - Y;
                Velocity =  (int)Math.round(Math.sqrt(tempX * tempX +tempY * tempY));
                if(Velocity > Size)
                    Velocity = Size;
                Direction = 270 - (int) getAngle(X, Y, temp.X, temp.Y) + 360;
                if(Direction >= 360) Direction -= 360;
                AnalogPositionX = (int)(Math.sin(Math.toRadians(Direction))* Velocity);
                AnalogPositionY = (int)(Math.cos(Math.toRadians(Direction))* Velocity);
            }
        }
        if(!stillClicked)
        {
            MouseIndex = -1;
            AnalogPositionX = 0;
            AnalogPositionY = 0;
            Velocity = 0;
            Direction = 0;
        }
        return false;
    }

    @Override
    public boolean render(RenderContainer rc)
    {
        if(this.stickType == StickType.RELEASE || this.stickType == StickType.TRIGGER)
        {
            rc.LowerDraw.add(new DrawShape(DrawShape.Shape.CIRCLE, this.CriticalColor, X, Y, criticalActionSize,0,0,0));
        }
        rc.LowerDraw.add(new DrawShape(DrawShape.Shape.CIRCLE, BackColor, X, Y, Size,0,0,0));
        rc.LowerDraw.add(new DrawShape(DrawShape.Shape.CIRCLE, ForeColor, X + AnalogPositionX, Y + AnalogPositionY, KnobSize,0,0,0));

        return  false;
    }

    @Override
    public void dispose() {

    }

    public ControlStick(int X, int Y, int padSize, int nubSize, Color backColor, Color foreColor)
    {
        this.X = X;
        this.Y = Y;
        this.Size = padSize;
        this.KnobSize = nubSize;
        this.BackColor = backColor;
        this.ForeColor = foreColor;
        this.stickType = StickType.STANDARD;
    }

    public ControlStick(int X, int Y, int padSize, int nubSize, int criticalActionSize, StickType stickType,Color criticalColor,Color backColor, Color foreColor)
    {
        this.X = X;
        this.Y = Y;
        this.Size = padSize;
        this.KnobSize = nubSize;
        this.BackColor = backColor;
        this.ForeColor = foreColor;
        this.CriticalColor = criticalColor;
        this.criticalActionSize = criticalActionSize;
        this.stickType = stickType;
    }
}
