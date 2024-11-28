package com.deameyesapps.GameLib;

import com.badlogic.gdx.graphics.Color;

public class DrawShape {
    public enum Shape{RECTANGLE, CIRCLE, TRIANGLE, LINE};
    //public enum Color {RGB, RED, BLUE, GREEN, YELLOW, PURPLE, };
    public float[] shapeArgs;
    public DrawShape.Shape shape;
    public Color color;
    public  DrawShape()
    {
        shapeArgs = new float[6];
    }

    public DrawShape(DrawShape.Shape shape, Color color, int arg0, int arg1, int arg2, int arg3, int arg4, int arg5)
    {
        this.shape = shape;
        this.color = color;
        shapeArgs = new float[6];
        this.shapeArgs[0] = arg0;
        this.shapeArgs[1] = arg1;
        this.shapeArgs[2] = arg2;
        this.shapeArgs[3] = arg3;
        this.shapeArgs[4] = arg4;
        this.shapeArgs[5] = arg5;
    }
}
