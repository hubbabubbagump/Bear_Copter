package com.hubbabubbagump.GameWorld;

import javax.microedition.khronos.opengles.GL10;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;


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
		
		Gdx.gl.glClearColor(0, 0, 0, 1); //specifies the rgb and alpha values when colors are cleared
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT); //sets the area of the window to values specified in glClearColor
		
	}
}
