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

    @Override
    public boolean mouseClick(PerspectiveCamera camera, int X, int Y) {
        this.X = X;
        this.Y = Y;

        if(testButton.mouseClick(camera, X, Y))
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
    public void render(PerspectiveCamera camera, List<ModelInstance> modelInstances, List<DrawShape> lowerDraw,
                       List<DrawText> text, List<DrawShape> upperDraw) {
        testButton.render(camera, modelInstances, lowerDraw, text, upperDraw);
        upperDraw.add(new DrawShape(DrawShape.Shape.CIRCLE, Color.GREEN, X, Y, 20,0,0,0));
        upperDraw.add(new DrawShape(DrawShape.Shape.CIRCLE, Color.RED, 0, 0, 20,0,0,0));
        controlStick.render(camera, modelInstances, lowerDraw, text,upperDraw);
    }

    @Override
    public void dispose() {

    }
}
