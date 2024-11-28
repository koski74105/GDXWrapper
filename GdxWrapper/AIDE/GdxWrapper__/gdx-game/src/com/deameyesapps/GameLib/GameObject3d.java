package com.deameyesapps.GameLib;

import com.badlogic.gdx.math.Vector3;

import java.util.List;

public abstract class GameObject3d extends GameObject {
    public List<Vector3> spaceOccupied;

    abstract void calculateSpaceOccupied();

    public boolean checkCollision(int x, int y, int z)
    {
        Vector3 temp;
        for(int i = 0; i < spaceOccupied.size(); i++)
        {
            temp = spaceOccupied.get(i);
            if(temp.x == x && temp.y == y && temp.z == z)
            {
                return true;
            }
        }
        return false;
    }
}
