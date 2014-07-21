package com.hubbabubbagump.Helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetLoader {
	
	public static Texture texture;
	public static Texture startButton;
	public static Texture scoreButton;
	public static Texture gameOverScreen;
	public static Texture startText;
	public static Texture fruit;
	public static Texture scoreSheet;
	public static Texture shrooms;
	public static Texture rainbowA;
	public static Texture rainbowB;
	public static Texture rainbowC;
	public static Texture splashScreen;
	
	public static TextureRegion background, grass;
	public static TextureRegion start;
	public static TextureRegion score;
	public static TextureRegion gameOver;
	public static TextureRegion pauseText;
	public static TextureRegion apple;
	public static TextureRegion orange;
	public static TextureRegion banana;
	public static TextureRegion highScores;
	public static TextureRegion redShroom;
	public static TextureRegion greenShroom;
	public static TextureRegion rainbow1;
	public static TextureRegion rainbow2;
	public static TextureRegion rainbow3;
	public static TextureRegion splash;
	
	public static Animation rainbowAnimation;
	
	public static TextureRegion bear, bearDown;
	
	public static TextureRegion brick;
	
	public static Sound up;
	public static Sound dead;
	public static Sound eat;
	
	public static Music BGM;
	public static Music trippy;
	
	public static BitmapFont font;
	public static BitmapFont fontSmall;
	
	public static Preferences prefs;
	
	//Pretty much self explanatory
	public static void load() {
		
		//loads music/sounds
		up = Gdx.audio.newSound(Gdx.files.internal("data/clapreverse.wav")); //sounds when bear goes up
		dead = Gdx.audio.newSound(Gdx.files.internal("data/failfare.mp3")); //sound when bear dies
		BGM = Gdx.audio.newMusic(Gdx.files.internal("data/Cozy.mp3")); //BGM
		eat = Gdx.audio.newSound(Gdx.files.internal("data/fruit.wav")); //sound of eating fruit
		trippy = Gdx.audio.newMusic(Gdx.files.internal("data/sonicattack.wav"));
		
		//Retrieves Images.png from bear-a-copter-android/assets/data
		texture = new Texture(Gdx.files.internal("data/texture.png"));
		texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		startButton = new Texture(Gdx.files.internal("data/startCircle.png"));
		startButton.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		scoreButton = new Texture(Gdx.files.internal("data/scoreButton.png"));
		scoreButton.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		startText = new Texture(Gdx.files.internal("data/startText.png"));
		startText.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		gameOverScreen = new Texture(Gdx.files.internal("data/GameOver.png"));
		gameOverScreen.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		scoreSheet = new Texture(Gdx.files.internal("data/scorePage.png"));
		scoreSheet.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		fruit = new Texture(Gdx.files.internal("data/fruit.png"));
		fruit.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		shrooms = new Texture(Gdx.files.internal("data/shrooms.png"));
		shrooms.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		rainbowA = new Texture(Gdx.files.internal("data/rainbow1.png"));
		rainbowA.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		rainbowB = new Texture(Gdx.files.internal("data/rainbow2.png"));
		rainbowB.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		rainbowC = new Texture(Gdx.files.internal("data/rainbow3.png"));
		rainbowC.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		splashScreen = new Texture(Gdx.files.internal("data/splash.png"));
		splashScreen.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		splash = new TextureRegion(splashScreen, 0, 0, 136, 64);
		splash.flip(false, true);
		
		rainbow1 = new TextureRegion(rainbowA, 0, 0, 136, 200);
		rainbow1.flip(false, true);
		
		rainbow2 = new TextureRegion(rainbowB, 0, 0, 136, 200);
		rainbow2.flip(false, true);
		
		rainbow3 = new TextureRegion(rainbowC, 0, 0, 136, 200);
		rainbow3.flip(false, true);
		
		redShroom = new TextureRegion(shrooms, 0, 0, 10, 10);
		redShroom.flip(false, true);
		
		greenShroom = new TextureRegion(shrooms, 10, 0, 10, 10);
		greenShroom.flip(false, true);
		
		start = new TextureRegion(startButton, 0, 0, 21, 21);
		start.flip(false, true);
		
		highScores = new TextureRegion(scoreSheet, 0, 0, 100, 80);
		highScores.flip(false, true);
		
		score = new TextureRegion(scoreButton, 0, 0, 21, 21);
		score.flip(false, true);
		
		pauseText = new TextureRegion(startText, 0, 0, 61, 10);
		pauseText.flip(false, true);
		
		gameOver = new TextureRegion(gameOverScreen, 0, 0, 100, 80);
		gameOver.flip(false, true);
		
		//Add logo in the future
		
		apple = new TextureRegion(fruit, 10, 0, 10, 9);
		apple.flip(false, true);
		banana = new TextureRegion(fruit, 0, 0, 10, 10);
		banana.flip(false, true);
		orange = new TextureRegion(fruit, 20, 0, 8, 8);
		orange.flip(false, true);
		
		//Creates a new texture using image starting at (0,0) 
		//with 136 width and 43 height.
		background = new TextureRegion(texture, 0, 0, 136, 43);
		background.flip(false, true);
				
		grass = new TextureRegion(texture, 0, 43, 143, 11);
		grass.flip(false, true);
		
		bearDown = new TextureRegion(texture, 136, 16, 18, 16);
		bearDown.flip(false, true);
		
		bear = new TextureRegion(texture, 136, 0, 18, 16);
		bear.flip(false, true);
		
		brick = new TextureRegion(texture, 154, 0, 22, 5);
		brick.flip(false, true);
		
		font = new BitmapFont(Gdx.files.internal("data/bitfont.fnt"));
		font.setScale(.5f, -.5f);
		fontSmall = new BitmapFont(Gdx.files.internal("data/bitfont.fnt"));
		fontSmall.setScale(.30f, -.30f);
		
		TextureRegion[] rainbows = {rainbow1, rainbow2, rainbow3};
		rainbowAnimation = new Animation(0.06f, rainbows);
		rainbowAnimation.setPlayMode(Animation.PlayMode.LOOP);
		
		prefs = Gdx.app.getPreferences("BearCopter");
		
		//if there isnt a file called highScore, secondHighScore, etc,
		//creates one with in initial value of 0
		if(!prefs.contains("highScore")) {
			prefs.putInteger("highScore", 0);
		}
		if(!prefs.contains("secondHighScore")) {
			prefs.putInteger("secondHighScore", 0);
		}
		if(!prefs.contains("thirdHighScore")) {
			prefs.putInteger("thirdHighScore", 0);
		}
	}
	
	public static void setHighScore(int val) {
		prefs.putInteger("highScore", val);
		prefs.flush();
	}
	
	public static void setSecondHighScore(int val) {
		prefs.putInteger("secondHighScore", val);
		prefs.flush();
	}
	
	public static void setThirdHighScore(int val) {
		prefs.putInteger("thirdHighScore", val);
		prefs.flush();
	}
	
	public static int getHighScore() {
		return prefs.getInteger("highScore");
	}
	
	public static int getSecondHighScore() {
		return prefs.getInteger("secondHighScore");
	}
	
	public static int getThirdHighScore() {
		return prefs.getInteger("thirdHighScore");
	}
	
	public static void dispose() {
		texture.dispose();
		startButton.dispose();
		fruit.dispose();
		gameOverScreen.dispose();
		scoreButton.dispose();
		startText.dispose();
		scoreSheet.dispose();
		shrooms.dispose();
		rainbowA.dispose();
		rainbowB.dispose();
		rainbowC.dispose();

		up.dispose();
		dead.dispose();
		BGM.dispose();
		eat.dispose();
		trippy.dispose();
		
		font.dispose();
		fontSmall.dispose();
	}

}
