package com.deameyesapps.GameLib;

import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.ModelInstance;

import java.util.List;

public class RenderContainer {
    public PerspectiveCamera camera;
    public List<ModelInstance> modelInstances;
    public java.util.List<DrawShape> LowerDraw;
    public List<DrawText> text;
    public List<DrawShape> upperDraw;
    public List<MouseClick> clicks;
    public List<IOEvent> iOEvents;
}