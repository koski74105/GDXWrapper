package com.deameyesapps.sparkCoreCode.Cell.CellParts;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.deameyesapps.GameLib.DrawShape;
import com.deameyesapps.GameLib.DrawText;
import com.deameyesapps.GameLib.MouseClick;

import java.util.List;

public class EyeStalk extends CellPart2Point {
    public EyeStalk(int x, int y, int endX, int endY)
    {
        super(x,y, endX, endY);
        _isMouth = false;
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
        int mountPointX = this.originX + this.getTranslateX(this._originAngle);
        int mountPointY = this.originY + this.getTranslateY(this._originAngle);

        int endPointX = this.originX + this.getTranslateX(this._originAngle, this._endPointX, this._endPointY);
        int endPointY = this.originY + this.getTranslateY(this._originAngle, this._endPointX, this._endPointY);

        LowerDraw.add(new DrawShape(DrawShape.Shape.LINE, Color.ORANGE,
                mountPointX
                ,mountPointY
                , endPointX,
                endPointY,
                10,0));

        LowerDraw.add(new DrawShape(DrawShape.Shape.CIRCLE, Color.BLUE,
                endPointX
                ,endPointY
                , 15,0,0,0));

        LowerDraw.add(new DrawShape(DrawShape.Shape.CIRCLE, Color.BLACK,
                endPointX
                ,endPointY
                , 5,0,0,0));
    }

    @Override
    public void dispose() {

    }
}
