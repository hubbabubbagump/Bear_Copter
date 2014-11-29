package com.hubbabubbagump.UI;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.hubbabubbagump.Helpers.AssetLoader;
import com.hubbabubbagump.Screens.GameScreen;

public class CharSelect {
	
	private float x, y, width, height;
	
	private TextureRegion select;
	private TextureRegion selectDown;
	
	private Rectangle bounds;
	
	int SX;
	int SY;
	
	private static int midPointY = (int) GameScreen.midScreen();
	
	private boolean press = false;
	
	//coordinates for where the menu button is
	public static final int BUTTON_X_LOCATION = 136 / 4 * 3 - (AssetLoader.score.getRegionWidth() / 2);
	public static final int BUTTON_Y_LOCATION = midPointY + 15;
	
	public CharSelect(float x, float y, float width, float height, TextureRegion select, TextureRegion selectDown) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.select = select;
		this.selectDown = selectDown;
		
		
		bounds = new Rectangle(x, y, width, height);
	}
	
	public boolean isClicked(int screenX, int screenY) {
		return bounds.contains(screenX, screenY);
	}
	
	public void draw(SpriteBatch batcher) {
		batcher.draw(select, x, y, width, height);
		
	}
	
	public void drawDown(SpriteBatch batcher) {
		batcher.draw(selectDown, x, y, width, height);
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
