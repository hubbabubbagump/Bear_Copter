package com.hubbabubbagump.Screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hubbabubbagump.GameObjects.BearCopter;

//Work in progress
public class SplashScreen implements Screen{
	
	private SpriteBatch batcher;
	private BearCopter game;
	private Sprite sprite;
	
	public SplashScreen(BearCopter game) {
		this.game = game;
	}

	public void show() {
		sprite = new Sprite(AssetLoader.logo);
	}
}
