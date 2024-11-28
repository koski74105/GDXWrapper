package com.deameyesapps.GameLib;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector3;
import java.util.ArrayList;
import java.util.List;

public class ProtoLimb3d extends GameObject{
    private Model baseRound;
    private Model midRound;
    private Model endRound;

    private ModelInstance baseInstance;
    private ModelInstance endInstance;

    private List<ModelInstance> betweenModels;

    private float baseX;
    private float baseY;
    private float baseZ;
    public float endX;
    public float endY;
    public float endZ;
    private  float maxDistance;
    private  Material material;

    public  ProtoLimb3d()
    {
        betweenModels = new ArrayList<ModelInstance>();
    }

    public ProtoLimb3d(Color color, float origX, float origY, float origZ, float endX, float endY, float endZ, float diameter, float maxDistance)
    {
        this();
        this.endX = endX;
        this.endY = endY;
        this.endZ = endZ;
        this.maxDistance = maxDistance;
        ModelBuilder modelBuilder = new ModelBuilder();
        material = new Material(ColorAttribute.createDiffuse(color));
        baseRound = modelBuilder.createSphere(diameter,diameter, diameter, 12, 12,
                material,
                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);



        midRound = modelBuilder.createSphere(diameter,diameter, diameter, 12, 12,
                material,
                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);

        endRound = modelBuilder.createSphere(diameter,diameter, diameter, 12, 12,
                material,
                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);

        baseInstance = new ModelInstance(baseRound, origX, origY, origZ);
        endInstance = new ModelInstance(endRound, endX, endY, endZ);
        addMidPoints();
    }

    public ProtoLimb3d(String texture, float origX, float origY, float origZ, float endX, float endY, float endZ, float diameter, float maxDistance)
    {
        this();
        this.endX = endX;
        this.endY = endY;
        this.endZ = endZ;
        this.maxDistance = maxDistance;
        ModelBuilder modelBuilder = new ModelBuilder();

        Texture _texture = new Texture(texture);
        _texture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        _texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        material = new Material(new Material(TextureAttribute.createDiffuse(_texture)));

        baseRound = modelBuilder.createSphere(diameter,diameter, diameter, 12, 12,
                material,
                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);



        midRound = modelBuilder.createSphere(diameter,diameter, diameter, 12, 12,
                material,
                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);

        endRound = modelBuilder.createSphere(diameter,diameter, diameter, 12, 12,
                material,
                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);

        baseInstance = new ModelInstance(baseRound, origX, origY, origZ);
        endInstance = new ModelInstance(endRound, endX, endY, endZ);
        addMidPoints();
    }

    public ProtoLimb3d(Material material, float origX, float origY, float origZ, float endX, float endY, float endZ, float diameter, float maxDistance)
    {
        this();
        this.endX = endX;
        this.endY = endY;
        this.endZ = endZ;
        this.maxDistance = maxDistance;
        ModelBuilder modelBuilder = new ModelBuilder();

        this.material = material;

        baseRound = modelBuilder.createSphere(diameter,diameter, diameter, 12, 12,
                material,
                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);

        midRound = modelBuilder.createSphere(diameter,diameter, diameter, 12, 12,
                material,
                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);

        endRound = modelBuilder.createSphere(diameter,diameter, diameter, 12, 12,
                material,
                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);

        baseInstance = new ModelInstance(baseRound, origX, origY, origZ);
        endInstance = new ModelInstance(endRound, endX, endY, endZ);
        addMidPoints();
    }

    public void setEndPoint(float x, float y, float z)
    {
        endX = x;
        endY = y;
        endZ = z;
        endInstance.transform.setToTranslation(new Vector3(endX, endY, endZ));
        addMidPoints();
    }

    public void addMidPoints()
    {
        betweenModels.clear();
        betweenModels.add(baseInstance);
        betweenModels.add(endInstance);

        boolean filledGaps = false;
        //while filledGaps has more than a diameter between
        //points iterate through list and insert a point in between
        //points

        while(!filledGaps)
        {
            boolean gapFound = false;
            for(int i = 0; i < betweenModels.size()-1; i++)
            {
                ModelInstance temp1 = betweenModels.get(i);
                ModelInstance temp2 = betweenModels.get(i + 1);

                Vector3 position1;
                position1 = temp1.transform.getTranslation(new Vector3());
                Vector3 position2;
                position2 = temp2.transform.getTranslation(new Vector3());
                if(getDistance3d(position1.x, position1.y, position1.z, position2.x, position2.y, position2.z) > maxDistance)
                {
                    gapFound = true;
                    betweenModels.add(i+1, new ModelInstance(midRound,
                            (position1.x + position2.x) /2,
                            (position1.y + position2.y) /2,
                            (position1.z + position2.z) /2));
                }
            }
            if(!gapFound)
                filledGaps = true;
        }
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
    public void render(PerspectiveCamera camera, List<ModelInstance> modelInstances, List<DrawShape> LowerDraw, List<DrawText> text, List<DrawShape> upperDraw) {
        for(int i = 0; i < betweenModels.size(); i++)
        {
            modelInstances.add(betweenModels.get(i));
        }
    }

    @Override
    public void dispose() {
        endRound.dispose();
        baseRound.dispose();
        midRound.dispose();
    }
}
