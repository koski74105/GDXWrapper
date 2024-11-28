package com.deameyesapps.sparkCoreCode.Cell.CellParts;

public abstract class CellPart2Point extends CellPart {

    int _endPointX;
    int _endPointY;

    public CellPart2Point(int x, int y, int endX, int endY) {
        super(x, y);
        this._endPointX = endX;
        this._endPointY = endY;
    }

}
