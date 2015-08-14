package com.sorbor.grit;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.sorbor.grit.screens.ControllerScreen;

public class Grit extends Game {

	@Override
	public void create() {
		setScreen(new ControllerScreen(this));
	}

	@Override
	public void render() {
		// Clears screen
		Gdx.gl.glClearColor(0f, 0f, 0.7f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// Check if escape is pressed, if so exit
		if (Gdx.input.isKeyPressed(Keys.ESCAPE))
			Gdx.app.exit();

		// Calls screen render
		super.render();
	}
}
