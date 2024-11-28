package com.deameyesapps.GameLib;
import  com.badlogic.gdx.graphics.Color;

public class DrawCollection {
    enum Shape{RECTANGLE, CIRCLE, TRIANGLE};
    float[] shapeArgs;
    Shape shape;
    int depth;
    Color color;
    public  DrawCollection()
    {
        shapeArgs = new float[6];
        depth = 0;
    }
}
