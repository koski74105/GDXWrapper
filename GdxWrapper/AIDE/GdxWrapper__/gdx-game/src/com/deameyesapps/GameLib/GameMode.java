package com.deameyesapps.GameLib;

public abstract class GameMode extends GameObject {
    public boolean ChangeMode = false;
    public GameMode newMode;
    public abstract void dispose();
}
