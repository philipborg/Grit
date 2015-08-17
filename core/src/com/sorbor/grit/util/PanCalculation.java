package com.sorbor.grit.util;

import com.badlogic.gdx.math.Vector2;

public class PanCalculation {

	public static Vector2 camPos = new Vector2(0,0);
	public static final float inMax = 2560/2f;

	public PanCalculation() {

	}

	public static void setCamPos(float camPosX, float camPosY) {
		PanCalculation.camPos = new Vector2(camPosX, camPosY);
	}

	public static float calculatePan(float x) {


		float relative = x - camPos.x;
		float returnValue = MathUtil.mapValues(relative, -inMax, inMax, -1, 1);
		if (returnValue > 1)
			returnValue = 1;
		if (returnValue < -1)
			returnValue = -1;
		return returnValue;
	}
	
	public static float calculateVolume(Vector2 pos){
		return MathUtil.mapValues(pos.cpy().sub(camPos).len(), -inMax*1.5f, inMax*1.5f, 1, 0);
	}

}
