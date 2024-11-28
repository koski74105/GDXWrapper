package com.deameyesapps.GameLib;

import com.badlogic.gdx.graphics.Color;

public class DrawText {
    public int X;
    public int Y;
    public String Text;
    public Color color;
    public DrawText(Color color, int X, int Y, String text)
    {
        this.color = color;
        this.Text = text;
        this.X = X;
        this.Y = Y;
    }
}
