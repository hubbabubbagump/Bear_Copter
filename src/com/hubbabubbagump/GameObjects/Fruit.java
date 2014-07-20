package com.hubbabubbagump.GameObjects;

import java.util.Random;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.hubbabubbagump.GameWorld.GameWorld;
import com.hubbabubbagump.Screens.GameScreen;

public class Fruit extends Scrollable{
	
	private Circle circle;
	private Circle bigCircle;
	private Random rand;
	
	public static final int FRUITAMOUNT = 3; //how many fruit images there are (apple, etc)
	private int fruit_number;
	
	public boolean eaten = false;
	
	public static final float RADIUS = (float) 4.5;
	public static final float BIG_RADIUS = (float) 11;
	public static final int MINIMUM_HEIGHT = 20;
	public static final int MAXIMUM_HEIGHT = (int) (GameScreen.midScreen() + 26);
	
	public static int BEHIND_BEAR = 25;
	
	private static boolean isHigh = false;
	
	public Fruit(float x, float y, int width, int height, float scrollSpeed) {
		super(x, y, width, height, scrollSpeed);

		rand = new Random();
		circle = new Circle();
		bigCircle = new Circle();
		
		//Creates a random number to decide which fruit is rendered
		fruit_number = rand.nextInt(FRUITAMOUNT);
	}
	
	@Override
	public void update(float delta) {
		super.update(delta);
		
			//position.x and position.x are the coordinates for the center of the circle
		circle.set(getX(), getY(), RADIUS);
		bigCircle.set(getX(), getY(), BIG_RADIUS);
		isHigh = GameWorld.isHigh();
	}
	
	@Override
	public void reset(float newX) {
		super.reset(newX);
		position.y = 0; //resets position.y to 0 to accommodate the randomized values in the constructor
		eaten = false; //sets eaten back to false
		randomize();
		
	}
	
	public Circle getCircle() {
		return circle;
	}
	
	public int fruitNumber() {
		return fruit_number;
	}
	
	public boolean collides(BearCopter bear) {
		//checks if the wall and circle around the bear overlap
		if(!eaten) {
			return (Intersector.overlaps(bear.getBoundingCircle(), circle));
		}
		return false;
	}
	
	public boolean wallCheck(Wall wall) {
		//ensures that none of the fruit will overlap with the bars.
		return (Intersector.overlaps(bigCircle, wall.getBar())); 
			
	}
	
	public void randomize() {
		fruitY = rand.nextInt(MAXIMUM_HEIGHT) + MINIMUM_HEIGHT; //randomizes height of fruit
		rand = new Random();
		fruit_number = rand.nextInt(FRUITAMOUNT);
	}
	
	public void restart(float x, float scrollSpeed) {
		velocity.x = scrollSpeed;
		reset(x);
	}
	
	public float getY() {
		return (float) (position.y + fruitY);
	}
	
	 public float getX() {
	     return (float) (position.x);
	 }
	
	public boolean isEaten() {
		return eaten;
	}
	
	public void ate() {
		//The bear has "eaten" the fruit
		eaten = true;
	}
	
	public boolean combo() {
		//if the fruit is behind bear and isn't eaten, returns true
		if(position.x <= 25  && !eaten && !isHigh) {
			return true;
		}
		else if (position.x >= 111 && !eaten && isHigh) {
			return true;
		}
		return false;
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
