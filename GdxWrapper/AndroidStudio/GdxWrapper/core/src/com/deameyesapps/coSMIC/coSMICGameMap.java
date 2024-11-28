package com.deameyesapps.coSMIC;

//import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.deameyesapps.GameLib.DrawShape;
import com.deameyesapps.GameLib.DrawText;
import com.deameyesapps.GameLib.GameMode;
import com.deameyesapps.GameLib.MouseClick;
import com.deameyesapps.GameLib.Point;
import com.deameyesapps.GameLib.RenderContainer;

import java.util.ArrayList;
import java.util.List;
import com.deameyesapps.GameLib.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.*;

public class coSMICGameMap extends GameMode {
    //hold array of maptiles. each tile has a terrain and can have a building and a unit.

    static TerrainTile[][] map;
	//BattleUnit testUnit;
	
	public coSMICGameMap()
	{
		map = new TerrainTile[20][20];
		
		for(int i = 0; i <20; i++)
		{
			for(int j = 0; j < 20; j++)
			{
				map[i][j] = new TerrainTile();
				map[i][j].x = i * TerrainTile.SPRITE_SIZE;
				map[i][j].y = j * TerrainTile.SPRITE_SIZE;
			}
		}
		map[5][5].assignUnit( new BattleUnit());
		//testUnit = new BattleUnit();
	}
	
    public static void checkMoveTile(int x, int y, int moveRange, List<Point> tileList, BattleUnit movingUnit)
    {
        if(moveRange > 0
                && x >= 0 && y >= 0 && x < map.length && y < map[0].length //still in the map
                && (map[x][y].occupyingUnit == null || map[x][y].occupyingUnit == movingUnit)
                //not enemy occupied
        ) //end of the line
        {
            tileList.add(new Point(x,y)); //only add this point and check subsequent points if movement isn't maxxed.
            checkMoveTile(x + 1, y, moveRange - map[x][y].movementPenalty, tileList, movingUnit);
            checkMoveTile(x - 1, y, moveRange - map[x][y].movementPenalty, tileList, movingUnit);
            checkMoveTile(x, y + 1, moveRange - map[x][y].movementPenalty, tileList, movingUnit);
            checkMoveTile(x, y - 1, moveRange - map[x][y].movementPenalty, tileList, movingUnit);
        }
    }

    public static List<Point> generateMoveTiles(BattleUnit unit)
    {
        //creates list of points based on moveRange;
        //enemy units block path.
        List<Point> moveTiles = new ArrayList<Point>();
		int temp = unit.x;
		temp = unit.y;
		temp = unit.moveRange;
		
        checkMoveTile(unit.x, unit.y, unit.moveRange, moveTiles, unit);
        return moveTiles;
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
		for(int i = 0; i < 20;i++)
		{
			for(int j = 0; j <20;j++)
			{
				map[i][j].render(rc);
			}
		}
		if(TerrainTile.selectedTile != null && TerrainTile.selectedTile.occupyingUnit != null)
		{
			List<Point> moveablePoints = generateMoveTiles(TerrainTile.selectedTile.occupyingUnit);
			for(Point point: moveablePoints)
			{
				rc.LowerDraw.add(new DrawShape(DrawShape.Shape.LINE, Color.RED, point.x, point.y, point.x + TerrainTile.SPRITE_SIZE, point.y, 20, 0));
				rc.LowerDraw.add(new DrawShape(DrawShape.Shape.LINE, Color.RED, x, y, x, y + TerrainTile.SPRITE_SIZE, 20, 0));
			}
			
		}
		//map[0][0].render(rc);
		//map[0][0].occupyingUnit.render(rc);		
		//testUnit.render(rc);
        return false;
    }

    @Override
    public void dispose() {

    }
}
