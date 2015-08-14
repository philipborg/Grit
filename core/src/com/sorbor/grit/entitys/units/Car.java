package com.sorbor.grit.entitys.units;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.sorbor.grit.entitys.units.attachments.Wheel;
import com.sorbor.grit.input.InputController;

public class Car extends Unit {
	
	private byte layer = 0;
	private static final float rotationSpeed = 0.36f; // How fast you can turn
	private static final float breakSpeed = 3.5f; // Friction
	private Sprite sprite; // Car sprite
	private SpriteBatch sb; // Games spritebatch
	private InputController cont; // The controller
	private Vector2 speed = new Vector2(); // The current speed
	private static final float acceleration = 8f; // The acceleration
	private static final float topSpeed = 8f; // Top speed
	private Wheel[] wheels = new Wheel[5]; // One to 4 are actual wheels, the
											// fifth is the handle
	private static final float xWheelOffset = 19; // How far the wheels are from
													// the center on the X axis
	private static final float yWheelOffset = 16; // How far the wheels are from
													// the center on the Y axis

	public Car(SpriteBatch sb, InputController inputCon) {
		sprite = new Sprite(new Texture("verynice.png")); // Loads sprite
		sprite.setOriginCenter();
		this.sb = sb;
		cont = inputCon;
		Texture handleTex = new Texture("HandleBar.png"); // Loads handle
															// texture

		// Loads wheels
		Texture wheelTex = new Texture("Wheel.png");
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

		// Handle
		wheels[4] = new Wheel(handleTr, 1, sb, new Vector2(20, 0));

	}

	@Override
	public void render() {
		// Renders each wheel
		for (int i = 0; i < wheels.length - 1; i++) {
			wheels[i].render();
		}
		sprite.draw(sb); // Renders actual vehicle
		wheels[4].render(); // Renders handle bar
	}

	@Override
	public void update() {
		Vector2 vec = cont.getDirectionOne();

		if (vec.len() != 0) { // Prevents movement to angle 0
			// Calculate angle between vec and current rotation
			float angle = vec.angle((new Vector2(1, 0)).setAngle(-sprite.getRotation())); // TODO
																							// Optimise,
																							// drop
																							// use
																							// of
																							// new
																							// vector
			// Rotate towards vec
			sprite.rotate(angle > 180 ? -angle : (angle * Gdx.graphics.getDeltaTime() * rotationSpeed * speed.len()));
		}

		sprite.setRotation(sprite.getRotation() % 360); // Ensure that rotation
														// is between higher
														// than -360 and less
														// than 360

		speed.add(new Vector2(acceleration * Gdx.graphics.getDeltaTime() * vec.cpy().limit(1).len(), 0)
				.setAngle(sprite.getRotation())); // Adds speed towards the
													// current rotation

		speed.limit(topSpeed); // Even in games speed regulations should be
								// followed

		// Applies friction
		speed.setLength((breakSpeed * Gdx.graphics.getDeltaTime()) > speed.len() ? 0
				: (speed.len() - (breakSpeed * Gdx.graphics.getDeltaTime())));

		// Move with speed
		sprite.setPosition(speed.x + sprite.getX(), speed.y + sprite.getY());

		// Calculate wheels angle
		float wheelAngleOffset = ((vec.angle((new Vector2(1, 0)).setAngle(-sprite.getRotation()))) > 180
				? -(vec.angle((new Vector2(1, 0)).setAngle(-sprite.getRotation())))
				: (vec.angle((new Vector2(1, 0)).setAngle(-sprite.getRotation())))) / 2.5f;

		// Set direction for wheels
		for (int i = 0; i < wheels.length; i++) {
			if (vec.len() == 0) {
				wheels[i].setDirection(sprite.getRotation());
			} else if (i > 1 && i != 4) {
				wheels[i].setDirection(sprite.getRotation() - wheelAngleOffset * 0.5f);
			} else {
				wheels[i].setDirection(sprite.getRotation() + wheelAngleOffset);
			}
			Vector2 pos = getPosition().add(wheels[i].getOffset().rotate(sprite.getRotation()))
					.sub(wheels[i].getWidth() / 2, wheels[i].getHeight() / 2);
			wheels[i].setPosition(pos);
		}
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

	@Override
	public byte getLayer() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setLayer(byte layer) {
		// TODO Auto-generated method stub

	}

}
