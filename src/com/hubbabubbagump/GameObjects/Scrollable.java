package com.hubbabubbagump.GameObjects;

import com.badlogic.gdx.math.Vector2;
import com.hubbabubbagump.GameWorld.GameWorld;

public abstract class Scrollable {

	 protected Vector2 position;
	 protected Vector2 velocity;
	 protected int width;
	 protected int height;
	 protected float yLoc;
	 protected float fruitY;
	 protected boolean isScrolledLeft;
	 protected boolean isScrolledRight;
	 
	 private boolean high;
	 
	 public Scrollable(float x, float y, int width, int height, float scrollSpeed) {
		 position = new Vector2(x, y);
		 velocity = new Vector2(scrollSpeed, 0);
		 this.width = width;
		 this.height = height;
		 isScrolledLeft = false;
		 isScrolledRight = false;
	 }
	 
	 //updates position
	 public void update(float delta) {
		 high = GameWorld.isHigh();		 
		 position.add(velocity.cpy().scl(delta));
		 //If object passes left side of screen, sets scrolledLeft to true
		 if (position.x + width < 0 && !high) {
			 isScrolledLeft = true;
		 }
		 else if (position.x > 136 && high) {
			 isScrolledRight = true;
		 }
	 }
	 
	 //resets object
	 public void reset(float newX) {
		 position.x = newX;
		 isScrolledLeft = false;
		 isScrolledRight = false;
	 }
	 
	 //has textures stop scrolling
	 public void stop() {
			 velocity.x = 0;
	 }
	 
	 public boolean isScrolledLeft() {
	        return isScrolledLeft;
	 }
	 
	 public boolean isScrolledRight() {
		 return isScrolledRight;
	 }

	 public float getTailX() {
	     return position.x + width;
	 }

	 public abstract float getX();
	 public abstract float getY();

	 public int getWidth() {
	     return width;
	 }

	 public int getHeight() {
	     return height;
	 }
}
