package com.hubbabubbagump.Helpers;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.InputProcessor;
import com.hubbabubbagump.GameObjects.BearCopter;
import com.hubbabubbagump.GameWorld.GameWorld;
import com.hubbabubbagump.UI.MenuButton;
import com.hubbabubbagump.UI.ScoreButton;
import com.hubbabubbagump.UI.StartButton;

public class InputHandler implements InputProcessor{
	
	private BearCopter myBear;
	
	private GameWorld myWorld;
	
	private static List<StartButton> menuButtons;
	private static List<ScoreButton> menuScoreButtons;
	private static List<MenuButton> menuMenuButtons;
	private StartButton playButton;
	private ScoreButton scoreButton;
	private MenuButton menuButton;
	private float scaleFactorX;
	private float scaleFactorY;
	
	private int buttonX;
	private int buttonY;
	private int scoreButtonX;
	private int scoreButtonY;
	private int menuX;
	private int menuY;
	
	public InputHandler(GameWorld myWorld, float scaleFactorX, float scaleFactorY) {
		//has myBear copy bear from GameScreen
		this.myWorld = myWorld;
		myBear = myWorld.getBear();
		
		this.scaleFactorX = scaleFactorX;
		this.scaleFactorY = scaleFactorY;
		
		buttonX = StartButton.getX();
		buttonY = StartButton.getY();
		scoreButtonX = ScoreButton.getX();
		scoreButtonY = ScoreButton.getY();
		menuX = MenuButton.getX();
		menuY = MenuButton.getY();
		menuButtons = new ArrayList<StartButton>();
		menuScoreButtons = new ArrayList<ScoreButton>();
		menuMenuButtons = new ArrayList<MenuButton>();
		playButton = new StartButton(buttonX, buttonY, 21, 21, AssetLoader.start, AssetLoader.startDown);
		scoreButton = new ScoreButton(scoreButtonX, scoreButtonY, 21, 21, AssetLoader.score, AssetLoader.scoreDown);
		menuButton = new MenuButton(menuX, menuY, 21, 21, AssetLoader.menu, AssetLoader.menuDown);
		menuButtons.add(playButton);
		menuScoreButtons.add(scoreButton);
		menuMenuButtons.add(menuButton);
	}
	
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		screenX = scaleX(screenX);
		screenY = scaleY(screenY);
		
		if (myWorld.pause()) {
			myWorld.start();
		}
		else if (myWorld.title()) {
			if (playButton.downTouch(screenX, screenY)) {
				myWorld.startButtonDown();
			}
			if (scoreButton.downTouch(screenX, screenY)) {
				myWorld.scoreButtonDown();
			}
		}
		else if (myWorld.isScore()) {
			myWorld.titleScreen();
		}
		
		myBear.onClick();
		
		if (myWorld.gameOver()) {
			if (menuButton.downTouch(screenX, screenY)) {
				myWorld.menuButtonDown();
			}
			else {
				myWorld.restart();
				
			}
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
        	myWorld.startButtonUp();
        	myWorld.scoreButtonUp();
        	if(playButton.upTouch(screenX, screenY)) {
        		myWorld.ready();
        		return true;
        	}
        	else if(scoreButton.upTouch(screenX, screenY)) {
        		myWorld.score();
        		return true;
        	}
        }
        if(myWorld.gameOver()) {
        	myWorld.menuButtonUp();
        	if(menuButton.upTouch(screenX, screenY)) {
        		myWorld.toMenu();
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
    
    public static List<ScoreButton> getMenuScoreButtons() {
    	return menuScoreButtons;
    }
    
    public static List<MenuButton> getMenuMenuButtons() {
    	return menuMenuButtons;
    }
}
