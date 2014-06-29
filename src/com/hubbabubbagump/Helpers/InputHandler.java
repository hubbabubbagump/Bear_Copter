package com.hubbabubbagump.Helpers;

import com.badlogic.gdx.InputProcessor;
import com.hubbabubbagump.GameObjects.BearCopter;

public class InputHandler implements InputProcessor{
	
	private BearCopter myBear;
	
	public InputHandler(BearCopter bear) {
		//has myBear copy bear from GameScreen
		myBear = bear;
	}
	
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
			myBear.onClick();
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
	

}
