package com.hubbabubbagump.GameWorld;

import java.util.List;

import javax.microedition.khronos.opengles.GL10;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.hubbabubbagump.Helpers.AssetLoader;
import com.hubbabubbagump.Helpers.InputHandler;
import com.hubbabubbagump.Screens.GameScreen;
import com.hubbabubbagump.UI.CharChange;
import com.hubbabubbagump.UI.CharSelect;
import com.hubbabubbagump.UI.MenuButton;
import com.hubbabubbagump.UI.PauseButton;
import com.hubbabubbagump.UI.ScoreButton;
import com.hubbabubbagump.UI.StartButton;
import com.hubbabubbagump.UI.Volume;
import com.hubbabubbagump.GameObjects.Animals;
import com.hubbabubbagump.GameObjects.Background;
import com.hubbabubbagump.GameObjects.BearCopter;
import com.hubbabubbagump.GameObjects.Dirt;
import com.hubbabubbagump.GameObjects.Fruit;
import com.hubbabubbagump.GameObjects.Grass;
import com.hubbabubbagump.GameObjects.Laser;
import com.hubbabubbagump.GameObjects.ScrollHandler;
import com.hubbabubbagump.GameObjects.Shroom;
import com.hubbabubbagump.GameObjects.Wall;


public class GameRenderer {

	private GameWorld myWorld;
	private OrthographicCamera cam;
	private ShapeRenderer shapeRenderer;
	
	//draws images using (x,y) provided
	private SpriteBatch batcher;

	@SuppressWarnings("unused")
	private int gameHeight;
	
	//objects
	private BearCopter bear;
	private ScrollHandler scroller;
	private Grass frontGrass, backGrass;
	private Wall wall1, wall2, wall3;
	private Fruit fruit1, fruit2, fruit3;
	private Shroom shroom;
	private Background frontBG, backBG;
	private Dirt frontDirt, backDirt;
	private Laser laser;
	private Animals penguins, rams, toasts, cats;
	
	
	//assets
	private TextureRegion BG1, BG2, BG3;
	private TextureRegion bg1;
	private TextureRegion bg12;
	private TextureRegion bg13;
	private TextureRegion bg2;
	private TextureRegion bg22;
	private TextureRegion bg23;
	private TextureRegion bgEnd;
	private TextureRegion bgFront;
	private TextureRegion bgBack;
	private TextureRegion grass;
	private TextureRegion start;
	private TextureRegion menu;
	private TextureRegion startDown;
	private TextureRegion scoreDown;
	private TextureRegion menuDown;
	private TextureRegion bearMid, bearDown;
	private TextureRegion brick;
	private TextureRegion wallTop;
	private TextureRegion wallBottom;
	private TextureRegion bar;
	private TextureRegion barTop;
	private TextureRegion barBottom;
	private TextureRegion apple;
	private TextureRegion banana;
	private TextureRegion orange;
	private TextureRegion gameOver;
	private TextureRegion fruitA, fruitB, fruitC;
	private TextureRegion shroomA;
	private TextureRegion scoreRegion;
	private TextureRegion scoreSheet;
	private TextureRegion redShroom;
	private TextureRegion greenShroom;
	private TextureRegion dirt;
	private TextureRegion beam;
	private TextureRegion beamplosion;
	private TextureRegion burntWall;
	private TextureRegion bearCopter;
	private TextureRegion pauseText;
	private TextureRegion bigBeam;
	private TextureRegion ice1, ice2, ice3;
	private TextureRegion cave1, cave2, cave3;
	private TextureRegion city1, city2, city3;
	private TextureRegion cat, catdown;
	private TextureRegion ram, ramdown;
	private TextureRegion penguin, penguindown;
	private TextureRegion toast, toastdown;
	private TextureRegion volumeON, volumeOFF;
	private TextureRegion pause;
	private TextureRegion charButton, charButtonDown;
	private TextureRegion avatar, avatarDown;
	private TextureRegion charBorder;
	private TextureRegion icedirt, iceground;
	private TextureRegion dirtTexture, grassTexture;
	private TextureRegion grass2, grassdark;
	private TextureRegion cage;
	private TextureRegion story;
	
