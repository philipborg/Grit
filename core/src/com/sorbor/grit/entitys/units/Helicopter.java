package com.sorbor.grit.entitys.units;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;
import com.sorbor.grit.entitys.Entity;
import com.sorbor.grit.entitys.units.attachments.Attachment;
import com.sorbor.grit.input.InputController;

class HelicopterBlades implements Attachment, Entity, Disposable {

	private SpriteBatch sb;
	float rotation = 0;
	float rotationSpeed = 0.5f;
	Vector2 offset = new Vector2(0, 0);
	Vector2 pos = new Vector2(0, 0);
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
		sb.draw(tr, pos.x, pos.y, tr.getRegionWidth() / 2, tr.getRegionHeight() / 2, tr.getRegionWidth(),
				tr.getRegionHeight(), 1, 1, rotation + ((System.currentTimeMillis() % 360) * rotationSpeed));

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

		rotation = angleDegrees % 360;

	}

	public Vector2 getOffset() {
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
		return new Vector2(pos.x + tr.getRegionWidth() / 2, pos.y + tr.getRegionHeight() / 2);
	}

	@Override
	public float getDirection() {
		// TODO Auto-generated method stub
		return rotation + ((System.currentTimeMillis() % 360) * rotationSpeed);
	}

}

public class Helicopter extends Unit {

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
		sprite.setOrigin(sprite.getWidth() / 2 + 25, sprite.getHeight() / 2);
		this.sb = sb;
		cont = inputCon;
		sprite.setScale(0.75f);
		setLayer((byte)6);

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
		// vec2.y *= -1;
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

		Vector2 pos = getPosition().add(blades.getOffset().rotate(sprite.getRotation())).sub(blades.getWidth() / 2,
				blades.getHeight() / 2);
		blades.setPosition(pos);
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
