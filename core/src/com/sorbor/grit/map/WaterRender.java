package com.sorbor.grit.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.utils.Disposable;

public class WaterRender implements Disposable{
	
	private SpriteBatch sb;
	private Texture tex = new Texture("WaterTexture64.png");
	private ShaderProgram waterShader;
	private ShaderProgram defaultShader;
	private float amplitude = 5f;
	private float angle = 2f;
	private float angleSpeed = 1.0f;
	public static final float PI2 = (float)Math.PI*2;
	
	
	
	
	public WaterRender(SpriteBatch sb) {
		// TODO Auto-generated constructor stub
		this.sb = sb;
		
		ShaderProgram.pedantic = false;
		final String vertexShader = Gdx.files.internal("shaders/vertexShader.glsl").readString();
		final String fragmentShader = Gdx.files.internal("shaders/defaultPixelShader.glsl").readString();
		waterShader = new ShaderProgram(vertexShader, fragmentShader);
		defaultShader = SpriteBatch.createDefaultShader();
		
	}
	
	public void render(){
		
		final float dt = Gdx.graphics.getRawDeltaTime();
		angle += dt * angleSpeed;
		while(angle > PI2)
			angle -= PI2;
		waterShader.begin();
		waterShader.setUniformf("waveData", angle, amplitude);
		waterShader.end();
		sb.setShader(waterShader);
		int y;
		for(int x=0;x<64;x++){
			for(y=0;y<64;y++){
				sb.draw(tex,x*64,(y*64)-4096);
			}
		}
		sb.setShader(defaultShader);
	}
	
	public void dispose(){
		tex.dispose();
		waterShader.dispose();
		defaultShader.dispose();
		
	}

}
