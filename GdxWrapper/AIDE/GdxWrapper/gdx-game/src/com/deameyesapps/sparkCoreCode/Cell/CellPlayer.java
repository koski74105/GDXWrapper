package com.deameyesapps.sparkCoreCode.Cell;

import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.deameyesapps.GameLib.DrawShape;
import com.deameyesapps.GameLib.DrawText;
import com.deameyesapps.GameLib.GameObject;
import com.deameyesapps.GameLib.MouseClick;
import com.deameyesapps.GameLib.RenderContainer;
import com.deameyesapps.sparkCoreCode.Cell.CellParts.BeadyEye;
import com.deameyesapps.sparkCoreCode.Cell.CellParts.CellPart;
import com.deameyesapps.sparkCoreCode.Cell.CellParts.Chomper;
import com.deameyesapps.sparkCoreCode.Cell.CellParts.EyeStalk;
import com.deameyesapps.sparkCoreCode.Cell.CellParts.Spike;

import java.util.List;

public class CellPlayer extends CellBase {
    public CellPlayer(){
        super();
    }

    public  CellPlayer(int x, int y)
    {
        super(x, y);
        speed = 10;
        cellParts.add(new BeadyEye(0, -60));
        cellParts.add(new BeadyEye(0, 60));
        cellParts.add(new Chomper(-60, 0));

        cellParts.add(new EyeStalk(60, -60, 60, -110));
        cellParts.add(new EyeStalk(60, 60, 60, 110));

        cellParts.add(new Spike(120, -60, 120, -110,20));
        cellParts.add(new Spike(120, 60,  120,110,20));
    }

    public void eatCheck(List<GameObject> food)
    {
        for(int i = 0; i < cellParts.size();i++)
        {
            CellPart tempCellPart = cellParts.get(i);
            if(tempCellPart.isMouth())
            {
                for(int j = 0; j < food.size();j++)
                {
                    GameObject tempFood = food.get(j);
                    if(Math.abs(this.x + tempCellPart.getTranslateX(this.direction) - tempFood.x) < this.speed * 2
                      && Math.abs(this.y + tempCellPart.getTranslateY(this.direction) - tempFood.y) < this.speed * 2)
                    {
                        food.remove(j);
                    }
                }
            }
        }
    }

    @Override
    public boolean render(RenderContainer rc)
    {
        if(CellMode.getDistance(this.x,this.y,destinationX, destinationY) > 2*speed)
        {
           x += (int)(Math.sin(Math.toRadians(direction))* speed);
           y += (int)(Math.cos(Math.toRadians(direction))* speed);
        }
        super.render(rc);

        return  false;
    }

    public boolean mouseClick(List<MouseClick> clicks) {
        for(int i = 0; i < clicks.size();i++)
        {
            MouseClick mc = clicks.get(i);
            destinationX = mc.X;
            destinationY = mc.Y;
            direction = 270 - (int)CellMode.getAngle(x, y, destinationX, destinationY);
        }
        return  false;
    }
}
