package com.hubbabubbagump.GameObjects;

import com.badlogic.gdx.math.Vector2;

public class Scrollable {

	 protected Vector2 position;
	 protected Vector2 velocity;
	 protected int width;
	 protected int height;
	 protected float yLoc;
	 protected boolean isScrolledLeft;
	 
	 public Scrollable(float x, float y, int width, int height, float scrollSpeed) {
		 position = new Vector2(x, y);
		 velocity = new Vector2(scrollSpeed, 0);
		 this.width = width;
		 this.height = height;
		 isScrolledLeft = false;
	 }
	 
	 //updates position
	 public void update(float delta) {
		 position.add(velocity.cpy().scl(delta));
		 
		 //If object passes left side of screen, sets scrolledLeft to true
		 if (position.x + width < 0) {
			 isScrolledLeft = true;
		 }
	 }
	 
	 //resets object
	 public void reset(float newX) {
		 position.x = newX;
		 isScrolledLeft = false;
	 }
	 
	 //has textures stop scrolling
	 public void stop() {
			 velocity.x = 0;
	 }
	 
	 public boolean isScrolledLeft() {
	        return isScrolledLeft;
	 }

	 public float getTailX() {
	     return position.x + width;
	 }

	 public float getX() {
	     return position.x;
	 }

	 public float getY() {
	     return position.y + yLoc;
	 }
	 
	 public float grassGetY() {
		 return position.y;
	 }

	 public int getWidth() {
	     return width;
	 }

	 public int getHeight() {
	     return height;
	 }
}
