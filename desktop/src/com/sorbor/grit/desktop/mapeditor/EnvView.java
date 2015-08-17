package com.sorbor.grit.desktop.mapeditor;

import java.io.ByteArrayInputStream;

import com.sorbor.grit.map.Map;
import com.sorbor.grit.util.Vector2Double;

import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class EnvView extends Canvas {

	/*
	 * When extending scrollpane private Canvas canvas;
	 * 
	 * public EnvView(Map map, Color paint, double lineWidth) throws Exception {
	 * super();
	 * 
	 * byte[] terrainData = map.getTerrainData(); if (terrainData == null) throw
	 * new Exception("No terrain to launch"); Image terrain = new Image(new
	 * ByteArrayInputStream(terrainData)); canvas = new
	 * Canvas(terrain.getWidth(), terrain.getHeight());
	 * canvas.getGraphicsContext2D().drawImage(terrain, 0, 0); int frequency =
	 * (int) Math.round(terrain.getWidth() / map.getEnvResolution());
	 * canvas.getGraphicsContext2D().setStroke(paint);
	 * canvas.getGraphicsContext2D().setLineWidth(lineWidth);
	 * 
	 * for (int x = 0; x < map.getEnvResolution() + 1; x++) {
	 * canvas.getGraphicsContext2D().strokeLine(x * frequency, 0, x * frequency,
	 * terrain.getHeight()); }
	 * 
	 * for (int y = 0; y < map.getEnvResolution() + 1; y++) {
	 * canvas.getGraphicsContext2D().strokeLine(0, y * frequency,
	 * terrain.getWidth(), y * frequency); }
	 * 
	 * setPannable(true);
	 * 
	 * setOnMouseDragEntered(new EventHandler<MouseEvent>() {
	 * 
	 * @Override public void handle(MouseEvent event) {
	 * System.out.println("SSS"); } });
	 * 
	 * setContent(canvas); }
	 * 
	 * public void setDrawMode(boolean drawModeEnabled){
	 * 
	 * }
	 */

	private Vector2Double primaryStartPos;

	public EnvView(Map map, Color paint, double lineWidth) throws Exception {
		super();
		byte[] terrainData = map.getTerrainData();
		if (terrainData == null)
			throw new Exception("No terrain to launch");
		Image terrain = new Image(new ByteArrayInputStream(terrainData));
		setWidth(terrain.getWidth());
		setHeight(terrain.getHeight());

		getGraphicsContext2D().drawImage(terrain, 0, 0);
		int frequency = (int) Math.round(terrain.getWidth() / map.getEnvResolution());
		getGraphicsContext2D().setStroke(paint);
		getGraphicsContext2D().setLineWidth(lineWidth);

		for (int i = 0; i < map.getEnvResolution() + 1; i++) {
			getGraphicsContext2D().strokeLine(i * frequency, 0, i * frequency, terrain.getHeight());
			getGraphicsContext2D().strokeLine(0, i * frequency, terrain.getWidth(), i * frequency);
		}

		setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (event.isPrimaryButtonDown()) {
					primaryStartPos = new Vector2Double(event.getX(), event.getY());
					System.out.println(primaryStartPos);
				}
			}
		});
		
		setOnMouseMoved(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				System.out.println("SS");
				event.
			}
		});

	}

}
