package com.deameyesapps.threeDCore;

import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.deameyesapps.GameLib.DrawShape;
import com.deameyesapps.GameLib.DrawText;
import com.deameyesapps.GameLib.GameMode;
import com.deameyesapps.GameLib.MouseClick;

import java.util.List;

public class threeDCore extends GameMode {
    public threeDCore()
    {
//        // we can pretend to be Android while running on desktop to test mobile features
//        // such as mobile-specific input
//        //platform = Application.ApplicationType.Android;
//        Assets assets = new Assets();
//        assets.loadAll();
//        physics = new Physics();
//        initializeSubModules();
//        frame = 0;
//        inst = this;
//        if (isClient()) {
//            view = new View();
//            new Particles();
//            input = new Input();
//            inputMulti = new InputMultiplexer();
//            inputMulti.addProcessor(View.inst.hud.stage);
//            inputMulti.addProcessor(input);
//            if (isMobile()) {
//                inputMulti.addProcessor(GestureHandler.createGestureHandler());
//            }
//            Gdx.input.setInputProcessor(inputMulti);
//        }
//        LevelBuilder.createLevel();
//
//        setupNetwork();
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

    }

    @Override
    public void dispose() {

    }
}
