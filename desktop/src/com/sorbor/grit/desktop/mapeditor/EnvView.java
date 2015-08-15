package com.sorbor.grit.desktop.mapeditor;

import java.io.ByteArrayInputStream;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;

public class EnvView extends Canvas {

	MapEditor mapEd;

	public EnvView(MapEditor mapEd) {
		super();
		this.mapEd = mapEd;
	}
	
	public void viewMap() throws Exception{
		if(mapEd.map==null) throw new Exception("No map to launch");
		byte[] terrainData = mapEd.map.getTerrainData();
		if(terrainData==null) throw new Exception("No terrain to launch");
		Image terrain = new Image(new ByteArrayInputStream(terrainData));
		getGraphicsContext2D().drawImage(terrain, 0, 0);
	}

}
