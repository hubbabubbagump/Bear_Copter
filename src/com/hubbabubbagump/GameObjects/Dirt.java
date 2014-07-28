package com.hubbabubbagump.GameObjects;

public class Dirt extends Scrollable {
	
	//resets the Dirt class
	public Dirt(float x, float y, int width, int height, float scrollSpeed) {
		super(x, y, width, height, scrollSpeed);
	}
	
	public void restart(float x, float scrollSpeed) {
		position.x = x;
		velocity.x = scrollSpeed;
	}
	
	public float getY() {
		return position.y; //returns the y coordinate of where the dirt should be
	}
	
	 public float getX() {
	     return position.x;
	 }
	 
	 public void reverse(boolean high) {
		 velocity.x = -velocity.x;
	 }

}