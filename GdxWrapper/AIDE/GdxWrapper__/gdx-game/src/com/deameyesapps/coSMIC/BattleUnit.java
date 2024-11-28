package com.deameyesapps.coSMIC;

import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.deameyesapps.GameLib.DrawShape;
import com.deameyesapps.GameLib.DrawText;
import com.deameyesapps.GameLib.GameObject;
import com.deameyesapps.GameLib.MouseClick;
import com.deameyesapps.GameLib.Point;
import com.deameyesapps.GameLib.RenderContainer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class BattleUnit extends GameObject {
    int attackValue;
    int currentHP;
    int maxHP;
    int maxShield;
    int currentShield;
    int zValue;
    int zAttackValue;
    boolean selected;
    int viewRange;
    int minAttackRange;
    int maxAttackRange;
    int moveRange;
    //16x16 sprites
    //repair at buildings
    //heal?
    //combine

    //attack grid
    public List<Point> attackTiles;
    //view grid
    public List<Point> viewTiles;
    //move grid
    public List<Point> moveTiles;

    public  void checkViewTile(int x, int y, int viewRange, List<Point> tileList)
    {
        tileList.add(new Point(x,y));
        if(viewRange > 0) //end of the line
        {
            checkViewTile(x + 1, y, viewRange - 1, tileList);
            checkViewTile(x - 1, y, viewRange - 1, tileList);
            checkViewTile(x, y + 1, viewRange - 1, tileList);
            checkViewTile(x, y - 1, viewRange - 1, tileList);
        }
    }

    public  void generateViewTiles()
    {
        //creates list of points relative to unitbased on viewRange;
        viewTiles = new ArrayList<Point>();
        checkViewTile(x, y, viewRange-1, viewTiles);
        viewTiles = new ArrayList<Point>(new HashSet<Point>(viewTiles));
    }

    public  void generateAttackTiles()
    {
        //creates list of points relative to unit based on attackRange;
        //first find points within min attack range.
        List<Point> innerAttackTiles = new ArrayList<Point>();
        checkViewTile(x,y, minAttackRange -1, innerAttackTiles);
        innerAttackTiles = new ArrayList<Point>(new HashSet<Point>(innerAttackTiles));
        //then find points within max range
        List<Point> outerAttackTiles = new ArrayList<Point>();
        checkViewTile(x,y, maxAttackRange , outerAttackTiles);
        outerAttackTiles = new ArrayList<Point>(new HashSet<Point>(outerAttackTiles));
        //return list within max not in min
        attackTiles = new ArrayList<Point>();
        for(int i = 0; i < outerAttackTiles.size();i++)
        {
            Point temp1 = outerAttackTiles.get(i);
            boolean inInner = false;
            for(int j = 0; j < innerAttackTiles.size(); j++)
            {
                Point temp2 = innerAttackTiles.get(j);
                if(temp1.x == temp2.x && temp1.y == temp2.y)
                    inInner = true;
            }
            if(!inInner)
                attackTiles.add(temp1);
        }
    }

    public  static boolean isAttackable(BattleUnit attacker, BattleUnit attackee)
    {
        //if attackee in range and is able to be attacked by attacker's weapon
        return true;
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
        return  false;
    }

    @Override
    public void dispose() {

    }
}
