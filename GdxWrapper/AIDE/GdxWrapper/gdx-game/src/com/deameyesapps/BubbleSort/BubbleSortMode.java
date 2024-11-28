package com.deameyesapps.BubbleSort;
import com.deameyesapps.GameLib.*;
import java.util.*;
import com.badlogic.gdx.graphics.*;

public class BubbleSortMode extends GameMode
{
	List<TestTube> tubes = new ArrayList<TestTube>();
	
	public BubbleSortMode()
	{
		TestTube temp1 = new TestTube();
		temp1.x = 200; temp1.y = 200;
		temp1.bubbles.push(new Bubble(Color.BLUE));
		temp1.bubbles.push(new Bubble(Color.BLUE));
		temp1.bubbles.push(new Bubble(Color.BLUE));
		temp1.bubbles.push(new Bubble(Color.RED));
		TestTube temp2 = new TestTube();
		temp2.x = 400; temp2.y = 200;
		temp2.bubbles.push(new Bubble(Color.RED));
		temp2.bubbles.push(new Bubble(Color.RED));
		temp2.bubbles.push(new Bubble(Color.RED));
		temp2.bubbles.push(new Bubble(Color.BLUE));		
		TestTube temp3 = new TestTube();
		temp3.x = 600; temp3.y = 200;
		TestTube temp4 = new TestTube();
		temp4.x = 800; temp4.y = 200;
		
		tubes.add(temp1);
		tubes.add(temp2);
		tubes.add(temp3);
		tubes.add(temp4);
	}

	@Override
	public void dispose()
	{
		// TODO: Implement this method
	}

	@Override
	public void mouseRelease()
	{
		// TODO: Implement this method
	}

	@Override
	public boolean checkClicks(List<MouseClick> clicks)
	{
		return false;
	}

	@Override
	public boolean render(RenderContainer rc)
	{
		for(TestTube tube: tubes)
		{
			tube.render(rc);
			tube.checkClicks(rc.clicks);
		}
		return false;
	}
}
