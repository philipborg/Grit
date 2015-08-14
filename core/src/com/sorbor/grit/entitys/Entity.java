package com.sorbor.grit.entitys;

import com.badlogic.gdx.math.Vector2;

public interface Entity {

	public void render();
	
	public void update();

	public void setPosition(Vector2 pos);

	public void setDirection(float angleDegrees);
	
	public Vector2 getPosition();
	
	public float getDirection();
}
