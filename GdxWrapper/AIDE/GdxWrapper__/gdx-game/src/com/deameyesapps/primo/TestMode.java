package com.deameyesapps.primo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector3;
import com.deameyesapps.GameLib.Button;
import com.deameyesapps.GameLib.CollisionVector;
import com.deameyesapps.GameLib.ControlStick;
import com.deameyesapps.GameLib.DrawShape;
import com.deameyesapps.GameLib.DrawText;
import com.deameyesapps.GameLib.GameMode;
import com.deameyesapps.GameLib.Limb3d;
import com.deameyesapps.GameLib.ModelShortCut;
import com.deameyesapps.GameLib.ModelSpaceContainer;
import com.deameyesapps.GameLib.Plane3D;
import com.deameyesapps.GameLib.ProtoLimb3d;
import com.deameyesapps.GameLib.MouseClick;
import com.deameyesapps.GameLib.RenderContainer;

import java.util.ArrayList;
import java.util.List;

public class TestMode extends GameMode {
    float gravity = 0.75f;
    Button xPlus;
    Button xMinus;
    Button yPlus;
    Button yMinus;
    Button zPlus;
    Button zMinus;
    Button rotPlus;
    Button rotMinus;
    Button movForward;
    Button movReverse;
    Button declPlus;
    Button declMinus;

    //ControlStick controlStick;
    //PerspectiveCamera setCamera;
    int declination = 0;

    List<ProtoLimb3d> testLimbs;
    //Limb3d cylLimb;

    List<ModelSpaceContainer> _3dObjects;
    private int camAngle;
    public static AssetManager manager;

    public  TestMode()
    {
//        controlStick = new ControlStick(1800, 180, 100, 50, Color.BLUE, Color.GREEN);
        xPlus = new Button(0, 100, 200, 100, Color.BLACK, Color.RED, "x+");
        xMinus = new Button(0, 0, 200, 100, Color.BLACK, Color.RED, "x-");
        yPlus = new Button(250, 100, 200, 100, Color.BLACK, Color.RED, "y+");
        yMinus = new Button(250, 0, 200, 100, Color.BLACK, Color.RED, "y-");
        zPlus = new Button(500, 100, 200, 100, Color.BLACK, Color.RED, "z+");
        zMinus = new Button(500, 0, 200, 100, Color.BLACK, Color.RED, "z-");
        declPlus = new Button(750, 100, 200, 100, Color.BLACK, Color.RED, "D+");
        declMinus = new Button(1250, 100, 200, 100, Color.BLACK, Color.RED, "D-");

        rotPlus = new Button(750, 0, 200, 100, Color.BLACK, Color.RED, "r+");
        movForward = new Button(1000, 100, 200, 100, Color.BLACK, Color.RED, "Fwd");
        movReverse = new Button(1000, 0, 200, 100, Color.BLACK, Color.RED, "Rev");
        rotMinus = new Button(1250, 0, 200, 100, Color.BLACK, Color.RED, "r-");

        _3dObjects = new ArrayList<ModelSpaceContainer>();
        testLimbs = new ArrayList<ProtoLimb3d>();
        testLimbs.add(new ProtoLimb3d(Color.CYAN, 4, 0, 3, 4,3,3,1, 0.25f));

        //cylLimb = new Limb3d(Color.CYAN, 48, 0, 3, 48, 5, 3, 1, Limb3d.Shape.cone1);
        //cylLimb = new Limb3d(Color.CYAN, 48, 0, 3, 48, 5, 3, 1, Limb3d.Shape.cylinder);

        for(int i = -14; i >= -24; i--)
        {
            testLimbs.add(new ProtoLimb3d(CHARTREUSE, -i, 0, 3, -i,3,3,1, 0.25f));
        }

        Model wall;
        //Perimeter
        wall = Plane3D.createPlane("textures/ground1.jpg", -50,0,0,50,0,0,50,10,0,-50,10,0);
        _3dObjects.add(new ModelSpaceContainer(
                new ModelInstance(wall, 50,0,0),
                Plane3D.calculateSpaceOccupied(-50,0,0,50,0,0,50,10,0,-50,10,0,50,0,0, CollisionVector.CollisionType.Solid)));
        _3dObjects.add(new ModelSpaceContainer(
                new ModelInstance(wall, 50,0,100),
                Plane3D.calculateSpaceOccupied(-50,0,0,50,0,0,50,10,0,-50,10,0,50,0,100, CollisionVector.CollisionType.Solid)));

        wall = Plane3D.createPlane("textures/ground1.jpg", 0,0,-50,0,0,50,0,10,50,0,10,-50);
        _3dObjects.add(new ModelSpaceContainer(
                new ModelInstance(wall, 0,0,50),
                Plane3D.calculateSpaceOccupied(0,0,-50,0,0,50,0,10,50,0,10,-50,0,0,50, CollisionVector.CollisionType.Solid)));
        _3dObjects.add(new ModelSpaceContainer(
                new ModelInstance(wall, 100,0,50),
                Plane3D.calculateSpaceOccupied(0,0,-50,0,0,50,0,10,50,0,10,-50,100,0,50, CollisionVector.CollisionType.Solid)));
//        //WALLS Textured
//        wall = Plane3D.createPlane("textures/ground1.jpg", -10,0,0,10,0,0,10,10,0,-10,10,0);
//        _3dObjects.add(new ModelSpaceContainer(
//                new ModelInstance(wall, 4,0,4),
//                Plane3D.calculateSpaceOccupied(-10,0,0,10,0,0,10,10,0,-10,10,0,4,0,4, CollisionVector.CollisionType.Solid)));
//
//        //angled
//        wall = Plane3D.createPlane("textures/ground1.jpg", -10,5,0,10,0,0,10,10,0,-10,15,0);
//        _3dObjects.add(new ModelSpaceContainer(
//                new ModelInstance(wall, 24,0,14),
//                Plane3D.calculateSpaceOccupied(-10,5,0,10,0,0,10,10,0,-10,15,0,24,0,14, CollisionVector.CollisionType.Solid)
//                ));
//
//        //perpendicular
//        wall = Plane3D.createPlane("textures/loz/lozFence.png", 0,0,-10,0,0,10,0,10,10,0,10,-10);
//        _3dObjects.add( new ModelSpaceContainer(
//                new ModelInstance(wall, 44,0,4),
//                Plane3D.calculateSpaceOccupied(0,0,-10,0,0,10,0,10,10,0,10,-10,44,0,4, CollisionVector.CollisionType.Ladder)
//        ));
//
        //horizontal
        wall = Plane3D.createPlane("textures/loz/lozBrick00.png", -5,10,-5,-5,10,5,5,10,5,5,10,-5);
        _3dObjects.add(new ModelSpaceContainer(
                new ModelInstance(wall, 64,0,4),
                Plane3D.calculateSpaceOccupied(-5,10,-5,-5,10,5,5,10,5,5,10,-5,64,0,4, CollisionVector.CollisionType.Solid)
        ));
        _3dObjects.addAll(ModelShortCut.createLevel());
        _3dObjects.add(ModelShortCut.createBoxes(4));//adds boxes
        camera.position.set(50, 1, 50);
        //_3dObjects.addAll(collisionSpheres());
    }



