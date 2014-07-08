package com.hubbabubbagump.bear_a_copter;

//Imports
import com.badlogic.gdx.Game;
import com.hubbabubbagump.Helpers.AssetLoader;
import com.hubbabubbagump.Screens.GameScreen;

public class BCGame extends Game {
	
	//Create method
	@Override
	public void create() {
		System.out.println("Game Created.");
		AssetLoader.load();
		setScreen(new GameScreen()); //Calls GameScreen
	}
	
	@Override
	public void dispose() {
		super.dispose();
		AssetLoader.dispose();
	}
}