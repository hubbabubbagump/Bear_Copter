package com.hubbabubbagump.Helpers;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.InputProcessor;
import com.hubbabubbagump.GameObjects.BearCopter;
import com.hubbabubbagump.GameWorld.GameWorld;
import com.hubbabubbagump.Screens.GameScreen;
import com.hubbabubbagump.UI.CharChange;
import com.hubbabubbagump.UI.CharSelect;
import com.hubbabubbagump.UI.MenuButton;
import com.hubbabubbagump.UI.PauseButton;
import com.hubbabubbagump.UI.ScoreButton;
import com.hubbabubbagump.UI.StartButton;
import com.hubbabubbagump.UI.Volume;

public class InputHandler implements InputProcessor{
	
	private BearCopter myBear;
	
	private GameWorld myWorld;
	
	private static List<StartButton> menuButtons;
	private static List<ScoreButton> menuScoreButtons;
	private static List<MenuButton> menuMenuButtons;
	private static List<Volume> volumeButtons;
	private StartButton playButton;
	private ScoreButton scoreButton;
	private MenuButton menuButton;
	private Volume volumeButton;
	private PauseButton pauseButton;
	private CharSelect charButton;
	private CharChange bearButton;
	private CharChange catButton;
	private CharChange ramButton;
	private CharChange penguinButton;
	private CharChange toastButton;
	private float scaleFactorX;
	private float scaleFactorY;
	
	private int buttonX;
	private int buttonY;
	private int scoreButtonX;
	private int scoreButtonY;
	private int menuX;
	private int menuY;
	private int volumeX;
	private int volumeY;
	private int pauseY;
	private float pauseX;
	private int charX;
	private int charY;
	
	private float midPointY = GameScreen.midScreen();
	private float middle = midPointY - 15;
	private float charTop = middle - 30;
	private float charBottom = middle + 30;
	private float XRIGHT = 136 / 4 * 3 - (AssetLoader.bear.getRegionWidth() / 2);
	private float XMID = 136 / 2 - (AssetLoader.bear.getRegionWidth() / 2);
	private float XLEFT = 136 / 4 - (AssetLoader.bear.getRegionWidth() / 2);
	
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
		volumeX = Volume.getX();
		volumeY = Volume.getY();
		pauseX = PauseButton.getX();
		pauseY = PauseButton.getY();
		charX = CharSelect.getX();
		charY = CharSelect.getY();
		menuButtons = new ArrayList<StartButton>();
		menuScoreButtons = new ArrayList<ScoreButton>();
		menuMenuButtons = new ArrayList<MenuButton>();
		volumeButtons = new ArrayList<Volume>();
		playButton = new StartButton(buttonX, buttonY, 21, 21, AssetLoader.start, AssetLoader.startDown);
		scoreButton = new ScoreButton(scoreButtonX, scoreButtonY, 21, 21, AssetLoader.score, AssetLoader.scoreDown);
		menuButton = new MenuButton(menuX, menuY, 21, 21, AssetLoader.menu, AssetLoader.menuDown);
		volumeButton = new Volume(volumeX, volumeY, 15, 15, AssetLoader.volumeON, AssetLoader.volumeOFF);
		pauseButton = new PauseButton(pauseX, pauseY, 11, 11, AssetLoader.pause);
		charButton = new CharSelect(charX, charY, 21, 21, AssetLoader.charButton, AssetLoader.charButtonDown);
		bearButton = new CharChange(XMID, middle, 18, 16, AssetLoader.bear);
		penguinButton = new CharChange(XLEFT, charTop, 18, 16, AssetLoader.penguin);
		ramButton = new CharChange(XRIGHT, charTop, 18, 16, AssetLoader.ram);
		toastButton = new CharChange(XLEFT, charBottom, 18, 16, AssetLoader.toast);
		catButton = new CharChange(XRIGHT, charBottom, 18, 16, AssetLoader.cat);
		menuButtons.add(playButton);
		menuScoreButtons.add(scoreButton);
		menuMenuButtons.add(menuButton);
		volumeButtons.add(volumeButton);
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
			if (charButton.downTouch(screenX, screenY)) {
				myWorld.charButtonDown();
			}
			volumeButton.downTouch(screenX, screenY);
				
		
		}
		else if (myWorld.isScore()) {
			myWorld.titleScreen();
		}
		
		if (myWorld.isCharSelect()) {
			if (bearButton.downTouch(screenX, screenY)) {
				AssetLoader.setCurrentAvatar(0);
			}
			else if (penguinButton.downTouch(screenX, screenY) && AssetLoader.penguinUnlocked()) {
				AssetLoader.setCurrentAvatar(1);
			}
			else if (ramButton.downTouch(screenX, screenY) && AssetLoader.ramUnlocked()) {
				AssetLoader.setCurrentAvatar(2);
			}
			else if (toastButton.downTouch(screenX, screenY) && AssetLoader.toastUnlocked()) {
				AssetLoader.setCurrentAvatar(3);
			}
			else if (catButton.downTouch(screenX, screenY) && AssetLoader.catUnlocked()) {
				AssetLoader.setCurrentAvatar(4);
			}
			
			myWorld.titleScreen();
			
		}
		
		if (GameWorld.running()) {
			pauseButton.downTouch(screenX, screenY);
		}
		
		if(!myWorld.isPaused()) {
			myBear.onClick();
		}
		
		if(myWorld.isPaused()) {
			myWorld.start();
		}
	
		
		if (myWorld.gameOver()) {
			if (menuButton.downTouch(screenX, screenY)) {
				myWorld.menuButtonDown();
			}
			else {
				myWorld.restart();
				
			}
		}
		
		if (myWorld.isStory()) {
			myWorld.titleScreen();
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
        	myWorld.charButtonUp();
        	if(playButton.upTouch(screenX, screenY)) {
        		myWorld.ready();
        		return true;
        	}
        	else if(scoreButton.upTouch(screenX, screenY)) {
        		myWorld.score();
        		return true;
        	}
        	else if (volumeButton.upTouch(screenX, screenY)) {
        		myWorld.volumeControl();
        	}
        	else if (charButton.upTouch(screenX, screenY)) {
        		myWorld.charSelect();
        	}
        	
        }
        if(GameWorld.running()) {
        	if(pauseButton.upTouch(screenX, screenY)) {
        		myWorld.pauseGame();
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
    
    public static List<Volume> getVolumeButtons() {
    	return volumeButtons;
    }
}