	private Animation rainbowAnimation;
	//private Animation bearBlink;
	//private Animation downBlink;
	
	
	@SuppressWarnings("unused")
	private List<StartButton> menuButtons;
	@SuppressWarnings("unused")
	private List<ScoreButton> menuScoreButtons;
	@SuppressWarnings("unused")
	private List<MenuButton> menuMenuButtons;
	@SuppressWarnings("unused")
	private List<Volume> volumeButtons;
	StartButton button;
	StartButton buttonDown;
	ScoreButton scoreButton;
	ScoreButton scoreButtonDown;
	MenuButton menuButton;
	MenuButton menButtonuDown;
	Volume volumeButton;
	PauseButton pauseButton;
	CharSelect charbutton;
	CharChange bearButton;
	CharChange penguinButton;
	CharChange ramButton;
	CharChange toastButton;
	CharChange catButton;
	private int startX;
	private int startY;
	private int scoreX;
	private int scoreY;
	private int menuX;
	private int menuY;
	private int volumeX;
	private int volumeY;
	private float pauseX;
	private int pauseY;
	private int charX;
	private int charY;
	private int score;
	private int combo;
	private int highScore;
	private int secondHighScore;
	private int thirdHighScore;
	private static final float mid = GameScreen.midScreen();
	
	String strScore;
	String strHighScore;
	String strSecondHS;
	String strThirdHS;
	
	private Vector2 time1;
	private Vector2 time2;
	private Vector2 time3;
	private Vector2 timeRate;
	private static final int TIMERATE = 1;
	
	private boolean isHigh;
	private int ground = (int) (GameScreen.midScreen() + 66);
	public static final int WALLS = ScrollHandler.WALLS;
	//private Wall[] wall = new Wall[WALLS];
	
	public static float VOLUME = GameWorld.VOLUME;
	private boolean pewOnce1 = false;
	private boolean pewOnce2 = false;
	private boolean pewOnce3 = false;
	private boolean pewOnceHorizontal = false;
	
	private float midPointY = GameScreen.midScreen();
	private float middle = midPointY - 15;
	private float charTop = middle - 30;
	private float charBottom = middle + 30;
	private float XRIGHT = 136 / 4 * 3 - (AssetLoader.bear.getRegionWidth() / 2);
	private float XMID = 136 / 2 - (AssetLoader.bear.getRegionWidth() / 2);
	private float XLEFT = 136 / 4 - (AssetLoader.bear.getRegionWidth() / 2);
	
	public static float i = 136;
	public static float j = 136;

	
	//Creates camera and shapes.
	public GameRenderer(GameWorld world, int gameHeight, int midPointY) {
		myWorld = world;
		
		this.gameHeight = gameHeight;
		this.midPointY = midPointY;
		
		this.menuButtons = InputHandler.getMenuButtons();
		this.menuScoreButtons = InputHandler.getMenuScoreButtons();
		this.menuMenuButtons = InputHandler.getMenuMenuButtons();
		this.volumeButtons = InputHandler.getVolumeButtons();
		
		cam = new OrthographicCamera();
		cam.setToOrtho(true, 136, gameHeight);
		batcher = new SpriteBatch();
		//Attaches the batcher to the camera
		batcher.setProjectionMatrix(cam.combined);
		
		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setProjectionMatrix(cam.combined);
		
		initGameObjects();
		initAssets();
		startX = StartButton.getX();
		startY = StartButton.getY();
		scoreX = ScoreButton.getX();
		scoreY = ScoreButton.getY();
		menuX = MenuButton.getX();
		menuY = MenuButton.getY();
		volumeX = Volume.getX();
		volumeY = Volume.getY();
		pauseX = PauseButton.getX();
		pauseY = PauseButton.getY();
		charX = CharSelect.getX();
		charY = CharSelect.getY();
		button = new StartButton(startX, startY, 21, 21, start, startDown);
		scoreButton = new ScoreButton(scoreX, scoreY, 21, 21, scoreRegion, scoreDown);
		menuButton = new MenuButton(menuX, menuY, 21, 21, menu, menuDown);
		volumeButton = new Volume(volumeX, volumeY, 15, 15, volumeON, volumeOFF);
		pauseButton = new PauseButton(pauseX, pauseY, 11, 11, pause);
		charbutton = new CharSelect(charX, charY, 21, 21, charButton, charButtonDown);
		
		bearButton = new CharChange(XMID, middle, 18, 14, bearMid);
		penguinButton = new CharChange(XLEFT, charTop, 18, 14, penguin);
		ramButton = new CharChange(XRIGHT, charTop, 18, 14, ram);
		toastButton = new CharChange(XLEFT, charBottom, 18, 14, toast);
		catButton = new CharChange(XRIGHT, charBottom, 18, 14, cat);
		
		time1 = new Vector2(0, 0);
		time2 = new Vector2(0, 0);
		time3 = new Vector2(0, 0);
		timeRate = new Vector2(TIMERATE, 0);
	}
	
