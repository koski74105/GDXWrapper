package com.deameyesapps.coSMIC;

import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.deameyesapps.GameLib.DrawShape;
import com.deameyesapps.GameLib.DrawText;
import com.deameyesapps.GameLib.GameMode;
import com.deameyesapps.GameLib.MouseClick;
import com.deameyesapps.GameLib.Point;

import java.util.ArrayList;
import java.util.List;

public class coSMICGameMap extends GameMode {
    //hold array of maptiles. each tile has a terrain and can have a building and a unit.

    static TerrainTile[][] map = new TerrainTile[20][20];

    public static void CheckMoveTile(int x, int y, int moveRange, List<Point> tileList, BattleUnit movingUnit)
    {
        if(moveRange > 0
                && x >= 0 && y >= 0 && x < map.length && y < map[0].length //still in the map
                && (map[x][y].occupyingUnit == null || map[x][y].occupyingUnit == movingUnit)
                //not enemy occupied
        ) //end of the line
        {
            tileList.add(new Point(x,y)); //only add this point and check subsequent points if movement isn't maxxed.
            CheckMoveTile(x + 1, y, moveRange - map[x][y].movementPenalty, tileList, movingUnit);
            CheckMoveTile(x - 1, y, moveRange - map[x][y].movementPenalty, tileList, movingUnit);
            CheckMoveTile(x, y + 1, moveRange - map[x][y].movementPenalty, tileList, movingUnit);
            CheckMoveTile(x, y - 1, moveRange - map[x][y].movementPenalty, tileList, movingUnit);
        }
    }

    public static List<Point> GenerateMoveTiles(BattleUnit unit)
    {
        //creates list of points based on moveRange;
        //enemy units block path.
        List<Point> moveTiles = new ArrayList<Point>();
        CheckMoveTile(unit.x, unit.y, unit.moveRange, moveTiles, unit);
        return moveTiles;
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

    }

    @Override
    public void dispose() {

    }
}
