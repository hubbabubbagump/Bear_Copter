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
	
	public GameScreen() {
		System.out.println("GameScreen attached");
		
		world = new GameWorld();
		renderer = new GameRenderer(world);
	}
	
	@Override
	public void render (float delta) {
		world.update(delta);
		renderer.render();
	}
	
	@Override
	public void resize(int width, int height) {
		System.out.println("Resizing Gamescreen");
	}
	
	@Override
	public void show() {
		System.out.println("Showing GameScreen");
	}
	
	@Override
	public void hide() {
		System.out.println("Hiding Gamescreen");
	}
	
	@Override
	public void pause() {
		System.out.println("Pausing Gamescreen");
	}
	
	@Override
	public void resume() {
		System.out.println("Resuming Gamescreen");
	}
	
	@Override
	public void dispose() {
		//Nothing as of the moment.
	}
}
