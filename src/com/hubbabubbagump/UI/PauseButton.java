package com.hubbabubbagump.UI;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class PauseButton {
	
	private float x, y, width, height;
	
	private TextureRegion pause;

	
	private Rectangle bounds;
	
	int SX;
	int SY;
	
	private boolean press = false;
	
	//coordinates for where the menu button is
	public static final float BUTTON_X_LOCATION = (float) (136/2 - 5.5);
	public static final int BUTTON_Y_LOCATION = 5;
	
	public PauseButton(float x, float y, float width, float height, TextureRegion pause) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.pause = pause;
		
		
		bounds = new Rectangle(x, y, width, height);
	}
	
	public boolean isClicked(int screenX, int screenY) {
		return bounds.contains(screenX, screenY);
	}
	
	public void draw(SpriteBatch batcher) {
		batcher.draw(pause, x, y, width, height);
		
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
	
	public static float getX() {
		return BUTTON_X_LOCATION;
	}
	
	public static int getY() {
		return BUTTON_Y_LOCATION;
	}
	
	
}
