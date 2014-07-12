package com.hubbabubbagump.Helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetLoader {
	
	public static Texture texture;
	public static Texture startButton;
	public static TextureRegion background, grass;
	public static TextureRegion start;
	
	public static Animation bearAnimation;
	public static TextureRegion bear, bearDown;
	
	public static TextureRegion brick;
	
	public static BitmapFont font;
	public static Preferences prefs;
	//public static final String FONT_CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	
	public static void load() {
		
		//Retrieves Images.png from bear-a-copter-android/assets
		texture = new Texture(Gdx.files.internal("data/texture.png"));
		texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		startButton = new Texture(Gdx.files.internal("data/start.png"));
		startButton.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		start = new TextureRegion(startButton, 0, 0, 40, 20);
		start.flip(false, true);
		
		//Add logo in the future
		
		//Creates a new texture using image starting at (0,0) 
		//with 136 width and 43 height.
		background = new TextureRegion(texture, 0, 0, 136, 43);
		background.flip(false, true);
		
		grass = new TextureRegion(texture, 0, 43, 143, 11);
		grass.flip(false, true);
		
		bearDown = new TextureRegion(texture, 136, 17, 18, 16);
		bearDown.flip(false, true);
		
		bear = new TextureRegion(texture, 136, 0, 18, 16);
		bear.flip(false, true);
		
		brick = new TextureRegion(texture, 154, 0, 22, 5);
		brick.flip(false, true);
		
		//font = TrueTypeFontFactory.createBitmapFont(Gdx.files.internal("data/font.ttf"), FONT_CHARACTERS, 12.5f, 7.5f, 1.0f, 100, 100);
		//font.setColor(0f, 0f, 0f, 1f);
		font = new BitmapFont(Gdx.files.internal("data/bitfont.fnt"));
		font.setScale(.5f, -.5f);
		
		prefs = Gdx.app.getPreferences("BearCopter");
		if(!prefs.contains("highScore")) {
			prefs.putInteger("highScore", 0);
		}
	}
	
	public static void setHighScore(int val) {
		prefs.putInteger("highScore", val);
		prefs.flush();
	}
	
	public static int getHighScore() {
		return prefs.getInteger("highScore");
	}
	
	public static void dispose() {
		texture.dispose();
		startButton.dispose();
		
		
		font.dispose();
	}

}
