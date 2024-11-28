package com.deameyesapps.GameLib;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.BlendingAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector3;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Plane3D {

    public static List<CollisionVector> calculateSpaceOccupied(Vector3 v1,
                                                               Vector3 v2,
                                                               Vector3 v3,
                                                               Vector3 v4,
                                                               int xoff, int yoff, int zoff)
    {
        return calculateSpaceOccupied(v1.x, v1.y, v1.z, v2.x, v2.y, v2.z,
                v3.x, v3.y, v3.z, v4.x, v4.y, v4.z, xoff, yoff, zoff, CollisionVector.CollisionType.Solid);
    }

    public static List<CollisionVector> calculateSpaceOccupied(Vector3 v1,
                                                       Vector3 v2,
                                                       Vector3 v3,
                                                       Vector3 v4,
                                                       int xoff, int yoff, int zoff, CollisionVector.CollisionType collisionType)
    {
        return calculateSpaceOccupied(v1.x, v1.y, v1.z, v2.x, v2.y, v2.z,
                v3.x, v3.y, v3.z, v4.x, v4.y, v4.z, xoff, yoff, zoff, collisionType);
    }

    public static List<CollisionVector> calculateSpaceOccupied(float x1, float y1, float z1,
                                                               float x2, float y2, float z2,
                                                               float x3, float y3, float z3,
                                                               float x4, float y4, float z4,
                                                               int xoff, int yoff, int zoff, CollisionVector.CollisionType collisionType)
    {
        x1 += xoff; x2 += xoff; x3 += xoff; x4 += xoff;
        y1 += yoff; y2 += yoff; y3 += yoff; y4 += yoff;
        z1 += zoff; z2 += zoff; z3 += zoff; z4 += zoff;

        //collection of all points between the specified vertices
        List<CollisionVector> retVal = new ArrayList<CollisionVector>();
        //first iteration to find all points between 2 vectors
        List<Vector3> rail1 = ModelShortCut.getMidPoints(
                new Vector3(x1, y1, z1),new Vector3(x2, y2, z2));
        List<Vector3> rail2 = ModelShortCut.getMidPoints(
                new Vector3(x3, y3, z3),new Vector3(x4, y4, z4));

        //build midpoints between Rail1 and third point
        for(int i = 0; i < rail1.size();i++)
        {
            //iterate through midoints of first 2 points and find all midpoints to third point
            //from each of those midpoints to
            List<Vector3> midPoints2 = ModelShortCut.getMidPoints(rail1.get(i),
                    new Vector3(x3,y3,z3));
            for(int j = 0; j < midPoints2.size();j++)
            {
                Vector3 temp = midPoints2.get(j);
                retVal.add(new CollisionVector((int)temp.x, (int) temp.y, (int) temp.z, collisionType));
            }
        }


        //build midpoints between Rail2 and opposing point
        for(int i = 0; i < rail2.size();i++)
        {
            //iterate through midoints and find all midpoints
            //from each of those midpoints to
            List<Vector3> midPoints2 = ModelShortCut.getMidPoints(rail2.get(i),
                    new Vector3(x1,y1,z1));
            for(int j = 0; j < midPoints2.size();j++)
            {
                Vector3 temp = midPoints2.get(j);
                retVal.add(new CollisionVector((int)temp.x, (int) temp.y, (int) temp.z, collisionType));
            }
        }

        return new ArrayList<CollisionVector>(new HashSet<CollisionVector>(retVal));
    }

//    public static boolean checkCollision(int x, int y, int z, List<CollisionVector> spaceOccupied)
//    {
//        Vector3 temp;
//        for(int i = 0; i < spaceOccupied.size(); i++)
//        {
//            temp = spaceOccupied.get(i);
//            if(temp.x == x && temp.y == y && temp.z == z)
//            {
//                return true;
//            }
//        }
//        return false;
//    }

    public static CollisionVector.CollisionType checkCollision(int x, int y, int z, List<CollisionVector> spaceOccupied
    ,CollisionVector.CollisionType prevValue)
    {
        CollisionVector temp;
        for(int i = 0; i < spaceOccupied.size(); i++)
        {
            temp = spaceOccupied.get(i);
            if(temp.x == x && temp.y == y && temp.z == z)
            {
                //return true;
                return temp.collisionType;
            }
        }
        return prevValue;
    }

    public static Model createPlane(String  texture, float x1, float y1, float z1, float x2, float y2, float z2, float x3, float y3, float z3, float x4, float y4, float z4)
    {
        return createPlane(texture, new Vector3(x1, y1, z1), new Vector3(x2, y2, z2), new Vector3(x3, y3, z3), new Vector3(x4, y4, z4));
    }

    public static Model createPlane(Color color, float x1, float y1, float z1, float x2, float y2, float z2, float x3, float y3, float z3, float x4, float y4, float z4)
    {
        return createPlane(color, new Vector3(x1, y1, z1), new Vector3(x2, y2, z2), new Vector3(x3, y3, z3), new Vector3(x4, y4, z4));
    }

    public static Model createPlane(String texture, Vector3 p1, Vector3 p2, Vector3 p3, Vector3 p4 )
    {
        Texture _texture = new Texture(texture);
        _texture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        _texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        Material material = new Material(new Material(TextureAttribute.createDiffuse(_texture)));
        material.set(new BlendingAttribute(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA));
        return  createPlane(material, p1, p2, p3, p4);
    }

    public static Model createPlane(Color color, Vector3 p1, Vector3 p2, Vector3 p3, Vector3 p4 )
    {
        Material material = new Material(ColorAttribute.createDiffuse(color));
        material.set(new BlendingAttribute(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA));
        return  createPlane(material, p1, p2, p3, p4);
    }

    public static Model createPlane(Material material, Vector3 p1, Vector3 p2, Vector3 p3, Vector3 p4 )
    {

        ModelBuilder modelBuilder = new ModelBuilder();
        MeshPartBuilder meshBuilder;
        modelBuilder.begin();
        meshBuilder = modelBuilder.part("room1", GL20.GL_TRIANGLES,
                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal |
                        VertexAttributes.Usage.TextureCoordinates, material);
        //meshBuilder.setUVRange(0,0, Gdx.graphics.getWidth()/wallTexture.getWidth(),1);
        Vector3 normal= new Vector3();

        meshBuilder.rect(p1, p2, p3, p4, normal.set(0, 0, 1));
        meshBuilder.rect(p2,p1, p4, p3, normal.set(0, 0, -1));
        return modelBuilder.end();
    }


    public static Model createFullPlane(String texture, Vector3 p1, Vector3 p2, Vector3 p3, Vector3 p4 )
    {
        ModelBuilder modelBuilder = new ModelBuilder();
        MeshPartBuilder meshBuilder;
        modelBuilder.begin();
        Texture wallTexture = new Texture(texture);
        wallTexture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        wallTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        Material material = new Material(new Material(TextureAttribute.createDiffuse(wallTexture)));
        material.set(new BlendingAttribute(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA));
        meshBuilder = modelBuilder.part("room1", GL20.GL_TRIANGLES,
                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal |
                        VertexAttributes.Usage.TextureCoordinates, material);
        meshBuilder.setUVRange(0,0, Gdx.graphics.getWidth()/wallTexture.getWidth(),1);

        Vector3 normal= new Vector3();

        meshBuilder.rect(p1,
                p2,
                p3,
                p4, normal.set(0, 0, 1));
        meshBuilder.rect(p2,p1,
                p4,
                p3, normal.set(0, 0, -1));
        return modelBuilder.end();
    }
}
