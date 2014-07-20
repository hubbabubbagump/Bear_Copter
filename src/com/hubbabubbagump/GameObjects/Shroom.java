package com.hubbabubbagump.GameObjects;

import java.util.Random;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.hubbabubbagump.Screens.GameScreen;

public class Shroom extends Scrollable{
	
	private Circle circle;
	private Random rand;
	
	public static final int SHROOMAMOUNT = 2; //how many fruit images there are (apple, etc)
	private int shroom_number;
	
	public boolean eaten = false;
	
	public static final float RADIUS = (float) 4.5;
	
	public static final int MINIMUM_HEIGHT = 20;
	public static final int MAXIMUM_HEIGHT = (int) (GameScreen.midScreen() + 26);

	
	public Shroom(float x, float y, int width, int height, float scrollSpeed) {
		super(x, y, width, height, scrollSpeed);

		rand = new Random();
		circle = new Circle();
		
		//Creates a random number to decide which fruit is rendered
		shroom_number = rand.nextInt(SHROOMAMOUNT);
	}
	
	@Override
	public void update(float delta) {
		super.update(delta);
		
		//position.x and position.x are the coordinates for the center of the circle
		circle.set(getX(), getY(), RADIUS);

	}
	
	@Override
	public void reset(float newX) {
		super.reset(newX);
		eaten = false; //sets eaten back to false
		randomize();
		
	}
	
	public Circle getCircle() {
		return circle;
	}
	
	public int shroomNumber() {
		return shroom_number;
	}
	
	public boolean collides(BearCopter bear) {
		//checks if the wall and circle around the bear overlap
		if(!eaten) {
			return (Intersector.overlaps(bear.getBoundingCircle(), circle));
		}
		return false;
	}
	
	public void randomize() {
		shroom_number = rand.nextInt(SHROOMAMOUNT);
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
	
	
	 public void reverse(boolean high) {
		 velocity.x = -velocity.x;
		 

		 if (position.x <= 68) {
			 position.x = 136 - position.x;
		 }
		 else if (position.x > 68) {
			 position.x = 136 - position.x;
		 }
	 }


}
