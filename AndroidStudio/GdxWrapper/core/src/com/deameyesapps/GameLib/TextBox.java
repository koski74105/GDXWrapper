package com.deameyesapps.GameLib;

import com.badlogic.gdx.graphics.PerspectiveCamera;

import java.util.List;

public class TextBox extends GameObject {
    String displayText;

    @Override
    public void mouseRelease() {

    }

    @Override
    public boolean checkClicks(List<MouseClick> clicks) {
        return false;
    }

    @Override
    public boolean render(RenderContainer rc)
    {
        return false;
    }

    @Override
    public void dispose() {

    }

    public void inKeyValue(String keyValue)
    {
        boolean commandExecuted = false;
        if(keyValue == "<CLEAR>")
        {
            displayText = "";
            commandExecuted = true;
        }

        if(!commandExecuted)
            displayText += keyValue;
    }
}
