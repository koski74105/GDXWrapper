package com.deameyesapps.sparkCoreCode.Cell;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.deameyesapps.GameLib.Button;
import com.deameyesapps.GameLib.DrawShape;
import com.deameyesapps.GameLib.DrawText;
import com.deameyesapps.GameLib.GameMode;
import com.deameyesapps.GameLib.GameObject;
import com.deameyesapps.GameLib.MouseClick;
import com.deameyesapps.Gallery.IntroMenuMode;
import com.deameyesapps.GameLib.RenderContainer;

import java.util.ArrayList;
import java.util.List;

public class CellMode extends GameMode
{
    CellPlayer mainCharacter;
    CellBase otherChar;
    Button cellEditorButon;
    Button mainMenuButon;

    List<GameObject> roomObjects = new ArrayList<GameObject>();

    public  CellMode()
    {
        cellEditorButon = new Button(0,0, 450, 100, Color.GREEN,Color.BLACK, "Edit Mode");
        mainMenuButon = new Button(500, 0, 450, 100, Color.GREEN, Color.BLACK, "Main Menu");
        mainCharacter = new CellPlayer(1500, 1000);

        otherChar = new CellBase(1000, 1000);

        roomObjects.add(new CellFood(200,200));
    }

    public  static float getDistance(int x1, int y1, int x2, int y2)
    {
        int x3 = x1-x2;
        int y3 = y1 - y2;
        return (float) Math.sqrt(x3 * x3 + y3 * y3 );
    }
    public static float getAngle(int x1, int y1, int x2, int y2) {
        float angle = (float) Math.toDegrees(Math.atan2(y1 - y2, x1 - x2));
        if(angle < 0){
            angle += 360;
        }
        return angle;
    }

    public boolean mouseClick(List<MouseClick> clicks) {
        mainCharacter.mouseClick(clicks);
        if(mainMenuButon.mouseClick(clicks))
        {
            ChangeMode = true;
            //newMode = new IntroMenuMode();
            newMode = new IntroMenuMode();
        }

        if(cellEditorButon.mouseClick(clicks))
        {
            ChangeMode = true;
            newMode = new CellEditMode();
        }
        return  false;
    }

    @Override
    public void mouseRelease()
    {

    }

    @Override
    public boolean checkClicks(List<MouseClick> clicks) {
        return false;
    }

    @Override
    public boolean render(RenderContainer rc) {
        //generate Food
        if(Math.random() * 1000 > 995)
        {
            roomObjects.add(new CellFood(
                            (int)(Math.random() * Gdx.graphics.getWidth()),
                            (int)(Math.random() * Gdx.graphics.getHeight())));
        }

        mainCharacter.eatCheck(roomObjects);
        mainCharacter.render(rc);
        otherChar.render(rc);
        mainMenuButon.render(rc);
        cellEditorButon.render(rc);

        for(int i = 0; i < roomObjects.size();i++)
        {
            GameObject temp = roomObjects.get(i);
            temp.render(rc);
        }

        mouseClick(rc.clicks);
        return false;
    }

    @Override
    public void dispose() {

    }
}
