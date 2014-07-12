package com.hubbabubbagump.UI;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.hubbabubbagump.Helpers.AssetLoader;
import com.hubbabubbagump.Screens.GameScreen;

public class StartButton {
	
	private float x, y, width, height;
	
	private TextureRegion startButton;
	
	private Rectangle bounds;
	
	private boolean press = false;
	private static int midPointY;
	
	public StartButton(float x, float y, float width, float height, TextureRegion startButton) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.startButton = startButton;
		
		bounds = new Rectangle(x, y, width, height);
	}
	
	public boolean isClicked(int screenX, int screenY) {
		return bounds.contains(screenX, screenY);
	}
	
	public void draw(SpriteBatch batcher) {
		batcher.draw(startButton, x, y, width, height);
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
		return (136/2 - (AssetLoader.start.getRegionWidth() / 2));
	}
	
	public static int getY() {
		midPointY = (int) GameScreen.midScreen();
		return midPointY + 40;
	}
}
