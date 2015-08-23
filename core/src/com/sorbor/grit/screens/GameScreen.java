package com.sorbor.grit.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sorbor.grit.Grit;
import com.sorbor.grit.entitys.EntityManager;
import com.sorbor.grit.entitys.units.Car;
import com.sorbor.grit.entitys.units.Helicopter;
import com.sorbor.grit.input.InputController;
import com.sorbor.grit.input.KeyboardController;
import com.sorbor.grit.map.Map;
import com.sorbor.grit.map.WaterRender;
import com.sorbor.grit.util.CameraMovement;
import com.sorbor.grit.util.PanCalculation;

public class GameScreen implements Screen {

	Grit game;
	EntityManager em = new EntityManager();
	SpriteBatch sb = new SpriteBatch();
	CameraMovement cv;
	Viewport vp;
	Map map;
	WaterRender wr;
	BitmapFont bmpFont;
	ShaderProgram defaultShader;

	public GameScreen(Grit game, InputController[] ic) {
		this.game = game;
		System.out.println(Controllers.getControllers().size);
		em.addEntity(new Car(sb, new KeyboardController()));
		for (InputController inputController : ic) {
			em.addEntity(new Helicopter(sb, inputController));
		}

		map = new Map(Gdx.files.internal("Map01.png"));
		wr = new WaterRender(sb);

		// Font generating
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Esphimere Thin.otf"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = 25;
		bmpFont = generator.generateFont(parameter);
		generator.dispose();

		// Create shaders
		defaultShader = SpriteBatch.createDefaultShader();

	}

	@Override
	public void show() {
		vp = new FitViewport(1920, 1080); // Makes virtual screensize QHD
		cv = new CameraMovement(vp.getCamera());
	}

	@Override
	public void render(float delta) {
		PanCalculation.setCamPos(vp.getCamera().position.x, vp.getCamera().position.y);
		cv.update();
		cv.setPosition(em.getEntity(0).getPosition());
		vp.getCamera().update(); // Updates the camera
		sb.setProjectionMatrix(vp.getCamera().combined); // Sets the projection
															// matrix
		em.update(); // Updates all entities

		sb.begin();

		wr.render();
		map.render(sb, new Vector2(), new Vector2());
		em.render(sb); // Renders all entities
		bmpFont.draw(sb, "" + Gdx.graphics.getFramesPerSecond(), em.getEntity(0).getPosition().x - 500,
				em.getEntity(0).getPosition().y + 500);
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
		wr.dispose();
	}

}
