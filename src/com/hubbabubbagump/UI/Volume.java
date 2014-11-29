package com.hubbabubbagump.UI;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Volume {
	
	private float x, y, width, height;
	
	private TextureRegion volumeON;
	private TextureRegion volumeOFF;
	
	private Rectangle bounds;
	
	int SX;
	int SY;
	
	private boolean press = false;
	
	//coordinates for where the menu button is
	public static final int BUTTON_X_LOCATION = 5;
	public static final int BUTTON_Y_LOCATION = 5;
	
	public Volume(float x, float y, float width, float height, TextureRegion volumeON, TextureRegion volumeOFF) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.volumeON = volumeON;
		this.volumeOFF = volumeOFF;
		
		
		bounds = new Rectangle(x, y, width, height);
	}
	
	public boolean isClicked(int screenX, int screenY) {
		return bounds.contains(screenX, screenY);
	}
	
	public void drawON(SpriteBatch batcher) {
		batcher.draw(volumeON, x, y, width, height);
		
	}
	
	public void drawOFF(SpriteBatch batcher) {
		batcher.draw(volumeOFF, x, y, width, height);
	}
	
	public boolean downTouch(int screenX, int screenY) {
		if(bounds.contains(screenX, screenY)) {
			press = true;
			return true;
			
		}
		return false;
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
