package com.hubbabubbagump.Screens;

//Imports
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.hubbabubbagump.GameWorld.GameRenderer;
import com.hubbabubbagump.GameWorld.GameWorld;

public class GameScreen implements Screen{
	
	private GameWorld world;
	private GameRenderer renderer;
	
	//Creates new world and renderer
	public GameScreen() {
		System.out.println("GameScreen attached");
		
		world = new GameWorld();
		renderer = new GameRenderer(world);
	}
	
	//Handles rendering
	@Override
	public void render (float delta) {
		world.update(delta);
		renderer.render();
	}
	
	//Handles resizing of the screen
	@Override
	public void resize(int width, int height) {
		System.out.println("Resizing Gamescreen");
	}
	
	//Handles the display
	@Override
	public void show() {
		System.out.println("Showing GameScreen");
	}
	
	
	//Handles what happens when the screen is out of focus
	@Override
	public void hide() {
		System.out.println("Hiding Gamescreen");
	}
	
	
	//Handles when the game is paused
	@Override
	public void pause() {
		System.out.println("Pausing Gamescreen");
	}
	
	
	//Handles what happens when the game is resumed
	@Override
	public void resume() {
		System.out.println("Resuming Gamescreen");
	}
	
	//Destructor
	@Override
	public void dispose() {
		//Nothing as of the moment.
	}
}
