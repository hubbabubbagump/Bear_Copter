package com.hubbabubbagump.GameObjects;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.hubbabubbagump.Screens.GameScreen;

public class GunFrags extends Scrollable{
	private Circle circle;
	
	public boolean eaten = false;
	
	public static final float RADIUS = (float) 4.5;
	
	public static final int MINIMUM_HEIGHT = 20;
	public static final int MAXIMUM_HEIGHT = (int) (GameScreen.midScreen() + 26);

	
	public GunFrags(float x, float y, int width, int height, float scrollSpeed) {
		super(x, y, width, height, scrollSpeed);
		circle = new Circle();
	}
	
	@Override
	public void update(float delta) {
		super.update(delta);
		
		//position.x and position.x are the coordinates for the center of the circle
		circle.set(getX() + 5, getY() + 5, RADIUS);

	}
	
	@Override
	public void reset(float newX) {
		super.reset(newX);
		eaten = false; //sets eaten back to false
		
	}
	
	public Circle getCircle() {
		return circle;
	}
	
	public boolean collides(BearCopter bear) {
		//checks if the wall and circle around the bear overlap
		if(!eaten) {
			return (Intersector.overlaps(bear.getBoundingCircle(), circle));
		}
		return false;
	}

	public void restart(float x, float y, float scrollSpeed) {
		velocity.x = scrollSpeed;
		position.y = y;
		reset(x);
	}
	
	public float getY() {
		return (float) (position.y);
	}
	
	 public float getX() {
	     return (float) (position.x);
	 }
	
	public boolean isEaten() {
		return eaten;
	}
	
	public void ate() {
		//The bear has "eaten" the shroom
		eaten = true;
	}
}
