package com.deameyesapps.GdxWrapper;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;


import com.badlogic.gdx.ApplicationAdapter;
import com.deameyesapps.GameLib.AppCore;
import com.deameyesapps.Gallery.IntroMenuMode;

public class MyGdxGame implements ApplicationListener
{
	AppCore appCore = new AppCore(true);
	Texture texture;
	SpriteBatch batch;
	
	@Override
	public void create()
	{
		appCore.create(new IntroMenuMode());
	}

	@Override
	public void render()
	{
		appCore.render();		
	}

	@Override
	public void dispose()
	{
		appCore.dispose();
	}

	@Override
	public void resize(int width, int height) {}

	@Override
	public void pause() {}

	@Override
	public void resume() {}
}
