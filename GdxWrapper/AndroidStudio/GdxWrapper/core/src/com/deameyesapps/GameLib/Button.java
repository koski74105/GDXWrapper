package com.deameyesapps.GameLib;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g3d.ModelInstance;

import java.util.List;

public class Button extends GameObject {
    int X;
    int Y;
    int Width;
    int Height;
    //string Text
	private DrawText text;
    Color ForeColor;
	public Color BackColor;
    Color DisplayBackColor;
    public  Button()
    {

    }

    public int buttonTimeoutMax;
    private int buttonTimeoutVal;
//    public void setBackColor(Color newBackColor)
//    {
//
//    }
//
//    public  void getBackColor()
//    {
//
//    }

    public  Button(int X, int Y, int Width, int Height, Color foreColor, Color backColor, String text)
    {
        this.X = X;
        this.Y = Y;
        this.Width = Width;
        this.Height = Height;
        this.ForeColor = foreColor;
        this.BackColor = backColor;
        this.DisplayBackColor = backColor;
        //this.Text = Text;
		this.text = new DrawText(ForeColor, X + 10, Y + Height/2 + 15, text);

        this.buttonTimeoutMax = 0;
        this.buttonTimeoutVal = 0;
    }

    public boolean mouseClick(List<MouseClick> clicks) {
        if(buttonTimeoutVal == 0)
        {
            boolean clicked = false;
            for(int i = 0; i < clicks.size();i++)
            {
                MouseClick mc = clicks.get(i);
                if(mc.X >= this.X && mc.Y >= this.Y && mc.X <= this.X+ Width && mc.Y <= this.Y + Height)
                    clicked = true;
            }

            if(clicked)
            {
                DisplayBackColor =  new Color(0f, this.BackColor.g, this.BackColor.b, this.BackColor.a * 0.5f);
                buttonTimeoutVal = buttonTimeoutMax;
            }
            else
                DisplayBackColor = BackColor;

            return clicked;
        }
        else
            return  false;

    }

    @Override
    public void mouseRelease() {
        DisplayBackColor = BackColor;
    }

    @Override
    public boolean checkClicks(List<MouseClick> clicks) {
        return false;
    }

    @Override
    //public void render(PerspectiveCamera camera, List<ModelInstance> modelInstances, List<DrawShape> LowerDraw, List<DrawText> text, List<DrawShape> upperDraw)
    public  boolean render(RenderContainer rc)
    {
        rc.LowerDraw.add(new DrawShape(DrawShape.Shape.RECTANGLE, DisplayBackColor, X, Y, Width, Height, 0, 0));
        //rc.text.add(new DrawText(ForeColor, X + 10, Y + Height/2 + 15, Text));
		rc.text.add(text);

        if(buttonTimeoutVal > 0)
            buttonTimeoutVal -= 1;

        return this.mouseClick(rc.clicks);
    }

    @Override
    public void dispose() {

    }
}
