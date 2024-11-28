package com.deameyesapps.GameLib;

import com.badlogic.gdx.math.Vector3;

public class CollisionVector extends Vector3 {
    public enum CollisionType{
        Nothing,
        Solid,
        Ladder
    }

    public CollisionType collisionType;

    public  CollisionVector(int x, int y, int z, CollisionType collisionType)
    {
        super(x, y, z);
        this.collisionType = collisionType;
    }
}
