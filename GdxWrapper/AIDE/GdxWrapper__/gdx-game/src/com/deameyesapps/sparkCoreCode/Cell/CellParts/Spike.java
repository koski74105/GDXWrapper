package com.deameyesapps.sparkCoreCode.Cell.CellParts;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.deameyesapps.GameLib.DrawShape;
import com.deameyesapps.GameLib.DrawText;
import com.deameyesapps.GameLib.MouseClick;
import com.deameyesapps.GameLib.RenderContainer;

import java.util.List;

public class Spike extends CellPart2Point {
    int _baseWidth;
    public  Spike(int x, int y, int endx, int endy, int baseWidth)
    {
        super(x, y, endx, endy);
        _isMouth = false;
        _baseWidth = baseWidth;
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

        int mountAngle = (int)getAngle(x, y, _endPointX, _endPointY);
        mountAngle += _originAngle + 90;
        while(mountAngle >= 360)
            mountAngle -= 360;
        int mountAngle2 = mountAngle + 180;
        if(mountAngle2 >= 360)
            mountAngle2 -= 360;

        double radVal = Math.toRadians(mountAngle);
        double radVal2 = Math.toRadians(mountAngle2);
        int mountPointX1 = mountPointX + (int)(Math.sin(radVal) * this._baseWidth);
        int mountPointY1 = mountPointY + (int)(Math.cos(radVal) * this._baseWidth);

        int mountPointX2 = mountPointX + (int)(Math.sin(radVal2) * this._baseWidth);
        int mountPointY2 = mountPointY + (int)(Math.cos(radVal2) * this._baseWidth);

        rc.LowerDraw.add(new DrawShape(DrawShape.Shape.TRIANGLE, Color.YELLOW,
                mountPointX1, mountPointY1, mountPointX2, mountPointY2, endPointX, endPointY));

        return  false;
    }

    @Override
    public void dispose() {

    }
}