    private  void updateLimbs(float x, float y, float z)
    {
        for(int i = 0; i < testLimbs.size();i++)
        {
            ProtoLimb3d temp = testLimbs.get(i);
            temp.setEndPoint(temp.endX + x, temp.endY + y, temp.endZ + z);
        }

        //cylLimb.setEndPoint(cylLimb.endX + x, cylLimb.endY + y, cylLimb.endZ + z);
    }

    private  void updateCamera(PerspectiveCamera camera, float direction, float declination, float speed)
    {
        //calculate distance on plane
        float distance = (float) Math.cos(Math.toRadians(declination));
        float tempx = camera.position.x + (float) Math.sin(Math.toRadians(direction) )*distance * speed;
        float tempz = camera.position.z + (float) Math.cos(Math.toRadians(direction))*distance * speed;
        if(speed != 0)
        {
            //boolean collide = false;
            CollisionVector.CollisionType collide = CollisionVector.CollisionType.Nothing;
//            if(cylLimb.checkCollision((int)tempx, (int)camera.position.y, (int)tempz))
//            {
//                collide = CollisionVector.CollisionType.Solid;
//            }

            for(int i = 0; i < _3dObjects.size(); i++)
            {
                collide = Plane3D.checkCollision((int)tempx, (int)camera.position.y, (int)tempz, _3dObjects.get(i).spaceOccupied, collide);
            }

            if(collide == CollisionVector.CollisionType.Nothing)
                camera.position.set(tempx, camera.position.y, tempz);

            //Increase Y if ladder collision
            if(collide == CollisionVector.CollisionType.Ladder)
            {
                float tempY = camera.position.y + gravity + 0.25f;
//                if(cylLimb.checkCollision((int)camera.position.x, (int)tempY, (int)camera.position.z))
//                    collide = CollisionVector.CollisionType.Solid;
                for(int i = 0; i < _3dObjects.size(); i++)
                    collide = Plane3D.checkCollision((int)camera.position.x, (int)tempY, (int)camera.position.z, _3dObjects.get(i).spaceOccupied, collide);
                if(collide == CollisionVector.CollisionType.Ladder || collide == CollisionVector.CollisionType.Nothing)
                    camera.position.set(camera.position.x, tempY, camera.position.z);
            }
        }

        //calculate vertical position
        float tempy = camera.position.y + (float) Math.sin(Math.toRadians(declination));
        float tempD = (float)Math.sin(Math.toRadians(declination));
        tempx = camera.position.x + (float) Math.sin(Math.toRadians(direction));
        tempz = camera.position.z + (float) Math.cos(Math.toRadians(direction));
        camera.up.set(Vector3.Y);
        camera.lookAt(tempx, tempy, tempz);
    }

