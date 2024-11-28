package com.deameyesapps.coSMIC;

import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.deameyesapps.GameLib.DrawShape;
import com.deameyesapps.GameLib.DrawText;
import com.deameyesapps.GameLib.GameObject;
import com.deameyesapps.GameLib.MouseClick;

import java.util.List;

public class TerrainTile extends GameObject {

    public enum terrainType {grass, mountain}
    public int defenseValue;
    //18x18 sprite
    public  int movementPenalty;

    public  BattleUnit occupyingUnit;

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
        this.occupyingUnit.render(camera, modelInstances, LowerDraw, text, upperDraw);
    }

    @Override
    public void dispose() {

    }
}
