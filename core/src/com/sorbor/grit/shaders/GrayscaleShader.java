package com.sorbor.grit.shaders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Matrix4;

public class GrayscaleShader implements Shader {
	
	private ShaderProgram shader;
	
	public GrayscaleShader() {
		// TODO Auto-generated constructor stub
		ShaderProgram.pedantic = false;
		final String vertexShader = Gdx.files.internal("shaders/grayscaleVertexShader.glsl").readString();
		final String fragmentShader = Gdx.files.internal("shaders/grayscalePixelShader.glsl").readString();
		shader = new ShaderProgram(vertexShader, fragmentShader);

	}
	
	public void render(){
		
	}
	
	public void setProjectionMatrix(Matrix4 matrix){
		//shader.setUniformMatrix("u_projTrans", matrix);
	}
	
	public ShaderProgram getShader(){
		return shader;
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		shader.dispose();
		
	}

}
