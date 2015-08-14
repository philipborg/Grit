package com.sorbor.grit.shaders;

import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.utils.Disposable;

public interface Shader extends Disposable {
	
	public void render(); 
	public ShaderProgram getShader();

}
