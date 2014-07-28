package com.hubbabubbagump.UI;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.hubbabubbagump.Helpers.AssetLoader;
import com.hubbabubbagump.Screens.GameScreen;

public class MenuButton {
	
	private float x, y, width, height;
	
	private TextureRegion menuButton;
	private TextureRegion menuDown;
	
	private Rectangle bounds;
	
	int SX;
	int SY;
	
	private boolean press = false;
	
	//coordinates for where the menu button is
	public static final int BUTTON_X_LOCATION = 18;
	public static final int BUTTON_Y_LOCATION = AssetLoader.start.getRegionHeight() / 2;
	
	public MenuButton(float x, float y, float width, float height, TextureRegion menuButton, TextureRegion menuDown) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.menuButton = menuButton;
		this.menuDown = menuDown;
		
		
		bounds = new Rectangle(x, y, width, height);
	}
	
	public boolean isClicked(int screenX, int screenY) {
		return bounds.contains(screenX, screenY);
	}
	
	public void draw(SpriteBatch batcher) {
		
		batcher.draw(menuButton, x, y, width, height);
		
	}
	
	public void drawDown(SpriteBatch batcher) {
		batcher.draw(menuDown, x, y, width, height);
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
