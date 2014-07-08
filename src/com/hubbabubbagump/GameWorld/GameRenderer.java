package com.hubbabubbagump.GameWorld;

import javax.microedition.khronos.opengles.GL10;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
//import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.hubbabubbagump.Helpers.AssetLoader;
import com.hubbabubbagump.GameObjects.BearCopter;
import com.hubbabubbagump.GameObjects.Grass;
import com.hubbabubbagump.GameObjects.ScrollHandler;
import com.hubbabubbagump.GameObjects.Wall;


public class GameRenderer {

	private GameWorld myWorld;
	private OrthographicCamera cam;
	private ShapeRenderer shapeRenderer;
	
	//draws images using (x,y) provided
	private SpriteBatch batcher;

	private int gameHeight;
	private int midPointY;
	
	//objects
	private BearCopter bear;
	private ScrollHandler scroller;
	private Grass frontGrass, backGrass;
	private Wall wall1, wall2, wall3;
	
	//assets
	private TextureRegion bg, grass;
	//private Animation bearAnimation;
	private TextureRegion bearMid, bearDown;
	private TextureRegion brick;
	
	//Creates camera and shapes.
	public GameRenderer(GameWorld world, int gameHeight, int midPointY) {
		myWorld = world;
		
		this.gameHeight = gameHeight;
		this.midPointY = midPointY;
		
		cam = new OrthographicCamera();
		cam.setToOrtho(true, 136, gameHeight);
		
		batcher = new SpriteBatch();
		//Attaches the batcher to the camera
		batcher.setProjectionMatrix(cam.combined);
		
		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setProjectionMatrix(cam.combined);
		
		initGameObjects();
		initAssets();
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
	}
	
	//initializes gameHelpers
	private void initAssets() {
		bg = AssetLoader.background;
		grass = AssetLoader.grass;
		//bearAnimation = AssetLoader.bearAnimation;
		bearMid = AssetLoader.bear;
		bearDown = AssetLoader.bearDown;
		brick = AssetLoader.brick;
	}
	
	private void drawGrass() {
		batcher.draw(grass, frontGrass.getX(), frontGrass.grassGetY(), frontGrass.getWidth(), frontGrass.getHeight());
	    batcher.draw(grass, backGrass.getX(), backGrass.grassGetY(), backGrass.getWidth(), backGrass.getHeight());
	}
	
	private void drawWalls() {
	
        batcher.draw(brick, wall1.getX(), wall1.getY(), wall1.getWidth(), wall1.getHeight());

        batcher.draw(brick, wall2.getX(), wall2.getY(), wall2.getWidth(), wall2.getHeight());
      
        batcher.draw(brick, wall3.getX(), wall3.getY(), wall3.getWidth(), wall3.getHeight());
      
    }
	
	//runTime determines which frame the animation should display
	public void render(float runTime) {
		
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
		
		batcher.draw(bg, 0, midPointY + 23, 136, 43);
		
		drawGrass();
		
		drawWalls();
		
		//enables transparency
		batcher.enableBlending();
		
		
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
		batcher.end();
		
		//Draws the collision circle on the bear
		/*shapeRenderer.begin(ShapeType.Filled);
		shapeRenderer.setColor(Color.RED);
		shapeRenderer.circle(bear.getBoundingCircle().x, bear.getBoundingCircle().y, bear.getBoundingCircle().radius);
		*/
		
		//bars 1 2 3 for collision detection
		/*shapeRenderer.rect(wall1.getBar().x, wall1.getBar().y, wall1.getBar().width, wall1.getBar().height);
		shapeRenderer.rect(wall2.getBar().x, wall2.getBar().y, wall2.getBar().width, wall2.getBar().height);
		shapeRenderer.rect(wall3.getBar().x, wall3.getBar().y, wall3.getBar().width, wall3.getBar().height);
		*/
		//shapeRenderer.end();
	}
	
}
