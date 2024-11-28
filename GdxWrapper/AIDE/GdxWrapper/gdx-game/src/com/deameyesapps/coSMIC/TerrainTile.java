package com.deameyesapps.coSMIC;

//import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.deameyesapps.GameLib.DrawShape;
import com.deameyesapps.GameLib.DrawText;
import com.deameyesapps.GameLib.GameObject;
import com.deameyesapps.GameLib.MouseClick;
import com.deameyesapps.GameLib.RenderContainer;
import com.deameyesapps.GameLib.DrawShape;

import java.util.List;
import android.os.*;
//import android.graphics.*;

import android.graphics.drawable.shapes.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.*;

public class TerrainTile extends GameObject {
	//54x54 sprite
	static int SPRITE_SIZE = 108;
	public static TerrainTile selectedTile;
	
    public enum terrainType {open, groundBlocked, creep} //groundBlocked = flying units only
    public int defenseValue;
	
    public  int movementPenalty = 1;
	public Building building;
    public  BattleUnit occupyingUnit;
	
	public int buttonTimeoutMax;
    private int buttonTimeoutVal;
	
	public TerrainTile()
	{
		this.buttonTimeoutMax = 15;
        this.buttonTimeoutVal = 0;
	}

    @Override
    public void mouseRelease() {

    }
	
	public void assignUnit(BattleUnit unit)
	{
		this.occupyingUnit = unit;
		this.occupyingUnit.x = this.x + 1;
		this.occupyingUnit.y = this.y + 1;
	}
	
	private void clickAction()
	{
		//select
		if(selectedTile == null)
			selectedTile = this;
		else //selected
		{
			if(selectedTile == this) //deselect
				selectedTile = null;
			else if(this.occupyingUnit == null)//check move
			{
				//check this space is within selected object's move tiles
				assignUnit(selectedTile.occupyingUnit);
				selectedTile.occupyingUnit = null;
			}
			else
			{
				selectedTile = null;
			}
		}
	}

    @Override
    public boolean checkClicks(List<MouseClick> clicks) {
        if(buttonTimeoutVal == 0)
		{
			for(MouseClick mc: clicks)
			{
				if(mc.X >= this.x && mc.Y >= this.y && mc.X < this.x + SPRITE_SIZE && mc.Y < this.y + SPRITE_SIZE)
				{
					clickAction();
					buttonTimeoutVal = buttonTimeoutMax;
					return false;
				}
			}
		}
		return false;
    }

    @Override
    public boolean render(RenderContainer rc) {
		try{
		checkClicks(rc.clicks);
		if(buttonTimeoutVal > 0)
			buttonTimeoutVal --;
		rc.LowerDraw.add(new DrawShape(DrawShape.Shape.RECTANGLE, Color.MAGENTA, x, y, TerrainTile.SPRITE_SIZE, TerrainTile.SPRITE_SIZE, 0, 0));
		if(this.occupyingUnit != null)
        	this.occupyingUnit.render(rc);
			}
			catch(Exception ex)
			{
				
			}
		return false;
    }

    @Override
    public void dispose() {

    }
}
