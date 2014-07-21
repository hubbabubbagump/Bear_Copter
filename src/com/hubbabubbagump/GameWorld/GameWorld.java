package com.hubbabubbagump.GameWorld;

import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.hubbabubbagump.GameObjects.BearCopter;
import com.hubbabubbagump.GameObjects.ScrollHandler;
import com.hubbabubbagump.Helpers.AssetLoader;
import com.hubbabubbagump.Screens.GameScreen;

public class GameWorld {
	
	private FPSLogger fps;
	
	private BearCopter bear;
	private ScrollHandler scroller;
	
	private Rectangle ground;
	private Rectangle ceiling;
	
	private Vector2 timeRate;
	private Vector2 time;
	private static final float TIMERATE = 1;

	public static boolean high = false;
	
	private static GameState currentState;
	
	private static float mid = GameScreen.midScreen();
	
	private static int score = 0;
	private static int secondHS;
	private static int thirdHS;
	
	private float runTime = 0;

	public static final int GRASS_LOCATION = (int) (mid + 66);
	public static final int RESTART_HEIGHT = (int) (mid - 5);
	public static final int GAMEWIDTH = 136;
	
	public static final float VOLUME = 0.1f;
	
	public static final float DELAY = 10;
	public static boolean runOnce = true;
	
	public boolean BGMpaused = false;
	
	public GameWorld(int midPointY) {
		currentState = GameState.TITLE_STATE;
		//Initializes the bear;
		bear = new BearCopter(33, RESTART_HEIGHT, 17, 12); //sets bear location to x = 33, y = midpoint of y - 5, initializes bear
		
		//creates new scrollHandlers, number sets position of grass
		scroller = new ScrollHandler(GRASS_LOCATION);
		ground = new Rectangle(0, GRASS_LOCATION, GAMEWIDTH, 20);
		ceiling = new Rectangle(0, -33, GAMEWIDTH, 20);
		
		time = new Vector2(0, 0);
		timeRate = new Vector2(TIMERATE, 0);
		
		AssetLoader.BGM.play(); //plays the BGM on loop
		AssetLoader.BGM.setLooping(true);
		AssetLoader.BGM.setVolume(VOLUME); //sets the volume
		
		fps = new FPSLogger();
	}
	
	public static int getScore() {
		return score;
	}
	
	public static float getMid(){
		return mid;
	}
	
	public void counter(float delta) {
		if(bear.Alive())
		time.add(timeRate.cpy().scl(delta));
	}
	
	public void counterReset() {
		time.x = 0;
	}
	
	
	public void update(float delta) {
		fps.log();
		score = (int) bear.getScore();
		runTime += delta;
		
		switch (currentState) {
		case PAUSE_STATE:
		case TITLE_STATE:
			updatingPause(delta);
			break;
		case SCORE_STATE:
			updatingPause(delta);
			break;
		case MAINGAME_STATE:
			updatingRunning(delta);
			break;
		default:
			break;
		}
		
	}
	
	private void high() {
		if(high && runOnce) { //checks if the bear is high
							  //runOnce makes it run only once during the duration that the bear is high
			bear.reverse(high);
			scroller.reverse(high);
			runOnce = false;
			AssetLoader.BGM.pause();
			AssetLoader.trippy.play();
			AssetLoader.trippy.setLooping(true);
			AssetLoader.trippy.setVolume(VOLUME);
			BGMpaused = true;
			
		}
	}
	
	
	
	private void updatingPause(float delta) {
		bear.updatePause(runTime);
		scroller.updatePause(delta);
	}
	
	private void updatingRunning(float delta) {
		bear.update(delta);
		scroller.update(delta);
		
		//collision checks
		wallCheck();
		groundCeilingCheck();
		fruitCheck();
		
		high();
		
		//if the bear is "high"
		if(high) {
			counter(delta);
		}
		if(time.x >= DELAY) { //once 10 seconds is up
			high = false;  //reverts everything back
			time.x = 0;
			bear.reverse(high);
			scroller.reverse(high);
			runOnce = true;
			AssetLoader.trippy.pause();
			AssetLoader.BGM.play();
			BGMpaused = false;
		}

	}
	
	//checks to see if the bear collides with any fruit or shrooms
	private void fruitCheck() {
		if(bear.Alive()) {
			if(scroller.collidesFruit1(bear)) {
				scroller.ate1();
				bear.fruitEaten();		
				BearCopter.comboUp(); //if it does increases the combo counter
			}
			if(scroller.collidesFruit2(bear)) {
				scroller.ate2();
				bear.fruitEaten();		
				BearCopter.comboUp();

			}
			if(scroller.collidesFruit3(bear)) {
				scroller.ate3();
				bear.fruitEaten();			
				BearCopter.comboUp();

			}
			
			if(scroller.collidesShroom(bear)) {
				scroller.ateShroom();
				bear.shroomEaten();
				BearCopter.comboUp();
				high = true;
				time.x = 0;
			}
		}
	}
	
	private void highScore() {
		//sets high scores accordingly
		if (score >= AssetLoader.getHighScore()) {
			secondHS = AssetLoader.getHighScore();
			thirdHS = AssetLoader.getSecondHighScore();
			AssetLoader.setHighScore(score);
			AssetLoader.setSecondHighScore(secondHS);
			AssetLoader.setThirdHighScore(thirdHS);
		}
		else if (score >= AssetLoader.getSecondHighScore()) {
			thirdHS = AssetLoader.getSecondHighScore();
			AssetLoader.setSecondHighScore(score);
			AssetLoader.setThirdHighScore(thirdHS);
		}
		else if (score >= AssetLoader.getThirdHighScore()) {
			AssetLoader.setThirdHighScore(score);
		}
	}
	
	private void groundCeilingCheck() {
		if(Intersector.overlaps(bear.getBoundingCircle(), ground)) {
			scroller.stop();
			bear.dead();
			bear.deadAcceleration();
			if(currentState != GameState.GAMEOVER_STATE) {
				long dead = AssetLoader.dead.play();
				AssetLoader.dead.setVolume(dead, VOLUME);
			}
			currentState = GameState.GAMEOVER_STATE;
			
			highScore();
			
			time.x = 0;
		}
		
		
		if(Intersector.overlaps(bear.getBoundingCircle(), ceiling)) {
			scroller.stop();
			bear.dead();
			
			time.x = 0;
		}
	}
	
	private void wallCheck() {
		if(scroller.collides(bear)) {
			if(bear.Alive()) {
				//Game Over
				scroller.stop();
				bear.dead();
				
				time.x = 0;
			}
		}
	}
	
	public enum GameState {
		TITLE_STATE,
		PAUSE_STATE,
		MAINGAME_STATE,
		GAMEOVER_STATE,
		SCORE_STATE,
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
	
	public void titleScreen() {
		currentState = GameState.TITLE_STATE;
	}
	
	public boolean pause() {
		return currentState == GameState.PAUSE_STATE;
	}
	
	public static boolean running() {
		return currentState == GameState.MAINGAME_STATE;
	}
	
	public boolean gameOver() {
		return currentState == GameState.GAMEOVER_STATE;
	}
	
	public boolean isScore() {
		return currentState == GameState.SCORE_STATE;
	}
	
	public void score() {
		currentState = GameState.SCORE_STATE;
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
		bear.restart(RESTART_HEIGHT);
		scroller.restart();
		score = 0;
		time.x = 0;
		high = false;
		runOnce = true;
		
		if (BGMpaused) {
			AssetLoader.trippy.pause();
			AssetLoader.BGM.play();
			BGMpaused = false;
		}
	}

	public static boolean isHigh() {
		return high;
	}

			
}
