package com.hubbabubbagump.GameWorld;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.hubbabubbagump.GameObjects.BearCopter;
import com.hubbabubbagump.GameObjects.ScrollHandler;
import com.hubbabubbagump.Helpers.AssetLoader;
import com.hubbabubbagump.Screens.GameScreen;

public class GameWorld {
	
	private BearCopter bear;
	private ScrollHandler scroller;
	
	private Rectangle ground;
	private Rectangle ceiling;
	
	private GameState currentState;
	
	private static float mid;
	
	private static int score = 0;
	private float runTime = 0;
	
	public GameWorld(int midPointY) {
		currentState = GameState.TITLE_STATE;
		//Initializes the bear;
		bear = new BearCopter(33, midPointY-5, 17, 12); //sets bear location to x = 33, y = midpoint of y - 5, initializes bear
		mid = midPointY;
		//creates new scrollHandlers, number sets position of grass
		scroller = new ScrollHandler(midPointY + 66);
		ground = new Rectangle(0, midPointY + 66, 126, 20);
		ceiling = new Rectangle(0, -20, 126, 20);
		
	}
	
	public static int getScore() {
		return score;
	}
	
	public static float getMid(){
		return mid;
	}
	
	public void update(float delta) {
		score = (int) bear.getScore();
		runTime += delta;
		
		switch (currentState) {
		case PAUSE_STATE:
		case TITLE_STATE:
			updatingPause(delta);
			break;
		
		case MAINGAME_STATE:
			updatingRunning(delta);
			break;
		default:
			break;
		}
		
	}
	
	private void updatingPause(float delta) {
		bear.updatePause(runTime);
		scroller.updatePause(delta);
	}
	
	private void updatingRunning(float delta) {
		bear.update(delta);
		scroller.update(delta);
		
		if(scroller.collides(bear)) {
			if(bear.Alive()) {
				//Game Over
				scroller.stop();
				bear.dead();
			}
		}
		
		if(Intersector.overlaps(bear.getBoundingCircle(), ground)) {
			scroller.stop();
			bear.dead();
			bear.deadAcceleration();
			currentState = GameState.GAMEOVER_STATE;
			
			if (score > AssetLoader.getHighScore()) {
				AssetLoader.setHighScore(score);
			}
		}
		
		if(Intersector.overlaps(bear.getBoundingCircle(), ceiling)) {
			scroller.stop();
			bear.dead();
		}
	}
	
	public enum GameState {
		TITLE_STATE,
		PAUSE_STATE,
		MAINGAME_STATE,
		GAMEOVER_STATE,
	}
	
	public BearCopter getBear() {
		return bear;
	}
	
	public ScrollHandler getScroller() {
		return scroller;
	}
	
	public boolean title() {
		return currentState == GameState.TITLE_STATE;
	}
	
	public boolean pause() {
		return currentState == GameState.PAUSE_STATE;
	}
	
	public boolean running() {
		return currentState == GameState.MAINGAME_STATE;
	}
	
	public boolean gameOver() {
		return currentState == GameState.GAMEOVER_STATE;
	}
	
	public void start() {
		currentState = GameState.MAINGAME_STATE;
	}
	
	public void ready() {
		currentState = GameState.PAUSE_STATE;
	}
	
	public void restart() {
		mid = GameScreen.midScreen();
		currentState = GameState.PAUSE_STATE;
		bear.restart((int) mid - 5);
		scroller.restart();
		score = 0;
	}

			
}
