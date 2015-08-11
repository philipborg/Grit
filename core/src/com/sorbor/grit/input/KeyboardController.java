package com.sorbor.grit.input;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.controllers.PovDirection;

public class KeyboardController implements InputController {

	HashMap<Short, Integer> keyMap = new HashMap<Short, Integer>();
	public static short leftOne = -1;
	public static short rightOne = -2;
	public static short downOne = -3;
	public static short upOne = -4;

	public KeyboardController() {
		keyMap.put(leftOne, Keys.A);
		keyMap.put(rightOne, Keys.D);
		keyMap.put(downOne, Keys.S);
		keyMap.put(upOne, Keys.W);
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
		if (Gdx.input.isKeyPressed(keyMap.get(leftOne)) && !Gdx.input.isKeyPressed(keyMap.get(rightOne))) {
			x = -1;
		} else if (!Gdx.input.isKeyPressed(keyMap.get(leftOne)) && Gdx.input.isKeyPressed(keyMap.get(rightOne))) {
			x = 1;
		}

		if (Gdx.input.isKeyPressed(keyMap.get(downOne)) && !Gdx.input.isKeyPressed(keyMap.get(upOne))) {
			y = 1;
		} else if (!Gdx.input.isKeyPressed(keyMap.get(downOne)) && Gdx.input.isKeyPressed(keyMap.get(upOne))) {
			y = -1;
		}

		return new Vector2(x, y);
	}

	@Override
	public Vector2 getDirectionTwo() {
		float x = 0;
		float y = 0;
		if (Gdx.input.isKeyPressed(Keys.LEFT) && !Gdx.input.isKeyPressed(Keys.RIGHT)) {
			x = -1;
		} else if (!Gdx.input.isKeyPressed(Keys.LEFT) && Gdx.input.isKeyPressed(Keys.RIGHT)) {
			x = 1;
		}

		if (Gdx.input.isKeyPressed(Keys.DOWN) && !Gdx.input.isKeyPressed(Keys.UP)) {
			y = -1;
		} else if (!Gdx.input.isKeyPressed(Keys.DOWN) && Gdx.input.isKeyPressed(Keys.UP)) {
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
