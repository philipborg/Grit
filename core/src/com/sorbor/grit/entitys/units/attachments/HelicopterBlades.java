package com.sorbor.grit.entitys.units.attachments;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;
import com.sorbor.grit.entitys.Entity;

public class HelicopterBlades implements Attachment, Entity, Disposable {
	
	private SpriteBatch sb;
	float rotation = 0;
	float rotationSpeed = 0.5f;
	Vector2 offset = new Vector2(0,0);
	Vector2 pos = new Vector2(0,0);
	TextureRegion tr = new TextureRegion(new Texture(Gdx.files.internal("units/HelicopterBlade.png")));

	public HelicopterBlades(SpriteBatch sb, Vector2 offset) {
		// TODO Auto-generated constructor stub
		this.sb = sb;
		this.offset = offset;
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		tr.getTexture().dispose();
		
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		sb.draw(tr, pos.x, pos.y, tr.getRegionWidth()/2,tr.getRegionHeight()/2, tr.getRegionWidth(), tr.getRegionHeight(), 1, 1, rotation+((System.currentTimeMillis()%360)*rotationSpeed));
	
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
	
	}

	@Override
	public void setPosition(Vector2 pos) {
		// TODO Auto-generated method stub
		this.pos = pos;
		
	}

	@Override
	public void setDirection(float angleDegrees) {

		rotation = angleDegrees%360;
		
	}
	
	public Vector2 getOffset(){
		return offset.cpy();
	}

	public int getWidth() {
		// TODO Auto-generated method stub
		return tr.getRegionWidth();
	}
	
	public int getHeight() {
		// TODO Auto-generated method stub
		return tr.getRegionHeight();
	}

	@Override
	public Vector2 getPosition() {
		// TODO Auto-generated method stub
		return new Vector2(pos.x+tr.getRegionWidth() / 2, pos.y+tr.getRegionHeight() / 2);
	}

	@Override
	public float getDirection() {
		// TODO Auto-generated method stub
		return  rotation+((System.currentTimeMillis()%360)*rotationSpeed);
	}

}