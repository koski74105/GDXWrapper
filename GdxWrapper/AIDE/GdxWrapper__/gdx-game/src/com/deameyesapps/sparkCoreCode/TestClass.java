package com.deameyesapps.sparkCoreCode;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.deameyesapps.GameLib.Button;
import com.deameyesapps.GameLib.ControlStick;
import com.deameyesapps.GameLib.DrawShape;
import com.deameyesapps.GameLib.DrawText;
import com.deameyesapps.GameLib.GameMode;
import com.deameyesapps.GameLib.MouseClick;
import com.deameyesapps.Gallery.IntroMenuMode;
import com.deameyesapps.GameLib.RenderContainer;

import java.util.List;

public class TestClass extends GameMode {
    public int X = 200;
    public int Y = 200;
    Button testButton;
    ControlStick controlStick;

    public  TestClass()
    {
        testButton = new Button(500, 500, 400, 100, Color.BLACK, Color.RED, "Test Button");
        controlStick = new ControlStick(1200, 600, 100, 50, Color.BLUE, Color.GREEN);
    }

    public boolean mouseClick(List<MouseClick> clicks) {
        for(int i = 0; i < clicks.size();i++)
        {
            MouseClick mc = clicks.get(i);
            this.X = mc.X;
            this.Y = mc.Y;
        }

        if(testButton.mouseClick(clicks))
        {
            ChangeMode = true;
            newMode = new IntroMenuMode();
        }
        return  false;
    }

    public void mouseRelease()
    {
        testButton.mouseRelease();
    }

    @Override
    public boolean checkClicks(List<MouseClick> clicks) {
        controlStick.checkClicks(clicks);
        return false;
    }

    @Override
    public boolean render(RenderContainer rc) {
        testButton.render(rc);
        rc.upperDraw.add(new DrawShape(DrawShape.Shape.CIRCLE, Color.GREEN, X, Y, 20,0,0,0));
        rc.upperDraw.add(new DrawShape(DrawShape.Shape.CIRCLE, Color.RED, 0, 0, 20,0,0,0));
        controlStick.render(rc);
        mouseClick(rc.clicks);
        return  false;
    }

    @Override
    public void dispose() {

    }
}
