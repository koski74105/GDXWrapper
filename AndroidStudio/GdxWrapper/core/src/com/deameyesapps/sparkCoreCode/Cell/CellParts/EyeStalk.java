package com.deameyesapps.sparkCoreCode.Cell.CellParts;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.deameyesapps.GameLib.DrawShape;
import com.deameyesapps.GameLib.DrawText;
import com.deameyesapps.GameLib.MouseClick;
import com.deameyesapps.GameLib.RenderContainer;

import java.util.List;

public class EyeStalk extends CellPart2Point {
    public EyeStalk(int x, int y, int endX, int endY)
    {
        super(x,y, endX, endY);
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
    public boolean render(RenderContainer rc) {
        int mountPointX = this.originX + this.getTranslateX(this._originAngle);
        int mountPointY = this.originY + this.getTranslateY(this._originAngle);

        int endPointX = this.originX + this.getTranslateX(this._originAngle, this._endPointX, this._endPointY);
        int endPointY = this.originY + this.getTranslateY(this._originAngle, this._endPointX, this._endPointY);

        rc.LowerDraw.add(new DrawShape(DrawShape.Shape.LINE, Color.ORANGE,
                mountPointX
                ,mountPointY
                , endPointX,
                endPointY,
                10,0));

        rc.LowerDraw.add(new DrawShape(DrawShape.Shape.CIRCLE, Color.BLUE,
                endPointX
                ,endPointY
                , 15,0,0,0));

        rc.LowerDraw.add(new DrawShape(DrawShape.Shape.CIRCLE, Color.BLACK,
                endPointX
                ,endPointY
                , 5,0,0,0));

        return  false;
    }

    @Override
    public void dispose() {

    }
}
