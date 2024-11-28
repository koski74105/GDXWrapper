package com.deameyesapps.GameLib;

import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector3;

import java.util.List;

public class ModelSpaceContainer  {
    public ModelInstance modelInstance;
    public List<CollisionVector> spaceOccupied;

    public ModelSpaceContainer(ModelInstance model, List<CollisionVector> spaceOccupied)
    {
        this.modelInstance = model;
        this.spaceOccupied = spaceOccupied;
    }
}