	//Initializes gameObjects
	private void initGameObjects() {
		bear = myWorld.getBear();
		scroller = myWorld.getScroller();
		frontGrass = scroller.getFrontGrass();
		backGrass = scroller.getBackGrass();
		wall1 = scroller.getWall1();
		wall2 = scroller.getWall2();
		wall3 = scroller.getWall3();
		fruit1 = scroller.getFruit1();
		fruit2 = scroller.getFruit2();
		fruit3 = scroller.getFruit3();
		shroom = scroller.getShroom();
		frontBG = scroller.getFrontBG();
		backBG = scroller.getBackBG();
		frontDirt = scroller.getFrontDirt();
		backDirt = scroller.getBackDirt();
		laser = scroller.getLaser();
		penguins = scroller.getPenguin();
		rams = scroller.getRam();
		toasts = scroller.getToast();
		cats = scroller.getCat();
	}
	
	//initializes gameHelpers
	private void initAssets() {
		
		bearCopter = AssetLoader.bearCopter;
		
		bg1 = AssetLoader.backgroundLight;
		bg12 = AssetLoader.backgroundLight2;
		bg13 = AssetLoader.backgroundLight3;
		bg2 = AssetLoader.backgroundDark;
		bg22 = AssetLoader.backgroundDark2;
		bg23 = AssetLoader.backgroundDark3;
		bgEnd = AssetLoader.backgroundDungeon;
		cave1 = AssetLoader.cave1;
		cave2 = AssetLoader.cave2;
		cave3 = AssetLoader.cave3;
		city1 = AssetLoader.city1;
		city2 = AssetLoader.city2;
		city3 = AssetLoader.city3;
		ice1 = AssetLoader.ice1;
		ice2 = AssetLoader.ice2;
		ice3 = AssetLoader.ice3;
		rainbowAnimation = AssetLoader.rainbowAnimation;
		
		grass = AssetLoader.grass;
		grass2 = AssetLoader.grass2;
		grassdark = AssetLoader.grassdark;
		dirt = AssetLoader.dirt;
		iceground = AssetLoader.iceground;
		icedirt = AssetLoader.icedirt;
		
		bearMid = AssetLoader.bear;
		bearDown = AssetLoader.bearDown;
		//bearBlink = AssetLoader.bearBlink;
		//downBlink = AssetLoader.downBlink;
		cat = AssetLoader.cat;
		catdown = AssetLoader.catdown;
		ram = AssetLoader.ram;
		ramdown = AssetLoader.ramdown;
		penguin = AssetLoader.penguin;
		penguindown = AssetLoader.penguindown;
		toast = AssetLoader.toast;
		toastdown = AssetLoader.toastdown;
		charBorder = AssetLoader.charBorder;
		cage = AssetLoader.cage;
		
		apple = AssetLoader.apple;
		banana = AssetLoader.banana;
		orange = AssetLoader.orange;
		redShroom = AssetLoader.redShroom;
		greenShroom = AssetLoader.greenShroom;
		
		gameOver = AssetLoader.gameOver;
		scoreSheet = AssetLoader.highScores;
		pauseText = AssetLoader.pauseText;
		
		wallBottom = AssetLoader.wallBottom;
		wallTop = AssetLoader.wallTop;
		burntWall = AssetLoader.burntWall;
		brick = AssetLoader.brick;
		
		beam = AssetLoader.beam;
		beamplosion = AssetLoader.beamplosion;
		bigBeam = AssetLoader.bigBeam;
		
		bar = AssetLoader.bar;
		barTop = AssetLoader.barTop;
		barBottom = AssetLoader.barBottom;
		
		start = AssetLoader.start;
		scoreRegion = AssetLoader.score;
		menu = AssetLoader.menu;
		startDown = AssetLoader.startDown;
		scoreDown = AssetLoader.scoreDown;
		menuDown = AssetLoader.menuDown;
		charButton = AssetLoader.charButton;
		charButtonDown = AssetLoader.charButtonDown;
		
		volumeON = AssetLoader.volumeON;
		volumeOFF = AssetLoader.volumeOFF;
		
		pause = AssetLoader.pause;
		
		story = AssetLoader.story;
		
	}
	
	private void drawGrass() {
		if (AssetLoader.getCurrentAvatar() == 1) {
			dirtTexture = icedirt;
			grassTexture = iceground;
		}
		else if (AssetLoader.getCurrentAvatar() == 2) {
			dirtTexture = dirt;
			grassTexture = grass2;
		}
		else if (AssetLoader.getCurrentAvatar() == 0) {
			dirtTexture = dirt;
			grassTexture = grass;
		}
		else {
			dirtTexture = dirt;
			grassTexture = grassdark;
		}
		batcher.draw(dirtTexture, frontDirt.getX(), frontDirt.getY(), frontDirt.getWidth(), frontDirt.getHeight());
		batcher.draw(dirtTexture, backDirt.getX(), backDirt.getY(), backDirt.getWidth(), backDirt.getHeight());
		
		batcher.draw(grassTexture, frontGrass.getX(), frontGrass.getY(), frontGrass.getWidth(), frontGrass.getHeight());
	    batcher.draw(grassTexture, backGrass.getX(), backGrass.getY(), backGrass.getWidth(), backGrass.getHeight());
	}
	
