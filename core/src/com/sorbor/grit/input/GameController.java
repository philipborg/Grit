package com.sorbor.grit.input;

import java.util.HashMap;

import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class GameController implements InputController, ControllerListener {

	private Vector2 one = new Vector2(), two = new Vector2();
	private Controller controller;
	private static final float ignoreArea = 0.2f;
	private HashMap<Short, Short> customKeyBinding = new HashMap<Short, Short>();

	public Controller getController() {
		return controller;
	}

	public GameController(Controller controller) {
		this.controller = controller;
		Controllers.addListener(this);
	}

	@Override
	public Vector2 getDirectionOne() {
		return one;
	}

	@Override
	public Vector2 getDirectionTwo() {
		return two;
	}

	@Override
	public void connected(Controller controller) {
		// TODO Auto-generated method stub

	}

	@Override
	public void disconnected(Controller controller) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean buttonDown(Controller controller, int buttonCode) {
		return true;
	}

	@Override
	public boolean buttonUp(Controller controller, int buttonCode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean axisMoved(Controller controller, int axisCode, float value) {
		if (controller == this.controller) {
			if (axisCode == XBox360Pad.AXIS_LEFT_X) {
				one.x = value > ignoreArea || value < -ignoreArea ? value : 0;
			}
			if (axisCode == XBox360Pad.AXIS_LEFT_Y) {
				one.y = value > ignoreArea || value < -ignoreArea ? value : 0;
			}

			if (axisCode == XBox360Pad.AXIS_RIGHT_X) {
				two.x = value > ignoreArea || value < -ignoreArea ? value : 0;
			}
			if (axisCode == XBox360Pad.AXIS_RIGHT_Y) {
				two.y = value > ignoreArea || value < -ignoreArea ? value : 0;
			}
		}
		return true;
	}

	@Override
	public boolean povMoved(Controller controller, int povCode, PovDirection value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean xSliderMoved(Controller controller, int sliderCode, boolean value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean ySliderMoved(Controller controller, int sliderCode, boolean value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean accelerometerMoved(Controller controller, int accelerometerCode, Vector3 value) {
		// TODO Auto-generated method stub
		return false;
	}

	public String toString() {
		return controller.getName();
	}

	@Override
	public boolean isButtonPressed(short key) {
		if (customKeyBinding.containsKey(key)) {
			return controller.getButton(customKeyBinding.get(key));
		} else {
			return controller.getButton(key);
		}
	}

	@Override
	public PovDirection getPad() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void bindInput(short event, short key) {
		customKeyBinding.put(event, key);
	}

}