    public boolean mouseClick(List<MouseClick> clicks) {
        //setCamera = camera;
        if(xPlus.mouseClick(clicks))
        {
            updateLimbs(0.1f, 0, 0);
        }
        if(xMinus.mouseClick(clicks))
        {
            updateLimbs(-0.1f, 0, 0);
        }
        if(yPlus.mouseClick(clicks))
        {
            updateLimbs(0, 0.1f, 0);
        }
        if(yMinus.mouseClick(clicks))//roll
        {
            updateLimbs(0,-0.1f, 0);
        }
        if(zPlus.mouseClick(clicks))//declination
        {
            updateLimbs(0,0, 0.1f);
        }
        if(zMinus.mouseClick(clicks))
        {
            updateLimbs(0,0, -0.1f);
        }

        if(rotPlus.mouseClick(clicks))
        {
            camAngle += 10;
            if(camAngle >= 360 ) camAngle -= 360;
            updateCamera(camera, camAngle, declination, 0);
        }
        if(rotMinus.mouseClick(clicks))
        {
            camAngle -= 10;
            if(camAngle < 0 ) camAngle += 360;
            updateCamera(camera, camAngle, declination, 0);
        }

        if(movForward.mouseClick(clicks))
        {
            updateCamera(camera, camAngle, declination, 1);
        }
        if(movReverse.mouseClick(clicks))
        {
            updateCamera(camera, camAngle, declination, -1);
        }

        if(declMinus.mouseClick(clicks))
        {
            declination --;
            if(declination < -45)
                declination = -45;
            updateCamera(camera, camAngle, declination, 0);
        }

        if(declPlus.mouseClick(clicks))
        {
            declination ++;
            if(declination > 45)
                declination = 45;
            updateCamera(camera, camAngle, declination, 0);
        }

        return  false;
    }

    @Override
    public void mouseRelease() {

    }

    @Override
    public boolean checkClicks(List<MouseClick> clicks) {
//        controlStick.checkClicks(clicks);
//        if((controlStick.Direction <45 || controlStick.Direction > 315) && controlStick.Velocity > 50 //&& setCamera != null
//        )
//        {
//            updateCamera(this.camera, camAngle, declination, 1);
//        }
//        if((controlStick.Direction > 135 && controlStick.Direction < 225) && controlStick.Velocity > 50 //&& setCamera != null
//        )
//        {
//            updateCamera(this.camera, camAngle, declination, -1);
//        }
//
//        if((controlStick.Direction > 45 && controlStick.Direction < 135) && controlStick.Velocity > 50 //&& setCamera != null
//        )
//        {
//            camAngle -= 10;
//            if(camAngle < 0) camAngle += 360;
//            updateCamera(this.camera, camAngle, declination, 0);
//        }
//
//        if((controlStick.Direction > 225 && controlStick.Direction < 315) && controlStick.Velocity > 50 // && setCamera != null
//        )
//        {
//            camAngle += 10;
//            if(camAngle > 360) camAngle -= 360;
//            updateCamera(this.camera, camAngle, declination, 0);
//        }
        doGravity();
        return false;
    }


