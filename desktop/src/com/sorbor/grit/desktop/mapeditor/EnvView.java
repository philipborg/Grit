package com.sorbor.grit.desktop.mapeditor;

import java.io.ByteArrayInputStream;

import com.badlogic.gdx.math.Vector2;
import com.sorbor.grit.map.Map;

import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class EnvView extends Pane {

	private Canvas collisionsCanvas;
	private Canvas lineDrawCanvas;
	private Canvas bg;
	private Map map;
	private int frequency;
	private Vector2 lastPanPos, startLinePos;
	private EnvView envV;

	public EnvView(Map map, Color strokeColour, double strokeSize) throws Exception {
		super();
		this.map = map;
		this.envV = this;
		byte[] terrainData = map.getTerrainData();
		if (terrainData == null)
			throw new Exception("No terrain to launch");
		Image terrain = new Image(new ByteArrayInputStream(terrainData));
		bg = new Canvas(terrain.getWidth(), terrain.getHeight());
		bg.getGraphicsContext2D().drawImage(terrain, 0, 0);
		frequency = (int) Math.round(terrain.getWidth() / map.getEnvResolution());
		bg.getGraphicsContext2D().setStroke(strokeColour);
		bg.getGraphicsContext2D().setLineWidth(strokeSize * 0.75f);
		for (int i = 0; i < map.getEnvResolution() + 1; i++) {
			bg.getGraphicsContext2D().strokeLine(i * frequency, 0, i * frequency, terrain.getHeight());
			bg.getGraphicsContext2D().strokeLine(0, i * frequency, terrain.getWidth(), i * frequency);
		}
		getChildren().add(bg);

		collisionsCanvas = new Canvas(terrain.getWidth(), terrain.getHeight());
		collisionsCanvas.getGraphicsContext2D().setLineWidth(strokeSize);
		lineDrawCanvas = new Canvas(terrain.getWidth(), terrain.getHeight());
		lineDrawCanvas.getGraphicsContext2D().setLineWidth(strokeSize * 1.25);
		getChildren().add(collisionsCanvas);
		getChildren().add(lineDrawCanvas);
		lineDrawCanvas.toBack();
		collisionsCanvas.toBack();
		bg.toBack();
		updateCollision(Color.GREEN);

		setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				switch (event.getButton()) {
				case MIDDLE:
					break;
				case NONE:
					break;
				case SECONDARY:
					lastPanPos = new Vector2((float) event.getX(), (float) event.getY());
					break;
				case PRIMARY:
					startLinePos = new Vector2((float) (event.getX() - bg.getTranslateX()),
							(float) (event.getY() - bg.getTranslateY()));
					System.out.println("Line draw begun at " + startLinePos);
					break;
				default:
					break;
				}
			}
		});

		setOnMouseReleased(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				switch (event.getButton()) {
				case PRIMARY:
					// Clear screen
					lineDrawCanvas.getGraphicsContext2D().clearRect(0, 0, lineDrawCanvas.getWidth(),
							lineDrawCanvas.getHeight());

					// Calculate new collisions
					int startPosX = (int) Math.round(Math.max(
							Math.ceil(Math.min(startLinePos.x, event.getX() - bg.getTranslateX()) / frequency), 0));
					int startPosY = (int) Math.round(Math.max(
							Math.ceil(Math.min(startLinePos.y, event.getY() - bg.getTranslateY()) / frequency), 0));
					int endPosX = (int) Math.round(Math.min(
							Math.floor(Math.max(startLinePos.x, event.getX() - bg.getTranslateX()) / frequency),
							map.getEnvResolution() + 1));
					int endPosY = (int) Math.round(Math.min(
							Math.floor(Math.min(startLinePos.y, event.getY() - bg.getTranslateY()) / frequency),
							map.getEnvResolution() + 1));

					System.out.println("StartPosX: " + startPosX + " StartPosY: " + startPosY + "EndPosX: " + endPosX
							+ " EndPosY: " + endPosY);
					double[][] lineCrosses = new double[1 + endPosX - startPosX][1 + endPosY - startPosY];
					for (long x = startPosX; x < endPosX; x++) {
						for (long y = startPosY; y < endPosY; y++) {

						}
					}

					startLinePos = null;
					break;
				case MIDDLE:
					break;
				case NONE:
					break;
				case SECONDARY:
					lastPanPos = null;
					break;
				default:
					break;
				}
			}
		});

		setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (lastPanPos != null && event.isSecondaryButtonDown()) {
					Vector2 vec = new Vector2((float) event.getX(), (float) event.getY()).sub(lastPanPos)
							.add((float) bg.getTranslateX(), (float) bg.getTranslateY());
					bg.setTranslateX(vec.x);
					bg.setTranslateY(vec.y);
					collisionsCanvas.setTranslateX(vec.x);
					collisionsCanvas.setTranslateY(vec.y);
					lineDrawCanvas.setTranslateX(vec.x);
					lineDrawCanvas.setTranslateY(vec.y);
				}
				if (event.isSecondaryButtonDown()) {
					lastPanPos = new Vector2((float) event.getX(), (float) event.getY());
				}

				if (event.isPrimaryButtonDown() && startLinePos != null) {
					lineDrawCanvas.getGraphicsContext2D().clearRect(-bg.getTranslateX(), -bg.getTranslateY(),
							envV.getWidth(), envV.getHeight());
					lineDrawCanvas.getGraphicsContext2D().strokeLine(startLinePos.x, startLinePos.y,
							event.getX() - bg.getTranslateX(), event.getY() - bg.getTranslateY());
				}

			}
		});
	}

	public void updateCollision(Color color) {
		collisionsCanvas.getGraphicsContext2D().setFill(Color.TRANSPARENT);
		collisionsCanvas.getGraphicsContext2D().fill();
		collisionsCanvas.getGraphicsContext2D().setStroke(color);
		int size = map.getEnvResolution();
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				if (map.getEnvironment()[x][y][0] != 0 && map.getEnvironment()[x][y][1] != 0)
					collisionsCanvas.getGraphicsContext2D().strokeLine(
							(x + map.getXEnvFactorOffset(x, y, 0)) * frequency,
							(y + map.getYEnvFactorOffset(x, y, 0)) * frequency,
							(x + map.getXEnvFactorOffset(x, y, 1)) * frequency,
							(y + map.getYEnvFactorOffset(x, y, 1)) * frequency);
			}
		}
	}

}
