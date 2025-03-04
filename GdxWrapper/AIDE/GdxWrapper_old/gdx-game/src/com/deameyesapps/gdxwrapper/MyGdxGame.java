package com.deameyesapps.gdxwrapper;

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