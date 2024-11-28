package com.deameyesapps.sparkCoreCode;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.deameyesapps.GameLib.RenderContainer;
import com.deameyesapps.sparkCoreCode.Cell.CellMode;
import com.deameyesapps.GameLib.Button;
import com.deameyesapps.GameLib.DrawShape;
import com.deameyesapps.GameLib.DrawText;
import com.deameyesapps.GameLib.GameMode;
import com.deameyesapps.GameLib.MouseClick;
import com.deameyesapps.sparkCoreCode.TestClass;

import java.util.List;

public class IntroMenuMode extends GameMode {
    Button testButton;
    Button cellModeButton;

    public  IntroMenuMode()
    {
        int centerX = (Gdx.graphics.getWidth() - 400)/2;
        testButton = new Button(centerX, Gdx.graphics.getHeight()- 100, 450, 100, Color.YELLOW,Color.BLACK, "TEST SCREEN");
        cellModeButton = new Button(centerX, Gdx.graphics.getHeight()- 300, 450, 100, Color.GREEN,Color.BLACK, "Cell Mode");
    }

    public boolean mouseClick(List<MouseClick> clicks) {
        if(testButton.mouseClick(clicks))
        {
            ChangeMode = true;
            newMode = new TestClass();
        }

        if(cellModeButton.mouseClick(clicks))
        {
            ChangeMode = true;
            newMode = new CellMode();
        }
        return  false;
    }

    @Override
    public void mouseRelease() {

    }

    @Override
    public boolean checkClicks(List<MouseClick> clicks) {
        return false;
    }

    @Override
    public boolean render(RenderContainer rc) {
        testButton.render(rc);
        cellModeButton.render(rc);
        mouseClick(rc.clicks);
        return  false;
    }

    @Override
    public void dispose() {

    }
}
