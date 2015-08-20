package com.sorbor.grit.desktop.mapeditor;

import java.io.ByteArrayInputStream;

import com.sorbor.grit.map.Map;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class EnvView extends Pane {

	private Canvas collisionsCanvas;
	private Canvas lineDrawCanvas;
	private Map map;
	
	public EnvView(Map map, Color strokeColour, double strokeSize) throws Exception {
		super();
		this.map = map;
		byte[] terrainData = map.getTerrainData();
		if (terrainData == null)
			throw new Exception("No terrain to launch");
		Image terrain = new Image(new ByteArrayInputStream(terrainData));
		Canvas bg = new Canvas(terrain.getWidth(), terrain.getHeight());
		bg.getGraphicsContext2D().drawImage(terrain, 0, 0);
		int frequency = (int) Math.round(terrain.getWidth() / map.getEnvResolution());
		bg.getGraphicsContext2D().setStroke(strokeColour);
		bg.getGraphicsContext2D().setLineWidth(strokeSize);
		for (int i = 0; i < map.getEnvResolution() + 1; i++) {
			bg.getGraphicsContext2D().strokeLine(i * frequency, 0, i * frequency, terrain.getHeight());
			bg.getGraphicsContext2D().strokeLine(0, i * frequency, terrain.getWidth(), i * frequency);
		}
		getChildren().add(bg);
		
		collisionsCanvas = new Canvas(terrain.getWidth(), terrain.getHeight());
		lineDrawCanvas = new Canvas(terrain.getWidth(), terrain.getHeight());
		getChildren().add(collisionsCanvas);
		getChildren().add(lineDrawCanvas);
		
		collisionsCanvas.toFront();
		bg.toFront();
	}
	
	public void updateCollision(Color color){
		collisionsCanvas.getGraphicsContext2D().setFill(Color.TRANSPARENT);
		collisionsCanvas.getGraphicsContext2D().fill();
		
	}

}
