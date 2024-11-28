package com.deameyesapps.ProjectConversion;

import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.deameyesapps.GameLib.GameMode;
import com.deameyesapps.GameLib.MouseClick;
import com.deameyesapps.GameLib.RenderContainer;

import java.util.List;

public class ProjectConversionMode extends GameMode {

    //todo:
    // 0: Extract Zip folder specified
    // 1: Copy files from static source directory to extracted directory
    // 2: Specify Path to zip
    // 3: Specify Path to source location
    // 4: Copy assets to specified location

    @Override
    public void mouseRelease() {

    }

    @Override
    public boolean checkClicks(List<MouseClick> clicks) {
        return false;
    }

    @Override
    public boolean render(RenderContainer renderContainer) {
        return  false;
    }

    @Override
    public void dispose() {

    }
}