	private void drawWalls(float delta) {
		if (wall1.wallLevel == 1) {
	        batcher.draw(brick, wall1.getX(), wall1.getY(), wall1.getWidth(), wall1.getHeight());
	        if (!wall1.isDestroy()) {
	        	batcher.draw(wallTop, wall1.getX(), wall1.getY(), 22, 5);
	        }
	        else {
	        	batcher.draw(burntWall, wall1.getX(), wall1.getY(), 22, 5);
	        }
	        batcher.draw(wallBottom, wall1.getX(), wall1.getY() + wall1.getHeight() - 5, 22, 5);
	        if (wall1.isDestroy() && time1.x <= 0.6) {
	        	time1.add(timeRate.cpy().scl(delta));
	        	batcher.draw(beam, wall1.getX() + 11, 0, 2, wall1.getY());
	        	batcher.draw(beamplosion, wall1.getX() + 8, wall1.getY() - 5, 8, 5);
	        	if (!pewOnce1) {
		        	AssetLoader.pew.play(VOLUME);
					
					pewOnce1 = true;
	        	}
	        }
	        if (wall1.getX() >= 136) {
	        	time1.x = 0;
	        	pewOnce1 = false;
	        }
		}
		else if (wall1.wallLevel == 2) {
			batcher.draw(bar, wall1.getX(), wall1.getY(), wall1.getWidth(), wall1.getHeight());
			batcher.draw(barTop, wall1.getX(), wall1.getY(), 22, 5);
			 batcher.draw(barBottom, wall1.getX(), wall1.getY() + wall1.getHeight() - 5, 22, 5);
			 if (wall1.isDestroy() && time1.x <= 0.6) {
		        	time1.add(timeRate.cpy().scl(delta));
		        	batcher.draw(beam, wall1.getX() + 11, 0, 2, wall1.getY());
		        	batcher.draw(beamplosion, wall1.getX() + 8, wall1.getY() - 5, 8, 5);
		        	if (!pewOnce1) {
			        	AssetLoader.pew.play(VOLUME);
						
						pewOnce1 = true;
		        	}
		        }
		        if (wall1.getX() >= 136) {
		        	time1.x = 0;
		        	pewOnce1 = false;
		        }
		}
        
		if (wall2.wallLevel == 1) {
	        batcher.draw(brick, wall2.getX(), wall2.getY(), wall2.getWidth(), wall2.getHeight());
	        if (!wall2.isDestroy()) {
	        	batcher.draw(wallTop, wall2.getX(), wall2.getY(), 22, 5);
	        }
	        else {
	        	batcher.draw(burntWall, wall2.getX(), wall2.getY(), 22, 5);
	        }
	        batcher.draw(wallBottom, wall2.getX(), wall2.getY() + wall2.getHeight() - 5, 22, 5);
	        if (wall2.isDestroy() && time2.x <= 0.6) {
	        	time2.add(timeRate.cpy().scl(delta));
	        	batcher.draw(beam, wall2.getX() + 11, 0, 2, wall2.getY());
	        	batcher.draw(beamplosion, wall2.getX() + 8, wall2.getY() - 5, 8, 5);
	        	if (!pewOnce2) {
		        	AssetLoader.pew.play(VOLUME);
					
					pewOnce2 = true;
	        	}
	        }
	        if (wall2.getX() >= 136) {
	        	time2.x = 0;
	        	pewOnce2 = false;
	        }
		}
		else if (wall2.wallLevel == 2) {
			batcher.draw(bar, wall2.getX(), wall2.getY(), wall2.getWidth(), wall2.getHeight());
			batcher.draw(barTop, wall2.getX(), wall2.getY(), 22, 5);
			batcher.draw(barBottom, wall2.getX(), wall2.getY() + wall2.getHeight() - 5, 22, 5);
			if (wall2.isDestroy() && time2.x <= 0.6) {
	        	time2.add(timeRate.cpy().scl(delta));
	        	batcher.draw(beam, wall2.getX() + 11, 0, 2, wall2.getY());
	        	batcher.draw(beamplosion, wall2.getX() + 8, wall2.getY() - 5, 8, 5);
	        	if (!pewOnce2) {
		        	AssetLoader.pew.play(VOLUME);
					
					pewOnce2 = true;
	        	}
	        }
	        if (wall2.getX() >= 136) {
	        	time2.x = 0;
	        	pewOnce2 = false;
	        }
		}
		
		if(wall3.wallLevel == 1) {
	        batcher.draw(brick, wall3.getX(), wall3.getY(), wall3.getWidth(), wall3.getHeight());
	        if (!wall3.isDestroy()) {
	        	batcher.draw(wallTop, wall3.getX(), wall3.getY(), 22, 5);
	        }
	        else {
	        	batcher.draw(burntWall, wall3.getX(), wall3.getY(), 22, 5);
	        }
	        batcher.draw(wallBottom, wall3.getX(), wall3.getY() + wall3.getHeight() - 5, 22, 5);
	        if (wall3.isDestroy() && time3.x <= 0.6) {
	        	time3.add(timeRate.cpy().scl(delta));
	        	batcher.draw(beam, wall3.getX() + 11, 0, 2, wall3.getY());
	        	batcher.draw(beamplosion, wall3.getX() + 8, wall3.getY() - 5, 8, 5);
	        	if (!pewOnce3) {
		        	AssetLoader.pew.play(VOLUME);
					
					pewOnce3 = true;
		        }
	        }
	        if (wall3.getX() >= 136) {
	        	time3.x = 0;
	        	pewOnce3 = false;
	        }
		}
		else if (wall3.wallLevel == 2) {
			batcher.draw(bar, wall3.getX(), wall3.getY(), wall3.getWidth(), wall3.getHeight());
			batcher.draw(barTop, wall3.getX(), wall3.getY(), 22, 5);
	        batcher.draw(barBottom, wall3.getX(), wall3.getY() + wall3.getHeight() - 5, 22, 5);
	        if (wall3.isDestroy() && time3.x <= 0.6) {
	        	time3.add(timeRate.cpy().scl(delta));
	        	batcher.draw(beam, wall3.getX() + 11, 0, 2, wall3.getY());
	        	batcher.draw(beamplosion, wall3.getX() + 8, wall3.getY() - 5, 8, 5);
	        	if (!pewOnce3) {
		        	AssetLoader.pew.play(VOLUME);
					
					pewOnce3 = true;
		        }
	        }
	        if (wall3.getX() >= 136) {
	        	time3.x = 0;
	        	pewOnce3 = false;
	        }

		}
    }
	
