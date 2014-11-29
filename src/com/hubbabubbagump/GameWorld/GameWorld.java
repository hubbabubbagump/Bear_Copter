package com.hubbabubbagump.GameWorld;

import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.hubbabubbagump.GameObjects.BearCopter;

import com.hubbabubbagump.GameObjects.Laser;
import com.hubbabubbagump.GameObjects.ScrollHandler;
import com.hubbabubbagump.GameObjects.Shroom;
import com.hubbabubbagump.Helpers.AssetLoader;
import com.hubbabubbagump.Screens.GameScreen;

public class GameWorld {
	
	private FPSLogger fps;
	
	private Shroom shroom;
	
	private BearCopter bear;
	private static ScrollHandler scroller;
	
	private Rectangle ground;
	private Rectangle ceiling;
	
	private Vector2 timeRate;
	private Vector2 time;
	private Vector2 storyTime;
	private static final float TIMERATE = 1;

	public static boolean high = false;
	public static boolean invincible = false;
	
	private static GameState currentState;
	
	private static float mid = GameScreen.midScreen();
	
	private static int score = 0;
	private static int secondHS;
	private static int thirdHS;
	
	private float runTime = 0;

	public static final int GRASS_LOCATION = (int) (mid + 66);
	public static final int RESTART_HEIGHT = (int) (mid - 5);
	public static final int GAMEWIDTH = 136;
	
	public static float VOLUME = 0.5f;
	public static final float VOLUME_REDUCTION = 0.2f;
	
	public static final float GREENDELAY = 10;
	public static final float REDDELAY = 6;
	public static boolean runOnce = true;
	
	public static boolean BGMpaused = false;
	
	public static boolean startDown = false;
	public static boolean scoreDown = false;
	public static boolean menuDown = false;
	public static boolean charDown = false;
	
	public static int LEVEL = 1;
	
	
	
	public GameWorld(int midPointY) {
		
		currentState = GameState.STORY_STATE;
		
		//Initializes the bear;
		bear = new BearCopter(33, RESTART_HEIGHT, 17, 13); //sets bear location to x = 33, y = midpoint of y - 5, initializes bear
		
		//creates new scrollHandlers, number sets position of grass
		scroller = new ScrollHandler(GRASS_LOCATION);
		ground = new Rectangle(0, GRASS_LOCATION, GAMEWIDTH, 20);
		ceiling = new Rectangle(0, -33, GAMEWIDTH, 20);
		
		time = new Vector2(0, 0);
		storyTime = new Vector2(0, 0);
		timeRate = new Vector2(TIMERATE, 0);
		
		AssetLoader.BGM.play(); //plays the BGM on loop
		AssetLoader.BGM.setLooping(true);
		AssetLoader.BGM.setVolume(VOLUME - VOLUME_REDUCTION); //sets the volume
		
		fps = new FPSLogger();
		
		shroom = scroller.getShroom();
	}
	
	public static int getScore() {
		return score;
	}
	
	public static boolean isInvincible() {
		return invincible;
	}
	
	public static float getMid() {
		return mid;
	}
	
	
	public void counter(float delta) {
		if(bear.Alive())
		time.add(timeRate.cpy().scl(delta));
	}
	
	public void counterReset() {
		time.x = 0;
	}
	
	
	
	//Handles levels
	public void level() {
		if (scroller.isLevel4()) {
			LEVEL = 4;
		}
		else if (score >= 125) {
			LEVEL = 3;
		}
		else if (score >= 50) {
			LEVEL = 2;
		}
		else {
			LEVEL = 1;
		}
	}
	
