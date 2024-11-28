package com.deameyesapps.GameLib;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;

import java.util.ArrayList;
import java.util.List;

public class AppCore implements ApplicationListener {
    boolean androidBuild = false;
    SpriteBatch batch;
    ShapeRenderer shapeRenderer;
    int locX;
    int locY;
    GameMode mode;
    BitmapFont font;

    public static PerspectiveCamera camera;
    private ModelBatch modelBatch;
    private Environment environment;

    public  AppCore(boolean androidBuild)
    {
        this.androidBuild = androidBuild;
    }

    public  void create(GameMode newMode)
    {
        create();
        mode=newMode;
    }

    @Override
    public void create() {
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();

        locX = 200;
        locY = 200;
        font = new BitmapFont();
        //font.getData().setScale(4);
        font.getData().scaleX = 4;
        font.getData().scaleY = 4;

        // Create camera sized to screens width/height with Field of View of 75 degrees
        camera = new PerspectiveCamera(75, Gdx.graphics.getWidth(),
                Gdx.graphics.getHeight());

        // Move the camera 3 units back along the z-axis and look at the origin
        camera.position.set(0f,1f,0f);
        camera.up.set(Vector3.Y);
        camera.lookAt(0f,1f,1f);

        // Near and Far (plane) represent the minimum and maximum ranges of the camera in, um, units
        camera.near = 0.1f;
        camera.far = 300.0f;

        // A ModelBatch is like a SpriteBatch, just for models.  Use it to batch up geometry for OpenGL
        modelBatch = new ModelBatch();

        // Finally we want some light, or we wont see our color.  The environment gets passed in during
        // the rendering process.  Create one, then create an Ambient ( non-positioned, non-directional ) light.
        environment = new Environment();
        //environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.8f, 0.8f, 0.8f, 1.0f));
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.5f, 0.5f, 0.5f, 1.0f));
        environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));
    }

    public  AppCore()
    {
        create();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void render() {
        // You've seen all this before, just be sure to clear the GL_DEPTH_BUFFER_BIT when working in 3D
        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        // When you change the camera details, you need to call update();
        // Also note, you need to call update() at least once.
        camera.update();

        List<DrawShape> LowerDraw = new ArrayList<DrawShape>();
        List<DrawText> TextDraw = new ArrayList<DrawText>();
        List<DrawShape> UpperDraw = new ArrayList<DrawShape>();
        List<ModelInstance> modelInstances = new ArrayList<ModelInstance>();
        List<MouseClick> clicks = new ArrayList<MouseClick>();
        boolean clicked = false;

        if(mode != null)
        {
            RenderContainer rc = new RenderContainer();
            rc.camera = camera;
            rc.modelInstances = modelInstances;
            rc.LowerDraw = LowerDraw;
            rc.text = TextDraw;
            rc.upperDraw = UpperDraw;


            for (int i = 0; i < 20; i++)
            {
                if (Gdx.input.isTouched(i))
                {
                    clicked = true;
                    locX =  Gdx.input.getX(i);
                    locY = Gdx.graphics.getHeight() - Gdx.input.getY(i);
                    clicks.add(new MouseClick(Gdx.input.getX(i), Gdx.graphics.getHeight() - Gdx.input.getY(i), i));
                }
            }

            rc.clicks = clicks;
            mode.render(rc);
        }

        if(modelInstances.size() > 0)
        {
            // Like spriteBatch, just with models!  pass in the box Instance and the environment
            modelBatch.begin(camera);
            for(int i = 0;i < modelInstances.size();i++)
            {
                modelBatch.render(modelInstances.get(i), environment);
            }
            modelBatch.end();
        }

        batch.begin();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        for(int i = 0; i < LowerDraw.size();i++)
        {
            DrawShape ds = LowerDraw.get(i);
            shapeRenderer.setColor(ds.color);
            if(ds.shape == DrawShape.Shape.CIRCLE)
                shapeRenderer.circle(ds.shapeArgs[0], ds.shapeArgs[1], ds.shapeArgs[2]);
            if(ds.shape == DrawShape.Shape.RECTANGLE)
                shapeRenderer.rect(ds.shapeArgs[0], ds.shapeArgs[1], ds.shapeArgs[2], ds.shapeArgs[3]);
            if(ds.shape == DrawShape.Shape.LINE)
            {
                shapeRenderer.rectLine(ds.shapeArgs[0], ds.shapeArgs[1], ds.shapeArgs[2], ds.shapeArgs[3], ds.shapeArgs[4]);
                //shapeRenderer.line(ds.shapeArgs[0], ds.shapeArgs[1], ds.shapeArgs[2], ds.shapeArgs[3]);
            }
            if(ds.shape == DrawShape.Shape.TRIANGLE)
                shapeRenderer.triangle(ds.shapeArgs[0], ds.shapeArgs[1],ds.shapeArgs[2],ds.shapeArgs[3],ds.shapeArgs[4],ds.shapeArgs[5]);
        }
        if(TextDraw.size() > 0)
        {
            shapeRenderer.end();
            SpriteBatch spriteBatch = new SpriteBatch();
            spriteBatch.begin();
            for(int i = 0; i < TextDraw.size(); i++)
            {
                DrawText dt = TextDraw.get(i);
                font.setColor(dt.color);
                font.draw(spriteBatch, dt.Text, dt.X, dt.Y);
            }
            spriteBatch.end();
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        }
        for(int i = 0; i < UpperDraw.size();i++)
        {
            DrawShape ds = UpperDraw.get(i);
            shapeRenderer.setColor(ds.color);
            if(ds.shape == DrawShape.Shape.CIRCLE)
            {
                shapeRenderer.circle(ds.shapeArgs[0], ds.shapeArgs[1], ds.shapeArgs[2]);
            }
            if(ds.shape == DrawShape.Shape.RECTANGLE)
            {
                shapeRenderer.rect(ds.shapeArgs[0], ds.shapeArgs[1], ds.shapeArgs[2], ds.shapeArgs[3]);
            }
            if(ds.shape == DrawShape.Shape.LINE)
            {
                shapeRenderer.line(ds.shapeArgs[0], ds.shapeArgs[1], ds.shapeArgs[2], ds.shapeArgs[3]);
            }
        }
        shapeRenderer.end();
        batch.end();

        if(mode.ChangeMode)
            mode = mode.newMode;
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        modelBatch.dispose();
        mode.dispose();
    }
}
