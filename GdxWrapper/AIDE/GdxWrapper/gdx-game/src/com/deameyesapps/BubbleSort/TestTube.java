package com.deameyesapps.BubbleSort;
import com.deameyesapps.GameLib.*;
import java.util.*;
import com.badlogic.gdx.graphics.*;

public class TestTube extends GameObject 
{
	public Stack<Bubble> bubbles = new Stack<Bubble>();
	public static TestTube selected = null;
	
	public int buttonTimeoutMax;
    private int buttonTimeoutVal;
	private int scale = 100;
	private int slots = 4;
	
	public TestTube()
	{
		this.buttonTimeoutMax = 25;
        this.buttonTimeoutVal = 0;
	}
	
	@Override
	public void mouseRelease() {}
	
	private void clickAction()
	{
		//select
		if(selected == null)
			selected = this;
		else //selected
		{
			if(selected == this) //deselect
				selected = null;
			else if(
						selected.bubbles.size() > 0 &&
						this.bubbles.size() < slots &&
						(
							this.bubbles.size() == 0 ||
							this.bubbles.peek().color == selected.bubbles.peek().color
						)
					)//check move
			{
				//Move
				Bubble temp = selected.bubbles.pop();
				this.bubbles.push(temp);
			}
			else
			{
				selected = null;
			}
		}
	}
	
	@Override
	public boolean checkClicks(List<MouseClick> clicks)
	{
		if(buttonTimeoutVal == 0)
		{
			for(MouseClick mc: clicks)
			{
				if(mc.X >= this.x && mc.Y >= this.y && mc.X < this.x + scale && mc.Y < this.y + (scale * slots))
				{
					clickAction();
					buttonTimeoutVal = buttonTimeoutMax;
					return false;
				}
			}
		}
		return false;
	}

	@Override
	public boolean render(RenderContainer rc)
	{
		if(buttonTimeoutVal > 0)
            buttonTimeoutVal -= 1;
		
		if(selected == this)
			rc.LowerDraw.add(new DrawShape(DrawShape.Shape.RECTANGLE, Color.GREEN, x, y, scale, scale * slots, 0, 0));
		else
			rc.LowerDraw.add(new DrawShape(DrawShape.Shape.RECTANGLE, Color.BLACK, x, y, scale, scale * slots, 0, 0));
		
		int topY = 0;
		
		for(Bubble bubble: bubbles)
		{
			rc.LowerDraw.add(new DrawShape(DrawShape.Shape.CIRCLE, bubble.color, x + (scale/2), y + topY + (scale/2), scale/2 -1, 0, 0, 0));
			topY += scale;
		}
		
		return false;
	}

	@Override
	public void dispose()
	{
		// TODO: Implement this method
	}
}
