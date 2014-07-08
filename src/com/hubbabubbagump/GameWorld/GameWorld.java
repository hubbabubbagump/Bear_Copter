package com.hubbabubbagump.GameWorld;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.hubbabubbagump.GameObjects.BearCopter;
import com.hubbabubbagump.GameObjects.ScrollHandler;

public class GameWorld {
	
	private BearCopter bear;
	private ScrollHandler scroller;
	
	private Rectangle ground;
	private Rectangle ceiling;
	
	public GameWorld(int midPointY) {
		//Initializes the bear;
		bear = new BearCopter(33, midPointY-5, 17, 12); //sets bear location to x = 33, y = midpoint of y - 5, initializes bear
		
		//creates new scrollHandlers, number sets position of grass
		scroller = new ScrollHandler(midPointY + 66);
		ground = new Rectangle(0, midPointY + 66, 126, 20);
		ceiling = new Rectangle(0, -20, 126, 20);
		
	}
	
	public void update(float delta) {
		bear.update(delta);
		scroller.update(delta);
		
		if(scroller.collides(bear)) {
			if(bear.Alive()) {
				//Game Over
				scroller.stop();
				bear.dead();
			}
		}
		
		if(Intersector.overlaps(bear.getBoundingCircle(), ground)) {
			scroller.stop();
			bear.dead();
			bear.deadAcceleration();
		}
		if(Intersector.overlaps(bear.getBoundingCircle(), ceiling)) {
			scroller.stop();
			bear.dead();
	
		}
		
		
	}
	
	public BearCopter getBear() {
		return bear;
	}
	
	public ScrollHandler getScroller() {
		return scroller;
	}
			
}
