package com.deameyesapps.GameLib;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.model.MeshPart;
import com.badlogic.gdx.graphics.g3d.model.Node;
import com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;
import java.util.List;

public class ModelShortCut {
    public static List<Vector3> getMidPoints(Vector3 startPoint, Vector3 endPoint)
    {
        List<Vector3> betweenModels = new ArrayList<Vector3>();
        betweenModels.add(startPoint);
        betweenModels.add(endPoint);

        boolean filledGaps = false;
        //while filledGaps has more than a diameter between
        //points iterate through list and insert a point in between
        //points

        while(!filledGaps)
        {
            boolean gapFound = false;
            for(int i = 0; i < betweenModels.size()-1; i++)
            {
                Vector3 position1 = betweenModels.get(i);
                Vector3 position2 = betweenModels.get(i + 1);
                if(GameObject.getDistance3d(position1.x, position1.y, position1.z, position2.x, position2.y, position2.z) > 0.5)
                {
                    gapFound = true;
                    betweenModels.add(i+1, new Vector3(
                            (position1.x + position2.x) /2,
                            (position1.y + position2.y) /2,
                            (position1.z + position2.z) /2));
                }
            }
            if(!gapFound)
                filledGaps = true;
        }
        return betweenModels;
    }

    public static List<ModelSpaceContainer> createLevel() {
        // graphical representation of the ground
        float groundPieceSize = 100f;

        Array<Model> models = new Array<Model>();
        List<ModelSpaceContainer> groundPieces = new ArrayList<ModelSpaceContainer>();

        ModelBuilder mb = new ModelBuilder();
        mb.begin();
        Vector3 bl = new Vector3();
        Vector3 tl = new Vector3();
        Vector3 tr = new Vector3();
        Vector3 br = new Vector3();
        Vector3 norm = new Vector3(0f, 1f, 0f);
        // the size of each rect that makes up the ground
        //Texture groundTex = Assets.manager.get("textures/ground1.jpg", Texture.class);
        Texture groundTex = new Texture("textures/ground1.jpg");
        Material groundMat = new Material(TextureAttribute.createDiffuse(groundTex));
        MeshPartBuilder mpb = mb.part("ground", GL20.GL_TRIANGLES,
                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal | VertexAttributes.Usage.TextureCoordinates, groundMat);
        float u1 = 0f;
        float v1 = 0f;
        float u2 = 1;
        float v2 = 1;
        mpb.setUVRange(u1, v1, u2, v2);
        bl.set(0, 0, 0);
        tl.set(0, 0, groundPieceSize);
        tr.set(groundPieceSize, 0, groundPieceSize);
        br.set(groundPieceSize, 0, 0);
        int divisions = ((int) groundPieceSize) / 4;
        mpb.patch(bl, tl, tr, br, norm, divisions, divisions);
        Model groundModel = mb.end();
        models.add(groundModel);
        groundPieces.clear();
        for (int x = 0; x < 100; x += groundPieceSize)
        {
            for (int z = 0; z < 100; z += groundPieceSize)
            {
                ModelInstance groundPiece = new ModelInstance(groundModel);
                groundPiece.transform.setToTranslation(x, 0f, z);
                //groundPieces.add(groundPiece);
                groundPieces.add(new ModelSpaceContainer(
                        groundPiece,
                        Plane3D.calculateSpaceOccupied(tl, tr, br, bl, x,0, z)
                ));
            }
        }

        return  groundPieces;
    }

    public static ModelSpaceContainer createBoxes(int count) {
        Quaternion q = new Quaternion();
        Vector3 tmp = new Vector3();
        ModelBuilder main = new ModelBuilder();
        ModelBuilder mb = new ModelBuilder();
        Material material = new Material();
        material.set(TextureAttribute.createDiffuse(new Texture("textures/marble.jpg")));

        List<CollisionVector> spaceOccupied = new ArrayList<CollisionVector>();

        main.begin();
        for (int i = 0; i < count; i++) {
            float w = 8f;
            float d = 8f;
            float h = (i+1)*5f;
            //tmp.set(10f + (w+2) * i, 0f, 10f + (d+2) * i);
            tmp.set(10f + (w+2) * i, h/2, 10f + (d+2) * i);
            mb.begin();
            MeshPartBuilder mpb = mb.part("part-" + i, GL20.GL_TRIANGLES,
                    VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal | VertexAttributes.Usage.TextureCoordinates, material);
            mpb.box(w, h, d);

            Model boxModel = mb.end();
            Node node = main.node("box-" + i, boxModel);
            node.translation.set(tmp);
            q.idt();
            node.rotation.set(q);
            //upper
            Vector3 vu1 = new Vector3(w/2,h/2+ 0.5f,d/2);
            Vector3 vu2 = new Vector3(w/2,h/2+ 0.5f,-d/2);
            Vector3 vu3 = new Vector3(-w/2,h/2+ 0.5f,-d/2);
            Vector3 vu4 = new Vector3(-w/2,h/2+ 0.5f,d/2);

            //lower
            Vector3 vl1 = new Vector3(w/2,-h/2,d/2);
            Vector3 vl2 = new Vector3(w/2,-h/2,-d/2);
            Vector3 vl3 = new Vector3(-w/2,-h/2,-d/2);
            Vector3 vl4 = new Vector3(-w/2,-h/2,d/2);
            //top
            spaceOccupied.addAll(
                Plane3D.calculateSpaceOccupied(vu1, vu2, vu3, vu4, (int)tmp.x,(int)tmp.y,(int)tmp.z, CollisionVector.CollisionType.Ladder)
            );
            //bottom
            spaceOccupied.addAll(
                Plane3D.calculateSpaceOccupied(vl1, vl2, vl3, vl4, (int)tmp.x,(int)tmp.y,(int)tmp.z)
            );
            //sides
            spaceOccupied.addAll(
                    Plane3D.calculateSpaceOccupied(vu1, vu2, vl2, vl1, (int)tmp.x,(int)tmp.y,(int)tmp.z, CollisionVector.CollisionType.Ladder)
            );

            spaceOccupied.addAll(
                Plane3D.calculateSpaceOccupied(vu2, vu3, vl3, vl2, (int)tmp.x,(int)tmp.y,(int)tmp.z, CollisionVector.CollisionType.Ladder)
            );
            spaceOccupied.addAll(
                Plane3D.calculateSpaceOccupied(vu3, vu4, vl4, vl3, (int)tmp.x,(int)tmp.y,(int)tmp.z, CollisionVector.CollisionType.Ladder)
            );
            spaceOccupied.addAll(
                Plane3D.calculateSpaceOccupied(vu4, vu1, vl1, vl4, (int)tmp.x,(int)tmp.y,(int)tmp.z, CollisionVector.CollisionType.Ladder)
            );
        }
        Model finalModel = main.end();
        //instance = new ModelInstance(finalModel);
        //return new ModelInstance(finalModel);
        return new ModelSpaceContainer(new ModelInstance(finalModel),
                spaceOccupied);
    }
}