    private void doGravity()
    {
        //gravity check not adjacent to ladder:
        CollisionVector.CollisionType collideDown = CollisionVector.CollisionType.Nothing;
        CollisionVector.CollisionType collideN = CollisionVector.CollisionType.Nothing;
        CollisionVector.CollisionType collideS = CollisionVector.CollisionType.Nothing;
        CollisionVector.CollisionType collideW = CollisionVector.CollisionType.Nothing;
        CollisionVector.CollisionType collideE = CollisionVector.CollisionType.Nothing;
        CollisionVector.CollisionType collideNW = CollisionVector.CollisionType.Nothing;
        CollisionVector.CollisionType collideSW = CollisionVector.CollisionType.Nothing;
        CollisionVector.CollisionType collideNE = CollisionVector.CollisionType.Nothing;
        CollisionVector.CollisionType collideSE = CollisionVector.CollisionType.Nothing;

        float tempY = camera.position.y - gravity;
//        if(cylLimb.checkCollision((int)camera.position.x, (int)tempY, (int)camera.position.z))
//            collideDown = CollisionVector.CollisionType.Solid;
        for(int i = 0; i < _3dObjects.size(); i++)
        {
            collideDown = Plane3D.checkCollision((int)camera.position.x, (int)tempY, (int)camera.position.z, _3dObjects.get(i).spaceOccupied, collideDown);
            collideN = Plane3D.checkCollision((int)camera.position.x, (int)camera.position.y, (int)(camera.position.z + 1f), _3dObjects.get(i).spaceOccupied, collideN);
            collideNW = Plane3D.checkCollision((int)(camera.position.x - 1f), (int)camera.position.y, (int)(camera.position.z + 1f), _3dObjects.get(i).spaceOccupied, collideNW);
            collideNE = Plane3D.checkCollision((int)(camera.position.x + 1f), (int)camera.position.y, (int)(camera.position.z + 1f), _3dObjects.get(i).spaceOccupied, collideNE);
            collideW = Plane3D.checkCollision((int)(camera.position.x - 1f), (int)camera.position.y, (int)camera.position.z, _3dObjects.get(i).spaceOccupied, collideW);
            collideE = Plane3D.checkCollision((int)(camera.position.x + 1f), (int)camera.position.y, (int)camera.position.z, _3dObjects.get(i).spaceOccupied, collideE);
            collideS = Plane3D.checkCollision((int)camera.position.x, (int)camera.position.y, (int)(camera.position.z - 1f), _3dObjects.get(i).spaceOccupied, collideS);
            collideSW = Plane3D.checkCollision((int)(camera.position.x - 1f), (int)camera.position.y, (int)(camera.position.z - 1f), _3dObjects.get(i).spaceOccupied, collideSW);
            collideSE = Plane3D.checkCollision((int)(camera.position.x + 1f), (int)camera.position.y, (int)(camera.position.z - 1f), _3dObjects.get(i).spaceOccupied, collideSE);
        }

        if(collideDown == CollisionVector.CollisionType.Nothing && collideN != CollisionVector.CollisionType.Ladder &&  collideNE != CollisionVector.CollisionType.Ladder
                && collideNW != CollisionVector.CollisionType.Ladder && collideW!= CollisionVector.CollisionType.Ladder && collideE != CollisionVector.CollisionType.Ladder
                && collideS != CollisionVector.CollisionType.Ladder &&  collideSE != CollisionVector.CollisionType.Ladder && collideSW != CollisionVector.CollisionType.Ladder
            //&& tempY > 1
        )
            camera.position.set(camera.position.x, tempY, camera.position.z);

        //calculate vertical position
        float tempy = camera.position.y + (float) Math.sin(Math.toRadians(declination));
        float tempD = (float)Math.sin(Math.toRadians(declination));
        float tempx = camera.position.x + (float) Math.sin(Math.toRadians(camAngle));
        float tempz = camera.position.z + (float) Math.cos(Math.toRadians(camAngle));
        camera.up.set(Vector3.Y);
        camera.lookAt(tempx, tempy, tempz);
    }

    @Override
    public boolean render(RenderContainer rc) {
        if(Gdx.input.isKeyPressed(Input.Keys.UP)){
            updateCamera(camera, camAngle, declination, 1);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            updateCamera(this.camera, camAngle, declination, -1);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT))
        {
            camAngle += 10;
            if(camAngle >= 360 ) camAngle -= 360;
            updateCamera(camera, camAngle, declination, 0);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT))
        {
            camAngle -= 10;
            if(camAngle < 0 ) camAngle += 360;
            updateCamera(camera, camAngle, declination, 0);
        }


        //controlStick.render(rc);
        xPlus.render(rc);
        xMinus.render(rc);
        yPlus.render(rc);
        yMinus.render(rc);
        zPlus.render(rc);
        zMinus.render(rc);

        //text.add(new DrawText(Color.BLACK, 0, 300, controlStick.Direction+""));
//        rc.text.add(new DrawText(Color.BLACK, 0, 300,  "Pitch:" + cylLimb.pitchVal));
//        rc.text.add(new DrawText(Color.BLACK, 0, 360,  "Yaw:" + cylLimb.yawVal+""));

        declPlus.render(rc);
        declMinus.render(rc);

        rotPlus.render(rc);
        rotMinus.render(rc);
        movForward.render(rc);
        movReverse.render(rc);

        //cylLimb.render(rc);

        for(int i = 0; i < testLimbs.size();i++)
        {
            testLimbs.get(i).render(rc);
        }

        //for(int i = 0; i < walls.size(); i++)
        for(int i = 0; i < _3dObjects.size();i++)
        {
            //modelInstances.add(walls.get(i));
            rc.modelInstances.add(_3dObjects.get(i).modelInstance);
        }
        //modelInstances.add(wallInstance);

        mouseClick(rc.clicks);
        checkClicks(rc.clicks);
        return false;
    }

    @Override
    public void dispose() {
        for(int i = testLimbs.size(); i >= 0;i--)
        {
            testLimbs.get(i).dispose();
            testLimbs.remove(i);
        }
    }

}
