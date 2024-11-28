package com.deameyesapps.sparkCoreCode.Cell.CellParts;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.deameyesapps.GameLib.DrawShape;
import com.deameyesapps.GameLib.DrawText;
import com.deameyesapps.GameLib.MouseClick;

import java.util.List;

public class Grinder extends CellPart {
    public Grinder(int x, int y)
    {
        super(x,y);
        _isMouth = true;
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
        LowerDraw.add(new DrawShape(DrawShape.Shape.CIRCLE, Color.PURPLE,
                this.originX + this.getTranslateX(this._originAngle)
                ,this.originY + this.getTranslateY(this._originAngle)
                , 10,0,0,0));
    }

    @Override
    public void dispose() {

    }
}
