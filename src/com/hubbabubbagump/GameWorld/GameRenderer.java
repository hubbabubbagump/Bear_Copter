package com.hubbabubbagump.GameWorld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class GameRenderer {
	
	private GameWorld myWorld;
	private OrthographicCamera cam;
	private ShapeRenderer shapeRenderer;
	
	//Creates camera and shapes.
	public GameRenderer(GameWorld world) {
		myWorld = world;
		cam = new OrthographicCamera();
		cam.setToOrtho(true, 136, 204);
		
		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setProjectionMatrix(cam.combined);
	}
	
	//Currently renders a test rectangle on a black background.
	public void render() {
		System.out.println("Rendering Game");
		
		//Draws a black background
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		//Draws a filled rect
		shapeRenderer.begin(ShapeType.Filled);
		
		//Choose RGB color
		shapeRenderer.setColor(87/255.0f, 109/255.0f, 120/255.0f, 1);
		
		//Draws the rect
		shapeRenderer.rect(myWorld.getRect().x, myWorld.getRect().y, myWorld.getRect().width, myWorld.getRect().height);
		
		//Tells shapeRenderer to end
		shapeRenderer.end();
		
		//Draws the rect outline
		shapeRenderer.begin(ShapeType.Line);
		
		//chooses RGB
		shapeRenderer.setColor(255/255.0f, 109/255.0f, 120/255.0f, 1);
		
		//draws rect
		shapeRenderer.rect(myWorld.getRect().x, myWorld.getRect().y, myWorld.getRect().width, myWorld.getRect().height);
		
		//ends shapeRenderer
		shapeRenderer.end();
		
	}
}
