package com.sorbor.grit.entitys.units;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.sorbor.grit.entitys.units.attachments.Wheel;
import com.sorbor.grit.input.InputController;

public class Car implements Unit {

	private static final float rotationSpeed = 0.36f;
	private static final float breakSpeed = 3.5f;
	private Sprite sprite;
	private SpriteBatch sb;
	private InputController cont;
	private Vector2 speed = new Vector2();
	private static final float acceleration = 8f;
	private static final float topSpeed = 8f;
	private Wheel[] wheels = new Wheel[5];
	private float wheelAngleOffset = 0;
	private static final float xWheelOffset = 19;
	private static final float yWheelOffset = 16;

	public Car(SpriteBatch sb, InputController inputCon) {
		sprite = new Sprite(new Texture("verynice.png"));
		sprite.setOriginCenter();
		this.sb = sb;
		cont = inputCon;
		Texture wheelTex = new Texture("Wheel.png");
		Texture handleTex = new Texture("Handtaglol.png");
		TextureRegion[][] tmpTr = TextureRegion.split(wheelTex, wheelTex.getWidth() / 1, wheelTex.getHeight() / 1);
		TextureRegion[] wheelTr = new TextureRegion[1];
		TextureRegion[] handleTr = new TextureRegion[1];
		handleTr[0] = new TextureRegion(handleTex);
		int index = 0;
		for (int x = 0; x < tmpTr.length; x++) {
			for (int y = 0; y < tmpTr[x].length; y++) {
				wheelTr[index++] = tmpTr[x][y];
			}
		}

		wheels[0] = new Wheel(wheelTr, 1, sb, new Vector2(xWheelOffset, yWheelOffset));
		wheels[1] = new Wheel(wheelTr, 1, sb, new Vector2(xWheelOffset, -yWheelOffset));
		wheels[2] = new Wheel(wheelTr, 1, sb, new Vector2(-xWheelOffset, -yWheelOffset));
		wheels[3] = new Wheel(wheelTr, 1, sb, new Vector2(-xWheelOffset, yWheelOffset));
		wheels[4] = new Wheel(handleTr, 1, sb, new Vector2(20, 0));

	}

	@Override
	public Vector2 getPos() {
		return new Vector2(sprite.getX(), sprite.getY());
	}

	@Override
	public void render() {
		for(int i=0;i<wheels.length-1;i++){
			wheels[i].render();
		}
		sprite.draw(sb);
		wheels[4].render();
	}

	@Override
	public void update() {
		Vector2 vec = cont.getDirectionOne();
		if (vec.len() != 0) {
			sprite.rotate(((vec.angle((new Vector2(1, 0)).setAngle(-sprite.getRotation()))) > 180
					? -(vec.angle((new Vector2(1, 0)).setAngle(-sprite.getRotation())))
					: (vec.angle((new Vector2(1, 0)).setAngle(-sprite.getRotation())))) * Gdx.graphics.getDeltaTime()
					* rotationSpeed * speed.len());
		}

		sprite.setRotation(sprite.getRotation() % 360);
		speed.add(new Vector2(acceleration * Gdx.graphics.getDeltaTime() * vec.cpy().limit(1).len(), 0)
				.setAngle(sprite.getRotation()));
		speed.limit(topSpeed);
		speed.setLength((breakSpeed * Gdx.graphics.getDeltaTime()) > speed.len() ? 0
				: (speed.len() - (breakSpeed * Gdx.graphics.getDeltaTime())));
		sprite.setPosition(speed.x + sprite.getX(), speed.y + sprite.getY());

		wheelAngleOffset = ((vec.angle((new Vector2(1, 0)).setAngle(-sprite.getRotation()))) > 180
				? -(vec.angle((new Vector2(1, 0)).setAngle(-sprite.getRotation())))
				: (vec.angle((new Vector2(1, 0)).setAngle(-sprite.getRotation())))) / 2.5f;

		for (int i = 0; i < wheels.length; i++) {
			if (vec.len() == 0) {
				wheels[i].setDirection(sprite.getRotation());
			} else if (i > 1 && i != 4) {
				wheels[i].setDirection(sprite.getRotation() - wheelAngleOffset * 0.5f);
			} else {
				wheels[i].setDirection(sprite.getRotation() + wheelAngleOffset);
			}
			Vector2 pos = getPos().add(sprite.getOriginX(), sprite.getOriginY())
					.add(wheels[i].getOffset().rotate(sprite.getRotation())).sub(wheels[i].getWidth()/2, wheels[i].getHeight()/2);
			wheels[i].setPosition(pos);
		}
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

}
