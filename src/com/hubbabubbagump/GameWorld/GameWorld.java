package com.hubbabubbagump.GameWorld;

import com.hubbabubbagump.GameObjects.BearCopter;

public class GameWorld {
	
	private BearCopter bear;
	
	public GameWorld(int midPointY) {
		//Initializes the bear;
		bear = new BearCopter(33, midPointY-5, 17, 12); //sets bird location to x = 33, y = midpoint of y - 5
	}
	
	public void update(float delta) {
		bear.update(delta);
	}
	
	public BearCopter getBear() {
		return bear;
	}
}