	private void drawScore() {
		score = GameWorld.getScore();
		combo = BearCopter.COMBO;
		String multiplier;
		strScore = score + "";
		AssetLoader.font.draw(batcher, "" + strScore, 130 - (11 * strScore.length()), 5);
		
		
		
		if (myWorld.gameOver()) {
			highScore = AssetLoader.getHighScore();
			strHighScore = "" + highScore;
			AssetLoader.font.draw(batcher, "" + strHighScore, 130 - (11 * strHighScore.length()), midPointY + 80);
		}
		
		//draws the combo numbers
		if (combo >= BearCopter.EXCELLENT_THRESHOLD) {
			multiplier = "4x";
		}
		else if (combo >= BearCopter.GREAT_THRESHOLD) {
			multiplier = "3x";
		}
		else if (combo >= BearCopter.GOOD_THRESHOLD) {
			multiplier = "2x";
		}
		else {
			multiplier = "1x";
		}
		String strCombo = "" + combo;
		if (!myWorld.gameOver()) {
			AssetLoader.font.draw(batcher, strCombo, 21, 5);
			AssetLoader.fontSmall.draw(batcher, multiplier, 6, 10);
			//AssetLoader.fontSmall.draw(batcher, gunfrags, 6, 17);
		}
		
	}
	
	private void drawGameOver() {
		score = GameWorld.getScore();
		strScore = score + "";
		highScore = AssetLoader.getHighScore();
		strHighScore = "" + highScore;
		
		batcher.draw(gameOver, 18, mid - 40, 100, 80);
		AssetLoader.fontSmall.draw(batcher, strScore, (float) (44 - (3.333333 * strScore.length())), mid - 13);
		AssetLoader.fontSmall.draw(batcher, strHighScore, (float) (90 - (3.33333333 * strHighScore.length())), mid - 13);
		
		if (GameWorld.menuDown) {
			menuButton.drawDown(batcher);
		}
		else {
			menuButton.draw(batcher);
		}
	}

