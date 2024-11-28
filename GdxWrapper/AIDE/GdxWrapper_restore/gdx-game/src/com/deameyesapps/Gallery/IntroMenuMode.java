package com.deameyesapps.Gallery;

import com.deameyesapps.GameLib.GameMode;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.deameyesapps.coSMIC.coSMICGameMap;
import com.deameyesapps.primo.TestMode;
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
    Button primoModeButton;
    Button coSMICModeButton;

    public  IntroMenuMode()
    {
        int centerX = (Gdx.graphics.getWidth() - 400)/2;
        testButton = new Button(centerX, Gdx.graphics.getHeight()- 100, 450, 100, Color.YELLOW,Color.BLACK, "TEST SCREEN");
        cellModeButton = new Button(centerX, Gdx.graphics.getHeight()- 300, 450, 100, Color.GREEN,Color.BLACK, "Cell Mode");
        primoModeButton = new Button(centerX, Gdx.graphics.getHeight()- 500, 450, 100, Color.GREEN,Color.BLACK, "Primo Mode");
        coSMICModeButton = new Button(centerX, Gdx.graphics.getHeight()- 700, 450, 100, Color.GREEN,Color.BLACK, "coSMIC");
    }

    @Override
    public boolean mouseClick(PerspectiveCamera camera, int X, int Y) {
        if(testButton.mouseClick(camera, X,Y))
        {
            ChangeMode = true;
            newMode = new TestClass();
        }

        if(cellModeButton.mouseClick(camera, X,Y))
        {
            ChangeMode = true;
            newMode = new CellMode();
        }

        if(primoModeButton.mouseClick(camera, X,Y))
        {
            ChangeMode = true;
            newMode = new TestMode();
        }

        if(coSMICModeButton.mouseClick(camera, X,Y))
        {
            ChangeMode = true;
            newMode = new coSMICGameMap();
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
    public void render(PerspectiveCamera camera, List<ModelInstance> modelInstances, List<DrawShape> LowerDraw, List<DrawText> text, List<DrawShape> upperDraw) {
        testButton.render(camera, modelInstances,LowerDraw, text, upperDraw);
        cellModeButton.render(camera, modelInstances,LowerDraw, text, upperDraw);
        primoModeButton.render(camera, modelInstances,LowerDraw, text, upperDraw);
        coSMICModeButton.render(camera, modelInstances,LowerDraw, text, upperDraw);
    }

    @Override
    public void dispose() {

    }
}