package com.deameyesapps.GameLib;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
//import com.badlogic.gdx.graphics.g3d.utils.shapebuilders.CylinderShapeBuilder;
import com.badlogic.gdx.math.Vector3;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Limb3d extends GameObject3d {
    public enum Shape{
        cylinder, cone1
    }

    ModelBuilder modelBuilder;
    Color color;
    float diameter;
    private Model baseRound;
    private Model endRound;
    private Model shaft;

    private ModelInstance shaftInstance;
    private ModelInstance baseInstance;
    private ModelInstance endInstance;
    public  Shape shape;
    public float baseX;
    public float baseY;
    public float baseZ;
    public float endX;
    public float endY;
    public float endZ;

    public float yawVal;
    public float pitchVal;

    public  Limb3d nextLimb;

    public void setEndPoint(float x, float y, float z)
    {
        Vector3 temp = new Vector3(x,y,z);
        setEndPoint(temp);
    }

    public void setEndPoint(Vector3 endPoint)
    {
        endX = endPoint.x;
        endY = endPoint.y;
        endZ = endPoint.z;
        endInstance.transform.setToTranslation(endPoint);

        float yVar = baseY - endY;
        float xVar = baseX - endX;
        float zVar = baseZ - endZ;

        float length = (float) Math.sqrt(
                xVar * xVar + yVar * yVar + zVar * zVar);

        if(shape == Shape.cylinder)
        {
            shaft = modelBuilder.createCylinder(diameter, length, diameter, 12,
                    new Material(ColorAttribute.createDiffuse(color)),
                    VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);
        }
        if(shape == Shape.cone1)
        {
            shaft = modelBuilder.createCone(diameter, length, diameter, 12,
                    new Material(ColorAttribute.createDiffuse(color)),
                    VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);
        }


        shaftInstance = new ModelInstance(shaft, Math.abs(baseX + endX)/2, Math.abs(baseY + endY)/2, Math.abs(baseZ + endZ)/2);

        yawVal = (float) Math.toDegrees(Math.atan2(xVar, zVar)); //set rotation
        pitchVal = (float)Math.toDegrees(Math.atan2(Math.sqrt(zVar * zVar + xVar * xVar), yVar) + Math.PI);
        this.shaftInstance.transform.setFromEulerAngles( yawVal, pitchVal,0).trn((baseX + endX)/2, (baseY + endY)/2, (baseZ + endZ)/2);
        calculateSpaceOccupied();
    }

    public Limb3d(Color color, float origX, float origY, float origZ, float endX, float endY, float endZ, float diameter, Shape shape, Limb3d nextLimb)
    {
        this(color, origX,origY, origZ, endX, endY, endZ, diameter, shape);
        this.nextLimb = nextLimb;
    }

    //Create list of points occupied by limb for collision detection
    @Override
    void calculateSpaceOccupied()
    {
        //create list of points along limb
        List<Vector3> betweenModels = ModelShortCut.getMidPoints(
                new Vector3(baseX,baseY,baseZ),new Vector3(endX,endY,endZ));
//        betweenModels.add(new Vector3(baseX,baseY,baseZ));
//        betweenModels.add(new Vector3(endX,endY,endZ));
//
//        boolean filledGaps = false;
//        //while filledGaps has more than a diameter between
//        //points iterate through list and insert a point in between
//        //points
//
//        while(!filledGaps)
//        {
//            boolean gapFound = false;
//            for(int i = 0; i < betweenModels.size()-1; i++)
//            {
//                Vector3 position1 = betweenModels.get(i);
//                Vector3 position2 = betweenModels.get(i + 1);
//                if(getDistance3d(position1.x, position1.y, position1.z, position2.x, position2.y, position2.z) > 0.5)
//                {
//                    gapFound = true;
//                    betweenModels.add(i+1, new Vector3(
//                            (position1.x + position2.x) /2,
//                            (position1.y + position2.y) /2,
//                            (position1.z + position2.z) /2));
//                }
//            }
//            if(!gapFound)
//                filledGaps = true;
//        }

        spaceOccupied = new ArrayList<Vector3>();
        //traverse to endpoint
        Vector3 currentPoint;
        for (int i = 0;i < betweenModels.size(); i++)
        {
            currentPoint = betweenModels.get(i);
            for(int ix = (int)diameter*-1; ix <= diameter;ix++)
            {
                for(int iy = (int)diameter*-1; iy <= diameter;iy++)
                {
                    for(int iz = (int)diameter*-1; iz <= diameter;iz++)
                    {
                        spaceOccupied.add(new Vector3(ix + (int) currentPoint.x,iy + (int) currentPoint.y,iz + (int) currentPoint.z));
                    }
                }
            }
        }
        //reached end of limb
        //get rid of all duplicates
        spaceOccupied = new ArrayList<Vector3>(new HashSet<Vector3>(spaceOccupied));
    }

   public Limb3d(Color color, float origX, float origY, float origZ, float endX, float endY, float endZ, float diameter, Shape shape)
    {
        this.endX = endX;
        this.endY = endY;
        this.endZ = endZ;
        this.baseX = origX;
        this.baseY = origY;
        this.baseZ = origZ;
        this.diameter = diameter;
        this.shape = shape;
        this.color = color;

        modelBuilder = new ModelBuilder();
        float length = (float) Math.sqrt(
                (origX - endX) * (origX - endX) +
                (origY - endY) * (origY - endY) +
                (origZ - endZ) * (origZ - endZ));

        if(shape == Shape.cylinder)
        {
            shaft = modelBuilder.createCylinder(diameter, length, diameter, 12,
                    new Material(ColorAttribute.createDiffuse(color)),
                    VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);
        }
        if(shape == Shape.cone1)
        {
            shaft = modelBuilder.createCone(diameter, length, diameter, 12,
                    new Material(ColorAttribute.createDiffuse(color)),
                    VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);
        }

        baseRound = modelBuilder.createSphere(diameter,diameter, diameter, 12, 12,
                new Material(ColorAttribute.createDiffuse(Color.ORANGE)),
                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);

        endRound = modelBuilder.createSphere(diameter,diameter, diameter, 12, 12,
                new Material(ColorAttribute.createDiffuse(Color.RED)),
                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);

        shaftInstance = new ModelInstance(shaft, Math.abs(origX + endX)/2, Math.abs(origY + endY)/2, Math.abs(origZ + endZ)/2);
        baseInstance = new ModelInstance(baseRound, origX, origY, origZ);
        endInstance = new ModelInstance(endRound, endX, endY, endZ);
        setEndPoint(endX, endY, endZ);
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
    public void render(PerspectiveCamera camera, List<ModelInstance> modelInstances,
                       List<DrawShape> LowerDraw, List<DrawText> text, List<DrawShape> upperDraw) {
        modelInstances.add(endInstance);
        modelInstances.add(baseInstance);
        modelInstances.add(shaftInstance);

        if(this.nextLimb != null)
            nextLimb.render(camera, modelInstances, LowerDraw, text, upperDraw);
    }

    @Override
    public void dispose() {
            endRound.dispose();
            baseRound.dispose();
            shaft.dispose();
    }
}
