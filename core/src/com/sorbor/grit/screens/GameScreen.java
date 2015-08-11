package com.sorbor.grit.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sorbor.grit.Grit;
import com.sorbor.grit.entitys.EntityManager;
import com.sorbor.grit.entitys.units.Car;
import com.sorbor.grit.entitys.units.Helicopter;
import com.sorbor.grit.input.InputController;
import com.sorbor.grit.util.CameraMovement;

public class GameScreen implements Screen {

	Grit game;
	EntityManager em = new EntityManager();
	SpriteBatch sb = new SpriteBatch();
	CameraMovement cv;
	Viewport vp;

	public GameScreen(Grit game, InputController[] ic) {
		this.game = game;
		System.out.println(Controllers.getControllers().size);
		for (InputController inputController : ic) {
			em.addEntity(new Helicopter(sb, inputController));
		}

	}

	@Override
	public void show() {
		vp = new FitViewport(2560, 1440); // Makes virtual screensize QHD
		cv = new CameraMovement(vp.getCamera());
	}

	@Override
	public void render(float delta) {
		cv.update();
		cv.setPosition(em.getEntity(0).getPosition());
		vp.getCamera().update(); // Updates the camera
		sb.setProjectionMatrix(vp.getCamera().combined); // Sets the projection
															// matrix
		em.update(); // Updates all entities
		sb.begin();
		em.render(sb); // Renders all entities
		sb.end();

	}

	@Override
	public void resize(int width, int height) {

		// Checks that it is not too small
		if (width < 256) {
			width = 256;
			Gdx.graphics.setDisplayMode(width, height, Gdx.graphics.isFullscreen());
		}
		if (height < 256) {
			height = 256;
			Gdx.graphics.setDisplayMode(width, height, Gdx.graphics.isFullscreen());
		}

		vp.update(Math.max(width, 256), Math.max(height, 256));// Updates
																// viewport
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
		sb.dispose(); // Disposes spritebatch
		em.disposeAllChildren(); // Disposes all entities
	}

}
