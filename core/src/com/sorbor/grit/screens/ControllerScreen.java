package com.sorbor.grit.screens;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.math.Vector3;
import com.sorbor.grit.Grit;
import com.sorbor.grit.input.GameController;
import com.sorbor.grit.input.InputController;
import com.sorbor.grit.input.KeyboardController;

public class ControllerScreen implements Screen, ControllerListener {

	Grit game;
	BitmapFont bmpFont;
	SpriteBatch sb = new SpriteBatch();
	ArrayList<InputController> registeredControllers = new ArrayList<InputController>();

	public ControllerScreen(Grit game) {
		this.game = game;
		Controllers.addListener(this);
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Esphimere Thin.otf"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = 25;
		bmpFont = generator.generateFont(parameter);
		generator.dispose();
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		sb.begin();
		bmpFont.draw(sb, "Press a button to register a controller", 20, Gdx.graphics.getHeight() - 20);
		bmpFont.draw(sb, "Press a button to unregister a controller", 20, Gdx.graphics.getHeight() - 50);
		bmpFont.draw(sb, "Press space to start", 20, Gdx.graphics.getHeight() - 80);
		for (int i = 0; i < registeredControllers.size(); i++) {
			bmpFont.draw(sb, "Player " + (i + 1) + "-" + registeredControllers.get(i), 20,
					Gdx.graphics.getHeight() - (110 - (i * -30)));
		}
		sb.end();
		if (Gdx.input.isKeyJustPressed(Keys.A) || Gdx.input.isKeyJustPressed(Keys.D)
				|| Gdx.input.isKeyJustPressed(Keys.W) || Gdx.input.isKeyJustPressed(Keys.S)) {
			registeredControllers.add(new KeyboardController());
		}

		if (Gdx.input.isKeyPressed(Keys.SPACE) && registeredControllers.size() > 0) {
			Controllers.removeListener(this);
			sb.dispose();
			bmpFont.dispose();
			InputController[] ic = new InputController[registeredControllers.size()];
			registeredControllers.toArray(ic);
			Controllers.removeListener(this);
			game.setScreen(new GameScreen(game, ic));
		}
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void connected(Controller controller) {
		// TODO Auto-generated method stub

	}

	@Override
	public void disconnected(Controller controller) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean buttonDown(Controller controller, int buttonCode) {
		boolean found = false;
		for (int i = 0; i < registeredControllers.size(); i++) {
			// Checks if controller is already found, if so, remove it
			if (registeredControllers.get(i) instanceof GameController) {
				if (((GameController) registeredControllers.get(i)).getController() == controller) {
					found = true;
					registeredControllers.remove(i--);
				}
			}
		}
		if (!found) { // If controller was not found, add it
			registeredControllers.add(new GameController(controller));
		}
		return false;
	}

	@Override
	public boolean buttonUp(Controller controller, int buttonCode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean axisMoved(Controller controller, int axisCode, float value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean povMoved(Controller controller, int povCode, PovDirection value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean xSliderMoved(Controller controller, int sliderCode, boolean value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean ySliderMoved(Controller controller, int sliderCode, boolean value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean accelerometerMoved(Controller controller, int accelerometerCode, Vector3 value) {
		// TODO Auto-generated method stub
		return false;
	}

}