	private void drawUI() {
		if (GameWorld.startDown) {
			button.drawDown(batcher);
		}
		else {
			button.draw(batcher);
		}
		
		if (GameWorld.scoreDown) {
			scoreButton.drawDown(batcher);
		}
		else {
			scoreButton.draw(batcher);
		}
		
		if (GameWorld.VOLUME == 0) {
			volumeButton.drawOFF(batcher);
		}
		else if (GameWorld.VOLUME > 0) {
			volumeButton.drawON(batcher);
		}
		
		if (GameWorld.charDown) {
			charbutton.drawDown(batcher);
			///////
		}
		else {
			charbutton.draw(batcher);
		}
		
	}
	
	private void drawScoreSheet() {
		highScore = AssetLoader.getHighScore();
		strHighScore = "" + highScore;
		
		secondHighScore = AssetLoader.getSecondHighScore();
		strSecondHS = secondHighScore + "";
		
		thirdHighScore = AssetLoader.getThirdHighScore();
		strThirdHS = thirdHighScore + "";
		
		batcher.draw(scoreSheet, 18, mid - 40, 100, 80);
		AssetLoader.font.draw(batcher, strHighScore, (float) (76.5 - (5.5 * strHighScore.length())), (float) (mid - 17.5));
		AssetLoader.font.draw(batcher, strSecondHS, (float) (76.5 - (5.5 * strSecondHS.length())), (float) (mid - 2));
		AssetLoader.font.draw(batcher, strThirdHS, (float) (76.5 - (5.5 * strThirdHS.length())), (float) (mid + 14.5));
	}
	
	private void drawBear(float runTime) {
		//Draws bear at coordinates
				//Retrieves the animation object form assetLoader
				//passes in the runTime value in order to determine which frame to draw
		
				//Determines which avatar to draw
				if (AssetLoader.getCurrentAvatar() == 0) {
					avatar = bearMid;
					avatarDown = bearDown;
				}
				else if (AssetLoader.getCurrentAvatar() == 1) {
					avatar = penguin;
					avatarDown = penguindown;
				}
				else if (AssetLoader.getCurrentAvatar() == 2) {
					avatar = ram;
					avatarDown = ramdown;
				}
				else if (AssetLoader.getCurrentAvatar() == 3) {
					avatar = toast;
					avatarDown = toastdown;
				}
				else if (AssetLoader.getCurrentAvatar() == 4) {
					avatar = cat;
					avatarDown = catdown;
				}
				
				if (!bear.Alive()) {
					batcher.draw(avatarDown, bear.getX(),
		                    bear.getY(), bear.getWidth() / 2.0f,
		                    bear.getHeight() / 2.0f, bear.getWidth(), bear.getHeight(),
		                    1, 1, bear.getRotation());
				}
				else if (bear.notScared()) { 
					
						batcher.draw(avatarDown, bear.getX(), bear.getY(),
								bear.getWidth() / 2.0f, bear.getHeight() / 2.0f,
			                    bear.getWidth(), bear.getHeight(), 1, 1, bear.getRotation());
				 
		        } 
				else {
					
						batcher.draw(avatar, bear.getX(),
		                    bear.getY(), bear.getWidth() / 2.0f,
		                    bear.getHeight() / 2.0f, bear.getWidth(), bear.getHeight(),
		                    1, 1, bear.getRotation());
					
		        }
				
	}
	
	public void drawLogo() {
		batcher.draw(bearCopter, (float) 5.5, mid - 30, 125, 12);
	}
	
	public void drawText() {
		batcher.draw(pauseText, (float) 37.5, mid - 30, 61, 10);
	}
	
	public void drawPauseText() {
		AssetLoader.font.draw(batcher, "Paused", 34, mid - 30);
	}
	
	public void drawLevels() {
		if (GameWorld.LEVEL == 2 && i >= -200) {
			AssetLoader.font.draw(batcher, "Level 2", i, 30);
			i--;
		}
		if (GameWorld.LEVEL == 4 && j >= -200 && (backBG.LEVEL == 4 || frontBG.LEVEL == 4)) {
			AssetLoader.font.draw(batcher, "Level 3", j, 30);
			j--;
		}
	}
	
