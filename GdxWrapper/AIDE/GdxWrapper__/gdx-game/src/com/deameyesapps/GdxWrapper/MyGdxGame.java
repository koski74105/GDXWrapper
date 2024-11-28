package com.deameyesapps.GdxWrapper;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;


import com.badlogic.gdx.ApplicationAdapter;
import com.deameyesapps.GameLib.AppCore;
import com.deameyesapps.Gallery.IntroMenuMode;

public class MyGdxGame extends ApplicationAdapter
{
	AppCore appCore = new AppCore(true);

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

/*
public class MyGdxGame implements ApplicationListener
{
	Texture texture;
	SpriteBatch batch;

	@Override
	public void create()
	{
		texture = new Texture(Gdx.files.internal("android.jpg"));
		batch = new SpriteBatch();
	}

	@Override
	public void render()
	{        
	    Gdx.gl.glClearColor(1, 1, 1, 1);
	    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(texture, Gdx.graphics.getWidth() / 4, 0, 
				   Gdx.graphics.getWidth() / 2, Gdx.graphics.getWidth() / 2);
		batch.end();
	}

	@Override
	public void dispose()
	{
	}

	@Override
	public void resize(int width, int height)
	{
	}

	@Override
	public void pause()
	{
	}

	@Override
	public void resume()
	{
	}
}
*/
