package com.hubbabubbagump.UI;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class CharChange {
	
	private float x, y, width, height;
	
	private TextureRegion avatar;
	
	private Rectangle bounds;
	
	int SX;
	int SY;
	
	private boolean press = false;
	
	//coordinates for where the menu button is
	public static int BUTTON_X_LOCATION;
	public static int BUTTON_Y_LOCATION;
	
	public CharChange(float x, float y, float width, float height, TextureRegion avatar) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.avatar = avatar;
		BUTTON_X_LOCATION = (int) x;
		BUTTON_Y_LOCATION = (int) y;
		
		bounds = new Rectangle(x, y, width, height);
	}
	
	public boolean isClicked(int screenX, int screenY) {
		return bounds.contains(screenX, screenY);
	}
	
	public void draw(SpriteBatch batcher) {
		batcher.draw(avatar, x, y, width, height);
		
	}
	
	public boolean downTouch(int screenX, int screenY) {
		if(bounds.contains(screenX, screenY)) {
			press = true;
			return true;
			
		}
		return false;
	}
	
	public boolean isPressed() {
		return press;
	}
	
	public boolean upTouch(int screenX, int screenY) {
		
		if(bounds.contains(screenX, screenY) && press) {
			press = false;
			return true;

		}
		press = false;
		return false;
	}
	
	public static int getX() {
		return BUTTON_X_LOCATION;
	}
	
	public static int getY() {
		return BUTTON_Y_LOCATION;
	}
	
	
}
