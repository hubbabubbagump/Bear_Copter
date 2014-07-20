package com.hubbabubbagump.GameObjects;

public class Grass extends Scrollable {
	
	//resets the Grass class
	public Grass(float x, float y, int width, int height, float scrollSpeed) {
		super(x, y, width, height, scrollSpeed);
	}
	
	public void restart(float x, float scrollSpeed) {
		position.x = x;
		velocity.x = scrollSpeed;
	}
	
	public float getY() {
		return position.y; //returns the y coordinate of where the grass should be
	}
	
	 public float getX() {
	     return position.x;
	 }
	 
	 public void reverse(boolean high) {
		 velocity.x = -velocity.x;
	 }

}
