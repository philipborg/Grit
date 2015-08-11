package com.sorbor.grit.entitys.units;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.sorbor.grit.input.InputController;
import com.sorbor.grit.util.MathUtil;

public class Airplane implements Unit {

	InputController inputCon;
	float speed = 3;
	float maxSize = 0.15f;
	float minSize = 0.1f;
	float speedSize = 0.035f;
	int rotationSpeed = 67;

	public Airplane(SpriteBatch sb, InputController inputCon) {
		this.sb = sb;
		this.inputCon = inputCon;
		sprite = new Sprite(new Texture("Plane.png"));
		sprite.scale(-0.9f);
		sprite.setOriginCenter();
		sprite.setCenter(0, 0);
	}

	@Override
	public void update() {
		Vector2 vec = inputCon.getDirectionOne();
		sprite.rotate((-vec.x * Gdx.graphics.getDeltaTime() * rotationSpeed));
		sprite.setPosition((float) (sprite.getX() + (Gdx.graphics.getDeltaTime() * speed * MathUtil.cosDegrees(sprite.getRotation()))),
				(float) (sprite.getY() + (Gdx.graphics.getDeltaTime() * speed * MathUtil.sinDegrees(sprite.getRotation()))));
		sprite.scale(vec.y*Gdx.graphics.getDeltaTime()*speedSize);
		if(sprite.getScaleX()>maxSize)
			sprite.setScale(maxSize);
		if(sprite.getScaleX()<minSize)
			sprite.setScale(minSize);
	}
	
	Sprite sprite;
	SpriteBatch sb;
	
	@Override
	public Vector2 getPos() {
		return new Vector2(sprite.getX(), sprite.getY());
	}

	@Override
	public void render() {
		sprite.draw(sb);
	}

	@Override
	public byte getHeightLevel() {
		return 1;
	}

	@Override
	public void dispose(){
		sprite.getTexture().dispose();
	}

	@Override
	public void setPosition(Vector2 pos) {
		sprite.setPosition(pos.x, pos.y);
	}

	@Override
	public void setDirection(float angleDegrees) {
		sprite.setRotation(angleDegrees);
	}
}
