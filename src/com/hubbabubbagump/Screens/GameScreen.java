package com.hubbabubbagump.Screens;

//Imports
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.hubbabubbagump.GameWorld.GameRenderer;
import com.hubbabubbagump.GameWorld.GameWorld;
import com.hubbabubbagump.Helpers.InputHandler;

public class GameScreen implements Screen{
	
	private GameWorld world;
	private GameRenderer renderer;
	private float runTime = 0;
	private static float mid;
	
	public static final int GAMEWIDTH = 136;
	public static float GAMEHEIGHT;
	
	//Creates new world and renderer
	public GameScreen() {
		
		//Calculates the midpoint of Y according to the device's height
		float screenWidth = Gdx.graphics.getWidth();
		float screenHeight = Gdx.graphics.getHeight();
		float gameWidth = GAMEWIDTH;
		float gameHeight = screenHeight / (screenWidth / gameWidth);
		GAMEHEIGHT = gameHeight;
		int midPointY = (int) (gameHeight/2);
		mid = midPointY;
		
		world = new GameWorld(midPointY);
		renderer = new GameRenderer(world, (int) gameHeight, midPointY);
		
		Gdx.input.setInputProcessor(new InputHandler(world, screenWidth / gameWidth, screenHeight / gameHeight));
	}
	
	//Handles rendering
	@Override
	public void render (float delta) {
		runTime += delta;
		world.update(delta);
		renderer.render(delta, runTime);
	}
	
	public static float midScreen() {
		return mid;
	}
	
	//Handles resizing of the screen
	@Override
	public void resize(int width, int height) {
		
	}
	
	//Handles the display
	@Override
	public void show() {
		
	}
	
	
	//Handles what happens when the screen is out of focus
	@Override
	public void hide() {
		
	}
	
	
	//Handles when the game is paused
	@Override
	public void pause() {

	}
	
	
	//Handles what happens when the game is resumed
	@Override
	public void resume() {
		
	}
	
	//Destructor
	@Override
	public void dispose() {
		//Nothing as of the moment.
	}
}
