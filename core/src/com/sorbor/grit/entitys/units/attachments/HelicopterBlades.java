package com.sorbor.grit.entitys.units.attachments;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;
import com.sorbor.grit.entitys.Entity;

public class HelicopterBlades implements Attachment, Entity, Disposable {
	
	float rotation;

	public HelicopterBlades() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPosition(Vector2 pos) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDirection(float angleDegrees) {
		// TODO Auto-generated method stub
		rotation = angleDegrees%360;
		
	}

}
