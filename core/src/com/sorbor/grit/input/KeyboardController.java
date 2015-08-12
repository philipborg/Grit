package com.sorbor.grit.input;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.controllers.PovDirection;

public class KeyboardController implements InputController {

	HashMap<Short, Integer> keyMap = new HashMap<Short, Integer>();
	public static short LEFTONE = -1;
	public static short RIGHTONE= -2;
	public static short DOWNONE = -3;
	public static short UPONE = -4;
	public static short LEFTTWO = -5;
	public static short RIGHTTWO = -6;
	public static short DOWNTWO = -7;
	public static short UPTWO = -8;

	public KeyboardController() {
		bindKey(LEFTONE, Keys.A);
		bindKey(RIGHTONE, Keys.D);
		bindKey(DOWNONE, Keys.S);
		bindKey(UPONE, Keys.W);
		bindKey(LEFTTWO, Keys.LEFT);
		bindKey(RIGHTONE, Keys.RIGHT);
		bindKey(DOWNONE, Keys.DOWN);
		bindKey(UPONE, Keys.UP);
	}

	public void bindKey(short target, int key) {
		keyMap.put(target, key);
	}

	public String toString() {
		return "Keyboard";

	}

	@Override
	public Vector2 getDirectionOne() {
		float x = 0;
		float y = 0;
		if (Gdx.input.isKeyPressed(keyMap.get(LEFTONE)) && !Gdx.input.isKeyPressed(keyMap.get(RIGHTONE))) {
			x = -1;
		} else if (!Gdx.input.isKeyPressed(keyMap.get(LEFTONE)) && Gdx.input.isKeyPressed(keyMap.get(RIGHTONE))) {
			x = 1;
		}

		if (Gdx.input.isKeyPressed(keyMap.get(DOWNONE)) && !Gdx.input.isKeyPressed(keyMap.get(UPONE))) {
			y = 1;
		} else if (!Gdx.input.isKeyPressed(keyMap.get(DOWNONE)) && Gdx.input.isKeyPressed(keyMap.get(UPONE))) {
			y = -1;
		}

		return new Vector2(x, y);
	}

	@Override
	public Vector2 getDirectionTwo() {
		float x = 0;
		float y = 0;
		if (Gdx.input.isKeyPressed(keyMap.get(LEFTTWO)) && !Gdx.input.isKeyPressed(keyMap.get(RIGHTTWO))) {
			x = -1;
		} else if (!Gdx.input.isKeyPressed(keyMap.get(LEFTTWO)) && Gdx.input.isKeyPressed(keyMap.get(RIGHTTWO))) {
			x = 1;
		}

		if (Gdx.input.isKeyPressed(keyMap.get(DOWNTWO)) && !Gdx.input.isKeyPressed(keyMap.get(UPTWO))) {
			y = -1;
		} else if (!Gdx.input.isKeyPressed(keyMap.get(DOWNTWO)) && Gdx.input.isKeyPressed(keyMap.get(UPTWO))) {
			y = 1;
		}

		return new Vector2(x, y);
	}

	@Override
	public PovDirection getPad() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isButtonPressed(short key) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void bindInput(short event, short key) {
		// TODO Auto-generated method stub

	}

}
