package com.deameyesapps.sparkCoreCode.Cell.CellParts;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.deameyesapps.GameLib.DrawShape;
import com.deameyesapps.GameLib.DrawText;
import com.deameyesapps.GameLib.MouseClick;
import com.deameyesapps.GameLib.RenderContainer;

import java.util.List;

public class Jet extends CellPart {
    public  Jet(int x, int y)
    {
        super(x, y);
        _isMouth = false;
    }

    @Override
    public void mouseRelease() {

    }

    @Override
    public boolean checkClicks(List<MouseClick> clicks) {
        return false;
    }

    @Override
    public boolean render(RenderContainer rc)
    {
        rc.LowerDraw.add(new DrawShape(DrawShape.Shape.CIRCLE, Color.ORANGE,
                this.originX + this.getTranslateX(this._originAngle)
                ,this.originY + this.getTranslateY(this._originAngle)
                , 10,0,0,0));

        return  false;
    }

    @Override
    public void dispose() {

    }
}
