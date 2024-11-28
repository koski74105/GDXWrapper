package com.deameyesapps.GameLib;

import java.util.ArrayList;
import java.util.List;

public class ButtonContainer extends GameObject {
    List<Button> buttons = new ArrayList<Button>();
    @Override
    public void mouseRelease() {

    }

    @Override
    public boolean checkClicks(List<MouseClick> clicks) {
        return false;
    }

    @Override
    public boolean render(RenderContainer rc) {
        for (Button btn: buttons) {
            btn.render(rc);
        }

        return false;
    }

    @Override
    public void dispose() {

    }

    public void addButton(Button button)
    {
        buttons.add(button);
    }
}