	public static int getLevel() {
		return LEVEL;
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
		case PAUSED_STATE:
			break;
		case CHARSELECT_STATE:
			updatingCharSelect(delta);
			break;
		case STORY_STATE:
			updatingStory(delta);
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
			
			AssetLoader.sad.pause();
			
			AssetLoader.BGM.pause();
			
			AssetLoader.trippy.play();
			AssetLoader.trippy.setLooping(true);
			AssetLoader.trippy.setVolume(VOLUME);
			BGMpaused = true;
			/*
			 * 
			 * 
			 * 
			 * 
			 * HERE
			 * 
			 * 
			 * 
			 * 
			 * 
			 * 
			 */
		}
	}
	
	private void updatingStory(float delta) {
		storyTime.add(timeRate.cpy().scl(delta));
		if (storyTime.x >= 5) {
			currentState = GameState.TITLE_STATE;
		}
	}
	
	private void updatingPause(float delta) {
		storyTime.x = 0;

		bear.updatePause(runTime);
		scroller.updatePause(delta);
	}
	
	private void updatingCharSelect(float delta) {
		scroller.updatePause(delta);
		
	}
	
	private void updatingRunning(float delta) {
		storyTime.x = 0;
		bear.update(delta);
		scroller.update(delta, bear);
		level();
		
		//collision checks
		
		wallCheck();
		
		groundCeilingCheck();
		fruitCheck();
		
		if(score > 150) {
			laserCheck();
		}
		
		high();
		
		//if the bear is "high"
		if(high || invincible) {
			counter(delta);
		}
		
		if (score > 150) {
			Laser.activate();
		}
		
		if(time.x >= GREENDELAY || (time.x >= REDDELAY && invincible)) { //once 10 seconds is up
			
			time.x = 0;
			runOnce = true;
			
			if (shroom.shroomNumber() == 0) {
				invincible = false;
				
				scroller.shroomRestore();
			}
			else {
				high = false;  //reverts everything back
			
				bear.reverse(high);
				scroller.reverse(high);
				
				AssetLoader.trippy.pause();
		
				AssetLoader.BGM.play();
				BGMpaused = false;
				
			}
		}

	}
	
	private void laserCheck() {
		if (scroller.fire) {
			if(scroller.laserHit(bear)) {
				if(bear.Alive()) {
					//Game Over
					scroller.stop();
					bear.dead();
					
					time.x = 0;
					bear.explode();
				}
			}
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
				time.x = 0;
				//red shroom triggers no walls
				if (shroom.shroomNumber() == 0) {
					invincible = true;
					scroller.shroomChange();
				}
				//green shroom triggers a high
				else {
					high = true;
				}
			}
			
			if(scroller.collidesPenguin(bear)) {
				scroller.atePenguin();
				bear.shroomEaten();
				BearCopter.comboUp();
				AssetLoader.unlockPenguin();
			}
			if(scroller.collidesRam(bear)) {
				scroller.ateRam();
				bear.shroomEaten();
				BearCopter.comboUp();
				AssetLoader.unlockRam();
			}
			if(scroller.collidesToast(bear)) {
				scroller.ateToast();
				bear.shroomEaten();
				BearCopter.comboUp();
				AssetLoader.unlockToast();
			}
			if(scroller.collidesCat(bear)) {
				scroller.ateCat();
				bear.shroomEaten();
				BearCopter.comboUp();
				AssetLoader.unlockCat();
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
				AssetLoader.dead.play(VOLUME);
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
		PAUSED_STATE,
		CHARSELECT_STATE,
		STORY_STATE,
	}
	
	public BearCopter getBear() {
		return bear;
	}
	
	public void volumeControl() {

		if (VOLUME > 0.0f) {
			VOLUME = 0.0f;

		}
		else if (VOLUME == 0.0f) {
			VOLUME = 0.5f;
		}
		if (VOLUME == 0) {
			AssetLoader.BGM.setVolume(VOLUME);
		}
		else {
			AssetLoader.BGM.setVolume(VOLUME - VOLUME_REDUCTION);
		}
		AssetLoader.trippy.setVolume(VOLUME);
		
		
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
	
	public boolean isPaused() {
		return currentState == GameState.PAUSED_STATE;
	}
	
	public boolean isCharSelect() {
		return currentState == GameState.CHARSELECT_STATE;
	}
	
	public void charSelect() {
		currentState = GameState.CHARSELECT_STATE;
	}
	
	public void pauseGame() {
		currentState = GameState.PAUSED_STATE;
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
		currentState = GameState.PAUSE_STATE;
		reset();
	}
	
	public boolean isStory() {
		return currentState == GameState.STORY_STATE;
	}
	
	public void story() {
		currentState = GameState.STORY_STATE;
	}
	
	public void reset() {
		LEVEL = 1;
		invincible = false;
		mid = GameScreen.midScreen();
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
	
	public void toMenu() {
		currentState = GameState.TITLE_STATE;
		reset();
	}

	public static boolean isHigh() {
		return high;
	}

	public void startButtonDown() {
		startDown = true;
	}
	
	public void startButtonUp() {
		startDown = false;
	}
	
	public void scoreButtonDown() {
		scoreDown = true;
	}
	
	public void charButtonDown() {
		charDown = true;
	}
	
	public void charButtonUp() {
		charDown = false;
	}
	
	public void scoreButtonUp() {
		scoreDown = false;
	}
	
	public void menuButtonDown() {
		menuDown = true;
	}
	
	public void menuButtonUp() {
		menuDown = false;
	}
}
