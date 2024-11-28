package com.deameyesapps.GameLib;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.ModelInstance;

import java.util.List;

public class Button extends GameObject {
    int X;
    int Y;
    int Width;
    int Height;
    String Text;
    Color ForeColor;
    Color BackColor;
    Color DisplayBackColor;
    public  Button()
    {

    }

    public  Button(int X, int Y, int Width, int Height, Color foreColor, Color backColor, String Text)
    {

        this.X = X;
        this.Y = Y;
        this.Width = Width;
        this.Height = Height;
        this.ForeColor = foreColor;
        this.BackColor = backColor;
        this.DisplayBackColor = backColor;
        this.Text = Text;
    }

    @Override
    public boolean mouseClick(PerspectiveCamera camera, int X, int Y) {
        boolean clicked = X >= this.X && Y >= this.Y && X <= this.X+ Width && Y <= this.Y + Height;
        if(clicked)
            DisplayBackColor =  new Color(0f, this.BackColor.g, this.BackColor.b, this.BackColor.a * 0.5f);
        else
            DisplayBackColor = BackColor;
        return clicked;
    }

    @Override
    public void mouseRelease() {

    }

    @Override
    public boolean checkClicks(List<MouseClick> clicks) {
        return false;
    }

    @Override
    public void render(PerspectiveCamera camera, List<ModelInstance> modelInstances, List<DrawShape> LowerDraw, List<DrawText> text, List<DrawShape> upperDraw) {
        LowerDraw.add(new DrawShape(DrawShape.Shape.RECTANGLE, DisplayBackColor, X, Y, Width, Height, 0, 0));
        text.add(new DrawText(ForeColor, X + 10, Y + Height/2, Text));
    }

    @Override
    public void dispose() {

    }
}
