package com.deameyesapps.Gallery;

import com.deameyesapps.GameLib.GameMode;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.deameyesapps.GameLib.RenderContainer;
import com.deameyesapps.coSMIC.coSMICGameMap;
import com.deameyesapps.crayon.CrayonMode;
import com.deameyesapps.primo.TestMode;
import com.deameyesapps.sparkCoreCode.Cell.CellMode;
import com.deameyesapps.GameLib.Button;
import com.deameyesapps.GameLib.DrawShape;
import com.deameyesapps.GameLib.DrawText;
import com.deameyesapps.GameLib.MouseClick;
import com.deameyesapps.sparkCoreCode.TestClass;

import java.util.List;
import com.deameyesapps.BubbleSort.*;

public class IntroMenuMode extends GameMode {
    Button testButton;
    Button cellModeButton;
    Button primoModeButton;
    Button coSMICModeButton;
    Button crayonModeButton;
	Button bubbleModeButton;

    public  IntroMenuMode()
    {
        //int centerX = (Gdx.graphics.getWidth() - 400)/2;
		int centerX = 100;
        testButton = new Button(centerX, Gdx.graphics.getHeight()- 100, 450, 100, Color.YELLOW,Color.BLACK, "TEST SCREEN");
        cellModeButton = new Button(centerX, Gdx.graphics.getHeight()- 300, 450, 100, Color.GREEN,Color.BLACK, "Cell Mode");
        primoModeButton = new Button(centerX, Gdx.graphics.getHeight()- 500, 450, 100, Color.GREEN,Color.BLACK, "Primo Mode");
        coSMICModeButton = new Button(centerX, Gdx.graphics.getHeight()- 700, 450, 100, Color.GREEN,Color.BLACK, "coSMIC");
        crayonModeButton = new Button(centerX, Gdx.graphics.getHeight()- 900, 450, 100, Color.GREEN,Color.BLACK, "Crayon");
		bubbleModeButton = new Button(600, Gdx.graphics.getHeight()- 100, 450, 100, Color.GREEN,Color.BLACK, "Bubble");
    }

    public boolean mouseClick(List<MouseClick> clicks) {
        if(testButton.mouseClick(clicks))
        {
            ChangeMode = true;
            newMode = new TestClass();
        }

        else if(cellModeButton.mouseClick(clicks))
        {
            ChangeMode = true;
            newMode = new CellMode();
        }

        else if(primoModeButton.mouseClick(clicks))
        {
            ChangeMode = true;
            newMode = new TestMode();
        }

        else if(coSMICModeButton.mouseClick(clicks))
        {
            ChangeMode = true;
            newMode = new coSMICGameMap();
        }

        else if(crayonModeButton.mouseClick(clicks))
        {
            ChangeMode = true;
            newMode = new CrayonMode();
        }

		else if(bubbleModeButton.mouseClick(clicks))
        {
            ChangeMode = true;
            newMode = new BubbleSortMode();
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
    public boolean render(RenderContainer rc){
        testButton.render(rc);
        cellModeButton.render(rc);
        primoModeButton.render(rc);
        coSMICModeButton.render(rc);
        crayonModeButton.render(rc);
		bubbleModeButton.render(rc);
        mouseClick(rc.clicks);
        return  false;
    }

    @Override
    public void dispose() {

    }
}
