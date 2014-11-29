package com.hubbabubbagump.GameObjects;

public class Background extends Scrollable{
	
	public int LEVEL = 1;
	
	//resets the Background class
	public Background(float x, float y, int width, int height, float scrollSpeed) {
		super(x, y, width, height, scrollSpeed);
		
	}
	
	public void restart(float x, float scrollSpeed) {
		position.x = x;
		velocity.x = scrollSpeed;
	}
	
	public float getY() {
		return position.y; //returns the y coordinate of where the background should be
	}
	
	 public float getX() {
	     return position.x;
	 }
	 
	 public void level1() {
		 LEVEL = 1;
	 }
	 
	 public void level2() {
	   	 LEVEL = 2;
	 }
		
	public void level3() {
		LEVEL = 3;
	}
		
	public void level4() {
		LEVEL = 4;
	}
	
}
