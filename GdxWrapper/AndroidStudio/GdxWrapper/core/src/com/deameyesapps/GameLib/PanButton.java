package com.deameyesapps.GameLib;

import java.util.List;

public class PanButton extends GameObject {
    @Override
    public void mouseRelease() {

    }

    @Override
    public boolean checkClicks(List<MouseClick> clicks) {
        return false;
    }

    @Override
    public  boolean render(RenderContainer rc)
    {
        return false;
    }

    @Override
    public void dispose() {

    }
}
