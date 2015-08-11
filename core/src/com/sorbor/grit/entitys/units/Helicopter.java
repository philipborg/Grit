package com.sorbor.grit.entitys.units;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.sorbor.grit.entitys.units.attachments.HelicopterBlades;
import com.sorbor.grit.input.InputController;

public class Helicopter implements Unit {

	private static final float rotationSpeed = 0.26f;
	private static final float breakSpeed = 3f;
	private Sprite sprite;
	private SpriteBatch sb;
	private InputController cont;
	private HelicopterBlades blades;
	private Vector2 speed = new Vector2();
	private static final float acceleration = 10f;
	private static final float topSpeed = 8f;

	public Helicopter(SpriteBatch sb, InputController inputCon) {
		// TODO Auto-generated constructor stub
		blades = new HelicopterBlades(sb, new Vector2(0, 0));
		sprite = new Sprite(new Texture("units/Helicopter.png"));
		sprite.setOriginCenter();
		sprite.setOrigin(sprite.getWidth()/2+25, sprite.getHeight()/2);
		this.sb = sb;
		cont = inputCon;

	}

	@Override
	public void render() {

		sprite.draw(sb);
		blades.render();

	}

	@Override
	public void update() {
		Vector2 vec = cont.getDirectionOne();
		Vector2 vec2 = cont.getDirectionTwo();
		if (vec2.len() != 0) {
			sprite.rotate(((vec2.angle((new Vector2(1, 0)).setAngle(-sprite.getRotation()))) > 180
					? -(vec2.angle((new Vector2(1, 0)).setAngle(-sprite.getRotation())))
					: (vec2.angle((new Vector2(1, 0)).setAngle(-sprite.getRotation())))) * Gdx.graphics.getDeltaTime()
					* rotationSpeed * 5);
		} else if (vec.len() != 0) {
			sprite.rotate(((vec.angle((new Vector2(1, 0)).setAngle(-sprite.getRotation()))) > 180
					? -(vec.angle((new Vector2(1, 0)).setAngle(-sprite.getRotation())))
					: (vec.angle((new Vector2(1, 0)).setAngle(-sprite.getRotation())))) * Gdx.graphics.getDeltaTime()
					* rotationSpeed * speed.len());
		}

		sprite.setRotation(sprite.getRotation() % 360);
		speed.add(new Vector2(acceleration * Gdx.graphics.getDeltaTime() * vec.cpy().limit(1).len(), 0)
				.setAngle(-vec.angle()));
		speed.limit(topSpeed);
		speed.setLength((breakSpeed * Gdx.graphics.getDeltaTime()) > speed.len() ? 0
				: (speed.len() - (breakSpeed * Gdx.graphics.getDeltaTime())));
		sprite.setPosition(speed.x + sprite.getX(), speed.y + sprite.getY());

		Vector2 pos = getPosition()
				.add(blades.getOffset().rotate(sprite.getRotation()))
				.sub(blades.getWidth() / 2, blades.getHeight() / 2);
		blades.setPosition(pos);
	}

	@Override
	public byte getHeightLevel() {
		return 0;
	}

	@Override
	public void dispose() {
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

	@Override
	public Vector2 getPosition() {
		return new Vector2(sprite.getX() + sprite.getOriginX(), sprite.getY() + sprite.getOriginY());
	}

	@Override
	public float getDirection() {
		return sprite.getRotation();
	}
}