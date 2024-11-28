package com.deameyesapps.sparkCoreCode.Cell;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.deameyesapps.GameLib.DrawShape;
import com.deameyesapps.GameLib.DrawText;
import com.deameyesapps.GameLib.GameObject;
import com.deameyesapps.GameLib.MouseClick;
import com.deameyesapps.GameLib.RenderContainer;

import java.util.List;

public class CellFood extends GameObject {
    public  CellFood(int X, int Y)
    {
//        setX(X);
//        setY(Y);
        this.x = X;
        this.y = Y;
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
        rc.LowerDraw.add(new DrawShape(DrawShape.Shape.CIRCLE, Color.GREEN, x, y, 20,0,0,0));
        return  false;
    }

    @Override
    public void dispose() {

    }
}
