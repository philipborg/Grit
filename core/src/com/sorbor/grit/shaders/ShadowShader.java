package com.sorbor.grit.shaders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;

public class ShadowShader implements Shader {

	private ShaderProgram shader;

	public ShadowShader() {
		// TODO Auto-generated constructor stub
		ShaderProgram.pedantic = false;
		final String vertexShader = Gdx.files.internal("shaders/grayscaleVertexShader.glsl").readString();
		final String fragmentShader = Gdx.files.internal("shaders/shadowPixelShader.glsl").readString();
		shader = new ShaderProgram(vertexShader, fragmentShader);

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		shader.dispose();

	}

	@Override
	public void render() {
		// TODO Auto-generated method stub

	}

	@Override
	public ShaderProgram getShader() {
		// TODO Auto-generated method stub
		return shader;
	}

}
