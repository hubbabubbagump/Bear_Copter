package com.hubbabubbagump.UI;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.hubbabubbagump.Helpers.AssetLoader;
import com.hubbabubbagump.Screens.GameScreen;

public class ScoreButton {
	
	private float x, y, width, height;
	
	private TextureRegion startButton;
	
	private Rectangle bounds;
	
	private boolean press = false;
	private static int midPointY = (int) GameScreen.midScreen();
	
	//coordinates for where the start button is
	public static final int BUTTON_X_LOCATION = 136 / 4 * 3 - (AssetLoader.score.getRegionWidth() / 2);
	public static final int BUTTON_Y_LOCATION = midPointY + 40;
	
	public ScoreButton(float x, float y, float width, float height, TextureRegion startButton) {
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
		return BUTTON_X_LOCATION;
	}
	
	public static int getY() {
		return BUTTON_Y_LOCATION;
	}
}

