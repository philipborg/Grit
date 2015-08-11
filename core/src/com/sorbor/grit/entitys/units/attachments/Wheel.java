package com.sorbor.grit.entitys.units.attachments;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;
import com.sorbor.grit.entitys.Entity;

public class Wheel implements Attachment, Entity, Disposable {

	private SpriteBatch sb;
	private Vector2 offset, lastPos = new Vector2();
	private float distanceTraveledSinceRot = 0, frequency, rotation = 0;
	private TextureRegion[] tr;
	private int cFrame = 0;

	public Wheel(TextureRegion[] tr, float frequency, SpriteBatch sb, Vector2 offset) {
		this.offset = offset;
		this.tr = tr;
		this.frequency = 10 / frequency;
		this.sb = sb;
	}

	@Override
	public void render() {
		sb.draw(tr[cFrame], lastPos.x, lastPos.y, tr[cFrame].getRegionWidth() / 2, tr[cFrame].getRegionHeight() / 2,
				tr[cFrame].getRegionWidth(), tr[cFrame].getRegionHeight(), 1, 1, rotation);
	}

	@Override
	public void update() {
		if (distanceTraveledSinceRot > frequency) {
			cFrame = (cFrame + 1) % tr.length;
			distanceTraveledSinceRot = 0;
		}
	}

	@Override
	public void setPosition(Vector2 pos) {
		distanceTraveledSinceRot += lastPos.dst(pos);
		lastPos = pos.cpy();
	}

	public int getWidth() {
		return tr[cFrame].getRegionWidth();
	}

	public int getHeight() {
		return tr[cFrame].getRegionHeight();
	}

	public Vector2 getOffset() {
		return offset.cpy();
	}

	@Override
	public void setDirection(float angleDegrees) {
		rotation = angleDegrees % 360;
	}

	@Override
	public void dispose() {
		tr[0].getTexture().dispose();
	}

}
