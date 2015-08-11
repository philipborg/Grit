package com.sorbor.grit.input;

import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.math.Vector2;

public interface InputController {

	public static final short Use0 = XBox360Pad.BUTTON_A;
	public static final short Use1 = XBox360Pad.BUTTON_B;
	public static final short Use2 = XBox360Pad.BUTTON_X;
	public static final short Use3 = XBox360Pad.BUTTON_Y;
	public static final short Use4 = XBox360Pad.BUTTON_RB;
	public static final short Use5 = XBox360Pad.BUTTON_LB;
	public static final short Special = XBox360Pad.BUTTON_BACK;
	public static final short Start = XBox360Pad.BUTTON_START;

	public PovDirection getPad();

	public Vector2 getDirectionOne();

	public Vector2 getDirectionTwo();

	public boolean isButtonPressed(short key);

	public void bindInput(short event, short key);
}