	public void drawFruit() {
		//only renders the fruit if the bear hasn't "eaten" it
		if(!fruit1.isEaten()) {
			//checks to see which fruit to render
			if(fruit1.fruitNumber() == 0) {
				fruitA = apple;
			}
			else if(fruit1.fruitNumber() == 1) {
				fruitA = banana;
			}
			else {
				fruitA = orange;
			}
			batcher.draw(fruitA, fruit1.getX(), fruit1.getY(), fruit1.getWidth(), fruit1.getHeight());
		}
		
		if(!fruit2.isEaten()) {
			if(fruit2.fruitNumber() == 0) {
				fruitB = apple;
			}
			else if(fruit2.fruitNumber() == 1) {
				fruitB = banana;
			}
			else {
				fruitB = orange;
			}
			batcher.draw(fruitB, fruit2.getX(), fruit2.getY(), fruit2.getWidth(), fruit2.getHeight());
		}
	
		if(!fruit3.isEaten()) {
			if(fruit3.fruitNumber() == 0) {
				fruitC = apple;
			}
			else if(fruit3.fruitNumber() == 1) {
				fruitC = banana;
			}
			else {
				fruitC = orange;
			}
			batcher.draw(fruitC, fruit3.getX(), fruit3.getY(), fruit3.getWidth(), fruit3.getHeight());
		}
	
		if(!shroom.isEaten()) {
			if (shroom.shroomNumber() == 0) {
				shroomA = redShroom;
			}
			else {
				shroomA = greenShroom;
			}
			batcher.draw(shroomA, shroom.getX(), shroom.getY(), shroom.getWidth(), shroom.getHeight());
		}
		
	}
	
	public void drawAnimals() {
		if(!penguins.isEaten()) {
			batcher.draw(penguin, penguins.getX(), penguins.getY(), penguins.getWidth(), penguins.getHeight());
			batcher.draw(cage, penguins.getX() - 1, penguins.getY() - 1, 15, 14);
		}
		if(!rams.isEaten()) {
			batcher.draw(ram, rams.getX(), rams.getY(), rams.getWidth(), rams.getHeight());
			batcher.draw(cage, rams.getX() - 1, rams.getY() - 1, 15, 14);

		}
		if(!toasts.isEaten()) {
			batcher.draw(toast, toasts.getX(), toasts.getY(), toasts.getWidth(), toasts.getHeight());
			batcher.draw(cage, toasts.getX() - 1, toasts.getY() - 1, 15, 14);
		}
		if(!cats.isEaten()) {
			batcher.draw(cat, cats.getX(), cats.getY(), cats.getWidth(), cats.getHeight());
			batcher.draw(cage, cats.getX() - 1, cats.getY() - 1, 15, 14);
		}
	}
	
	public void drawBG(float runTime) {
		isHigh = GameWorld.isHigh();
		if(!isHigh) {
			if (AssetLoader.getCurrentAvatar() == 0) {
				BG1 = bg1;
				BG2 = bg12;
				BG3 = bg13;
			}
			else if (AssetLoader.getCurrentAvatar() == 1) {
				BG1 = ice1;
				BG2 = ice2;
				BG3 = ice3;
			}	
			else if (AssetLoader.getCurrentAvatar() == 2) {
				BG1 = cave1;
				BG2 = cave2;
				BG3 = cave3;
			}
			else if (AssetLoader.getCurrentAvatar() == 3) {
				BG1 = bg2;
				BG2 = bg22;
				BG3 = bg23;
			}
			else if (AssetLoader.getCurrentAvatar() == 4) {
				BG1 = city1;
				BG2 = city2;
				BG3 = city3;
			}
				
				
			if (frontBG.LEVEL == 1) {
				bgFront = BG1;
			}
			else if (frontBG.LEVEL == 2) {
				bgFront = BG2;
			}
			else if (frontBG.LEVEL == 3) {
				bgFront = BG3;
			}
			else if (frontBG.LEVEL == 4) {
				bgFront = bgEnd;
			}
			
			if (backBG.LEVEL == 1) {
				bgBack = BG1;
			}
			else if (backBG.LEVEL == 2) {
				bgBack = BG2;
			}
			else if (backBG.LEVEL == 3) {
				bgBack = BG3;
			}
			else if (backBG.LEVEL == 4) {
				bgBack = bgEnd;
			}
			
			batcher.draw(bgFront, frontBG.getX(), frontBG.getY(), frontBG.getWidth(), frontBG.getHeight());
			batcher.draw(bgBack, backBG.getX(), backBG.getY(), backBG.getWidth(), backBG.getHeight());
			
				
				
			
		}
		else if (isHigh) {
			batcher.draw(rainbowAnimation.getKeyFrame(runTime), 0, ground - 200, 136, 200);
		}
	}
	
	public void drawLaser() {
		if (laser.isFiring()) {
			batcher.draw(beam, 0, laser.getY() + 1, 136, 1);
			pewOnceHorizontal = false;
		}
		else if (laser.isFire()) {
			batcher.draw(bigBeam, 0, laser.getY(), 136, 3);
			if (!pewOnceHorizontal) {
				AssetLoader.pew.play(VOLUME);
				
				pewOnceHorizontal = true;
			}
		}
		else {
			pewOnceHorizontal = false;
		}
	}
	
