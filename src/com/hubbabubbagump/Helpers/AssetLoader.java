package com.hubbabubbagump.Helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetLoader {
	
	public static Texture texture;
	public static TextureRegion background, grass;
	
	public static Animation bearAnimation;
	public static TextureRegion bear, bearDown;
	
	public static TextureRegion brick;
	
	public static void load() {
		
		//Retrieves Images.png from bear-a-copter-android/assets
		texture = new Texture(Gdx.files.internal("data/texture.png"));
		texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
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
	}
	
	public static void dispose() {
		texture.dispose();
	}

}
