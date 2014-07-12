package com.hubbabubbagump.Helpers;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.InputProcessor;
import com.hubbabubbagump.GameObjects.BearCopter;
import com.hubbabubbagump.GameWorld.GameWorld;
import com.hubbabubbagump.UI.StartButton;

public class InputHandler implements InputProcessor{
	
	private BearCopter myBear;
	
	private GameWorld myWorld;
	
	private static List<StartButton> menuButtons;
	private StartButton playButton;
	private float scaleFactorX;
	private float scaleFactorY;
	
	private int buttonX;
	private int buttonY;
	
	public InputHandler(GameWorld myWorld, float scaleFactorX, float scaleFactorY) {
		//has myBear copy bear from GameScreen
		this.myWorld = myWorld;
		myBear = myWorld.getBear();
		
		this.scaleFactorX = scaleFactorX;
		this.scaleFactorY = scaleFactorY;
		
		buttonX = StartButton.getX();
		buttonY = StartButton.getY();
		menuButtons = new ArrayList<StartButton>();
		playButton = new StartButton(buttonX, buttonY, 40, 20, AssetLoader.start);
		menuButtons.add(playButton);
	}
	
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		screenX = scaleX(screenX);
		screenY = scaleY(screenY);
		System.out.println(screenX + "    " + screenY);
		
		if (myWorld.pause()) {
			myWorld.start();
		}
		else if (myWorld.title()) {
			playButton.downTouch(screenX, screenY);
		}
		myBear.onClick();
		if (myWorld.gameOver()) {
			myWorld.restart();
		}
		return true; //returns true - touch has been handled
	}

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        screenX = scaleX(screenX);
        screenY = scaleY(screenY);
        if(myWorld.title()) {
        	if(playButton.upTouch(screenX, screenY)) {
        		myWorld.ready();
        		return true;
        	}
        }
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
    
    private int scaleX(int screenX) {
        return (int) (screenX / scaleFactorX);
    }

    private int scaleY(int screenY) {
        return (int) (screenY / scaleFactorY);
    }
    
    public static List<StartButton> getMenuButtons() {
        return menuButtons;
    }

}
