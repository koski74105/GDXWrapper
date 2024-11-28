package com.deameyesapps.sparkCoreCode.Cell;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.deameyesapps.GameLib.DrawShape;
import com.deameyesapps.GameLib.DrawText;
import com.deameyesapps.GameLib.GameObject;
import com.deameyesapps.GameLib.MouseClick;

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
    public boolean mouseClick(PerspectiveCamera camera, int X, int Y) {
        return false;
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
        LowerDraw.add(new DrawShape(DrawShape.Shape.CIRCLE, Color.GREEN, x, y, 20,0,0,0));
    }

    @Override
    public void dispose() {

    }
}
