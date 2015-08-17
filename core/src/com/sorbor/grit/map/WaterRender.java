package com.sorbor.grit.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.utils.Disposable;

public class WaterRender implements Disposable {

	private SpriteBatch sb;
	private final int chunkRes = 256/4;
	private Texture tex = new Texture("WaterTexture.png");
	private TextureRegion texRegions[][];
	private ShaderProgram waterShader;
	private ShaderProgram defaultShader;
	private float amplitude = 10f;
	private float angle = 2f;
	private float angleSpeed = 0.85f;
	public static final float PI2 = (float) Math.PI * 2;

	public WaterRender(SpriteBatch sb) {
		// TODO Auto-generated constructor stub
		this.sb = sb;

		ShaderProgram.pedantic = false;
		final String vertexShader = Gdx.files.internal("shaders/vertexShader.glsl").readString();
		final String fragmentShader = Gdx.files.internal("shaders/defaultPixelShader.glsl").readString();
		waterShader = new ShaderProgram(vertexShader, fragmentShader);
		defaultShader = SpriteBatch.createDefaultShader();
		texRegions = TextureRegion.split(tex, chunkRes, chunkRes);

	}

	public void render() {

		final float dt = Gdx.graphics.getRawDeltaTime();
		angle += dt * angleSpeed;
		while (angle > PI2)
			angle -= PI2;
		waterShader.begin();
		waterShader.setUniformf("waveData", angle, amplitude);
		waterShader.end();
		sb.setShader(waterShader);
		int y;
		for (int x = 0; x < texRegions.length; x++) {
			for (y = 0; y < texRegions[0].length; y++) {
				sb.draw(texRegions[y][x], x * chunkRes, -y * chunkRes + 256 + 64);
			}
		}
		sb.setShader(defaultShader);
	}

	public void dispose() {
		tex.dispose();
		waterShader.dispose();
		defaultShader.dispose();

	}

}