	public void drawCharSelect() {
		bearButton.draw(batcher);
		if (AssetLoader.penguinUnlocked()) {
			penguinButton.draw(batcher);
		}
		if (AssetLoader.ramUnlocked()) {
			ramButton.draw(batcher);
		}
		if (AssetLoader.toastUnlocked()) {
			toastButton.draw(batcher);
		}
		if (AssetLoader.catUnlocked()) {
			catButton.draw(batcher);
		}
		
		if (AssetLoader.getCurrentAvatar() == 0) {
			batcher.draw(charBorder, (float) (XMID - 2.5), (float) (middle - 4.5), 23, 23);
		}
		else if (AssetLoader.getCurrentAvatar() == 1) {
			batcher.draw(charBorder, (float) (XLEFT - 2.5), (float) (charTop - 4.5), 23, 23);
		}
		else if (AssetLoader.getCurrentAvatar() == 2) {
			batcher.draw(charBorder, (float) (XRIGHT - 2.5), (float) (charTop - 4.5), 23, 23);
		}
		else if (AssetLoader.getCurrentAvatar() == 3) {
			batcher.draw(charBorder, (float) (XLEFT - 2.5), (float) (charBottom - 4.5), 23, 23);
		}
		else if (AssetLoader.getCurrentAvatar() == 4) {
			batcher.draw(charBorder, (float) (XRIGHT - 2.5), (float) (charBottom - 4.5), 23, 23);
		}
	}
	
	public void drawStory() {
		batcher.draw(story, 0, 0, 136, GameScreen.GAMEHEIGHT);
		
	
	}
	
	//runTime determines which frame the animation should display
	public void render(float delta, float runTime) {
		VOLUME = GameWorld.VOLUME;
		//sets screen to black to prevent flickering
		Gdx.gl.glClearColor(0, 0, 0, 1); //specifies the rgb and alpha values when colors are cleared
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT); //sets the area of the window to values specified in glClearColor
		
		shapeRenderer.begin(ShapeType.Filled);
		
		//Draws background color
		if (AssetLoader.getCurrentAvatar() == 0) {
			shapeRenderer.setColor(182/255.0f, 56/255.0f, 126/255.0f, 1);
			shapeRenderer.rect(0, 0, 136, midPointY + 66);
		}
		else {
			shapeRenderer.setColor(0/255.0f, 0/255.0f, 0/255.0f, 1);
			shapeRenderer.rect(0, 0, 136, GameScreen.GAMEHEIGHT);
		}
		
		if (!myWorld.isStory()) {
			//Draws grass
			shapeRenderer.setColor(39/255.0f, 59/255.0f, 10/255.0f, 1);
			shapeRenderer.rect(0, midPointY + 66, 136, 11);
			
			//Draws Dirt
			shapeRenderer.setColor(89/255.0f, 69/255.0f, 18/255.0f, 1);
			shapeRenderer.rect(0,  midPointY + 77, 136, 52);
		}
		
		shapeRenderer.end();
		
		batcher.begin();
		
		//disables transparency
		batcher.disableBlending();
				
		//enables transparency
		batcher.enableBlending();
		
		if (!myWorld.isStory()) {
			drawBG(runTime);
		}
		
		if (GameWorld.running()) {
			drawWalls(delta);
			drawLaser();
			drawBear(runTime);
			pauseButton.draw(batcher);
			drawFruit();
			drawAnimals();
			drawLevels();
			drawScore();
		}
		else if (myWorld.isPaused()) {
			drawWalls(delta);
			drawLaser();
			drawBear(runTime);
			
			drawFruit();
			pauseButton.draw(batcher);
			drawPauseText();
			drawScore();
		}
		else if (myWorld.pause()) {
			drawBear(runTime);
			drawText();
			i = 136;
			j = 136;
		}
		else if (myWorld.title()) {
			drawBear(runTime);
			drawLogo();
			drawUI();
		}
		else if (myWorld.gameOver()) {
			drawBear(runTime);
			
			drawFruit();
			drawWalls(delta);
			drawAnimals();
			drawGameOver();
		}
		else if (myWorld.isScore()) {
			drawBear(runTime);
			drawScoreSheet();
		}
		else if (myWorld.isCharSelect()) {
			drawCharSelect();
		}
		if (!myWorld.isStory()) {
			drawGrass();
		}
		if (myWorld.isStory()) {
			drawStory();
		}
		if (myWorld.title()) {
			System.out.println(":3");
			AssetLoader.fontSmall.draw(batcher, "Music by d-orbital", 12, GameScreen.GAMEHEIGHT - 10);
		}
		
		batcher.end();
		
		
	}
	
}
