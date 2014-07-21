package com.hubbabubbagump.Screens;

import javax.microedition.khronos.opengles.GL10;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.hubbabubbagump.Helpers.AssetLoader;
import com.hubbabubbagump.bear_a_copter.BCGame;

public class SplashScreen implements Screen {
	
	private BCGame game;
	
	private TextureRegion splash;
	
	private SpriteBatch batcher;
	private OrthographicCamera cam;
	private ShapeRenderer shapeRenderer;
	
	private static final float GAMEWIDTH = 136;
	private static float GAMEHEIGHT = 0;
	private int midPointY;
	
	private Vector2 timer;
	private Vector2 timerRate;
	
	public SplashScreen(BCGame game) {
		
		this.game = game;
		
		float screenWidth = Gdx.graphics.getWidth();
		float screenHeight = Gdx.graphics.getHeight();
		float gameHeight = screenHeight / (screenWidth / GAMEWIDTH);
		GAMEHEIGHT = gameHeight;
		midPointY = (int) (gameHeight/2);
		
		cam = new OrthographicCamera();
		cam.setToOrtho(true, 136, gameHeight);
		batcher = new SpriteBatch();
		//Attaches the batcher to the camera
		batcher.setProjectionMatrix(cam.combined);
		
		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setProjectionMatrix(cam.combined);
		
		timer = new Vector2(0, 0);
		timerRate = new Vector2(1, 0);
		
		splash = AssetLoader.splash;
	}
	
	@Override
	public void render(float delta) {
		timer.add(timerRate.cpy().scl(delta));
		
		Gdx.gl.glClearColor(0, 0, 0, 1); //specifies the rgb and alpha values when colors are cleared
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT); //sets the area of the window to values specified in glClearColor
		
		shapeRenderer.begin(ShapeType.Filled);
		
		//Draws background color
		shapeRenderer.setColor(0/255.0f, 0/255.0f, 0/255.0f, 1);
		shapeRenderer.rect(0, 0, GAMEWIDTH, GAMEHEIGHT);
		
		shapeRenderer.end();
		
		batcher.begin();
		
		//disables transparency
		batcher.disableBlending();
				
		//enables transparency
		batcher.enableBlending();
		
		batcher.draw(splash, 0, midPointY - 32, 136, 64);
		
		batcher.end();
		
		if (timer.x >= 3) {
			game.setScreen(new GameScreen());
		}
		else if (Gdx.input.justTouched()) {
			game.setScreen(new GameScreen());
		}
	}
	
	@Override
	public void show() {
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
