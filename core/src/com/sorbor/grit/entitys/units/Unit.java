package com.sorbor.grit.entitys.units;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;
import com.sorbor.grit.entitys.Entity;

public abstract class Unit implements Entity, Disposable {

	private byte layer = 0;

	private int durability = 1000;
	private int health = 500;
	private float armorMultipler = 0.2f;
	private String name = "Avrunkarn";
	private Vector2 renderOffset = new Vector2(32, 128);
	private SpriteBatch sb;
	private TextureRegion tr = new TextureRegion(new Texture("Shield.png"));
	private BitmapFont bmpFont;

	public Unit(SpriteBatch sb) {
		this.sb = sb;
		// Font generating
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Esphimere.otf"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = 21;
		parameter.kerning = true;
		parameter.borderStraight = true;
		parameter.borderColor = com.badlogic.gdx.graphics.Color.BLACK;
		parameter.borderWidth = 2;
		parameter.color = com.badlogic.gdx.graphics.Color.GREEN;
		bmpFont = generator.generateFont(parameter);
		generator.dispose();
	}

	public byte getLayer() {
		return layer;
	}

	public void setLayer(byte layer) {
		this.layer = layer;
	}

	public int getDurability() {
		return durability;
	}

	public void setDurability(int durability) {
		this.durability = durability;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public float getArmorMultipler() {
		return armorMultipler;
	}

	public void setArmorMultipler(float armorMultipler) {
		this.armorMultipler = armorMultipler;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Vector2 getRenderOffset() {
		return renderOffset;
	}

	public void setRenderOffset(Vector2 renderOffset) {
		this.renderOffset = renderOffset;
	}

	public void renderInfo(Vector2 pos) {
		int sprites = (int) Math.round(Math.ceil(((double) durability) / 1000));
		for (int i = 0; i < sprites; i++) {
			sb.draw(tr, pos.x + renderOffset.x + (20 * i), pos.y + renderOffset.y-32);
			
		}
		bmpFont.draw(sb, name, pos.x+renderOffset.x-120, pos.y + renderOffset.y);

	}

	public void dispose() {
		tr.getTexture().dispose();
	}

}
