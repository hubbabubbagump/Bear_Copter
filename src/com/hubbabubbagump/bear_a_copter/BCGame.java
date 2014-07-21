/* ======================================
 * BearCopter 
 * ======================================
 * Current version: 0.5
 * Release Date: TBA
 * ======================================
 * Created by Ethan Jung (hubbabubbagump)
 * ethansjung@gmail.com
 * ======================================
 */

package com.hubbabubbagump.bear_a_copter;

//Imports
import com.badlogic.gdx.Game;
import com.hubbabubbagump.Helpers.AssetLoader;
import com.hubbabubbagump.Screens.SplashScreen;


public class BCGame extends Game{
	
	//Create method
	@Override
	public void create() {
		System.out.println("Game Created.");
		AssetLoader.load();
		setScreen(new SplashScreen(this));
		
		
	}
	
	@Override
	public void dispose() {
		super.dispose();
		AssetLoader.dispose();
	}
}