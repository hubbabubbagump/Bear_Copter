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
import com.hubbabubbagump.Helpers.AssetLoader;
import com.hubbabubbagump.Helpers.InputHandler;
import com.hubbabubbagump.Screens.GameScreen;
import com.hubbabubbagump.UI.MenuButton;
import com.hubbabubbagump.UI.ScoreButton;
import com.hubbabubbagump.UI.StartButton;
import com.hubbabubbagump.GameObjects.Background;
import com.hubbabubbagump.GameObjects.BearCopter;
import com.hubbabubbagump.GameObjects.Dirt;
import com.hubbabubbagump.GameObjects.Fruit;
import com.hubbabubbagump.GameObjects.Grass;
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
	private int midPointY;
	
	//objects
	private BearCopter bear;
	private ScrollHandler scroller;
	private Grass frontGrass, backGrass;
	private Wall wall1, wall2, wall3;
	private Fruit fruit1, fruit2, fruit3;
	private Shroom shroom;
	private Background frontBG, backBG;
	private Dirt frontDirt, backDirt;
	
	
	//assets
	private TextureRegion bg1;
	private TextureRegion bg2;
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
	private TextureRegion apple;
	private TextureRegion banana;
	private TextureRegion orange;
	private TextureRegion gameOver;
	private TextureRegion fruitA, fruitB, fruitC;
	private TextureRegion shroomA;
	private TextureRegion pauseText;
	private TextureRegion scoreRegion;
	private TextureRegion scoreSheet;
	private TextureRegion redShroom;
	private TextureRegion greenShroom;
	private TextureRegion dirt;
	
	private Animation rainbowAnimation;
	
	@SuppressWarnings("unused")
	private List<StartButton> menuButtons;
	@SuppressWarnings("unused")
	private List<ScoreButton> menuScoreButtons;
	private List<MenuButton> menuMenuButtons;
	StartButton button;
	StartButton buttonDown;
	ScoreButton scoreButton;
	ScoreButton scoreButtonDown;
	MenuButton menuButton;
	MenuButton menButtonuDown;
	private int startX;
	private int startY;
	private int scoreX;
	private int scoreY;
	private int menuX;
	private int menuY;
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
	
	private boolean isHigh;
	private int ground = (int) (GameScreen.midScreen() + 66);
	public static final int WALLS = ScrollHandler.WALLS;
	//private Wall[] wall = new Wall[WALLS];
	
	//Creates camera and shapes.
	public GameRenderer(GameWorld world, int gameHeight, int midPointY) {
		myWorld = world;
		
		this.gameHeight = gameHeight;
		this.midPointY = midPointY;
		
		this.menuButtons = InputHandler.getMenuButtons();
		this.menuScoreButtons = InputHandler.getMenuScoreButtons();
		this.menuMenuButtons = InputHandler.getMenuMenuButtons();
		
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
		button = new StartButton(startX, startY, 21, 21, start, startDown);
		scoreButton = new ScoreButton(scoreX, scoreY, 21, 21, scoreRegion, scoreDown);
		menuButton = new MenuButton(menuX, menuY, 21, 21, menu, menuDown);
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
	}
	
	//initializes gameHelpers
	private void initAssets() {
		bg1 = AssetLoader.backgroundLight;
		bg2 = AssetLoader.backgroundDark;
		grass = AssetLoader.grass;
		bearMid = AssetLoader.bear;
		bearDown = AssetLoader.bearDown;
		brick = AssetLoader.brick;
		start = AssetLoader.start;
		scoreRegion = AssetLoader.score;
		apple = AssetLoader.apple;
		banana = AssetLoader.banana;
		orange = AssetLoader.orange;
		gameOver = AssetLoader.gameOver;
		pauseText = AssetLoader.pauseText;
		scoreSheet = AssetLoader.highScores;
		redShroom = AssetLoader.redShroom;
		greenShroom = AssetLoader.greenShroom;
		rainbowAnimation = AssetLoader.rainbowAnimation;
		dirt = AssetLoader.dirt;
		wallBottom = AssetLoader.wallBottom;
		wallTop = AssetLoader.wallTop;
		menu = AssetLoader.menu;
		startDown = AssetLoader.startDown;
		scoreDown = AssetLoader.scoreDown;
		menuDown = AssetLoader.menuDown;
	}
	
	private void drawGrass() {
		batcher.draw(dirt, frontDirt.getX(), frontDirt.getY(), frontDirt.getWidth(), frontDirt.getHeight());
		batcher.draw(dirt, backDirt.getX(), backDirt.getY(), backDirt.getWidth(), backDirt.getHeight());
		
		batcher.draw(grass, frontGrass.getX(), frontGrass.getY(), frontGrass.getWidth(), frontGrass.getHeight());
	    batcher.draw(grass, backGrass.getX(), backGrass.getY(), backGrass.getWidth(), backGrass.getHeight());
	}
	
	private void drawWalls() {
		
		/*for (int i = 0; i < WALLS; i++) {
			batcher.draw(brick, wall[i].getX(), wall[i].getY(), wall[i].getWidth(), wall[i].getHeight());
		}*/
        batcher.draw(brick, wall1.getX(), wall1.getY(), wall1.getWidth(), wall1.getHeight());
        batcher.draw(wallTop, wall1.getX(), wall1.getY(), 22, 5);
        batcher.draw(wallBottom, wall1.getX(), wall1.getY() + wall1.getHeight() - 5, 22, 5);

        batcher.draw(brick, wall2.getX(), wall2.getY(), wall2.getWidth(), wall2.getHeight());
        batcher.draw(wallTop, wall2.getX(), wall2.getY(), 22, 5);
        batcher.draw(wallBottom, wall2.getX(), wall2.getY() + wall2.getHeight() - 5, 22, 5);
      
        batcher.draw(brick, wall3.getX(), wall3.getY(), wall3.getWidth(), wall3.getHeight());
        batcher.draw(wallTop, wall3.getX(), wall3.getY(), 22, 5);
        batcher.draw(wallBottom, wall3.getX(), wall3.getY() + wall3.getHeight() - 5, 22, 5);
      
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
				if (!bear.Alive()) {
					batcher.draw(bearDown, bear.getX(),
		                    bear.getY(), bear.getWidth() / 2.0f,
		                    bear.getHeight() / 2.0f, bear.getWidth(), bear.getHeight(),
		                    1, 1, bear.getRotation());
				}
				else if (bear.notScared()) { 
		            batcher.draw(bearDown, bear.getX(), bear.getY(),
		                    bear.getWidth() / 2.0f, bear.getHeight() / 2.0f,
		                    bear.getWidth(), bear.getHeight(), 1, 1, bear.getRotation());

		        } 
				else {
		            batcher.draw(bearMid, bear.getX(),
		                    bear.getY(), bear.getWidth() / 2.0f,
		                    bear.getHeight() / 2.0f, bear.getWidth(), bear.getHeight(),
		                    1, 1, bear.getRotation());
		        }
	}
	
	public void drawText() {
		batcher.draw(pauseText, (float) 22.25, mid - 40, (float) 91.5, 15);
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
	
	public void drawBG(float runTime) {
		isHigh = GameWorld.isHigh();
		if(!isHigh) {
			if (GameWorld.background() >= 1) {
				batcher.draw(bg2, frontBG.getX(), frontBG.getY(), frontBG.getWidth(), frontBG.getHeight());
				batcher.draw(bg2, backBG.getX(), backBG.getY(), backBG.getWidth(), backBG.getHeight());
			}
			else {
				batcher.draw(bg1, frontBG.getX(), frontBG.getY(), frontBG.getWidth(), frontBG.getHeight());
				batcher.draw(bg1, backBG.getX(), backBG.getY(), backBG.getWidth(), backBG.getHeight());
				
			}
		}
		else if (isHigh) {
			batcher.draw(rainbowAnimation.getKeyFrame(runTime), 0, ground - 200, 136, 200);
		}
	}
	
	//runTime determines which frame the animation should display
	public void render(float delta, float runTime) {
		
		//sets screen to black to prevent flickering
		Gdx.gl.glClearColor(0, 0, 0, 1); //specifies the rgb and alpha values when colors are cleared
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT); //sets the area of the window to values specified in glClearColor
		
		shapeRenderer.begin(ShapeType.Filled);
		
		//Draws background color
		shapeRenderer.setColor(7/255.0f, 140/255.0f, 253/255.0f, 1);
		shapeRenderer.rect(0, 0, 136, midPointY + 66);
		
		//Draws grass
		shapeRenderer.setColor(39/255.0f, 59/255.0f, 10/255.0f, 1);
		shapeRenderer.rect(0, midPointY + 66, 136, 11);
		
		//Draws Dirt
		shapeRenderer.setColor(89/255.0f, 69/255.0f, 18/255.0f, 1);
		shapeRenderer.rect(0,  midPointY + 77, 136, 52);
		
		shapeRenderer.end();
		
		batcher.begin();
		
		//disables transparency
		batcher.disableBlending();
				
		//enables transparency
		batcher.enableBlending();
		
		drawBG(runTime);
		
		if (GameWorld.running()) {
			drawWalls();
			drawBear(runTime);
			drawFruit();
			drawScore();
		}
		else if (myWorld.pause()) {
			drawBear(runTime);
			drawText();
		}
		else if (myWorld.title()) {
			drawBear(runTime);
			drawUI();
		}
		else if (myWorld.gameOver()) {
			drawBear(runTime);
			drawFruit();
			drawWalls();
			drawGameOver();
		}
		else if (myWorld.isScore()) {
			drawBear(runTime);
			drawScoreSheet();
		}
		drawGrass();
		
		batcher.end();
		
		
	}
	
}
