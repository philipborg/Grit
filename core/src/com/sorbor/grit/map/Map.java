package com.sorbor.grit.map;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.Disposable;

public class Map implements Disposable {

	private TiledMap tmap;
	private MapRenderer mapRend;

	public Map(String map, OrthographicCamera cam) {
		tmap = new TmxMapLoader().load("maps/" + map + ".tmx");
		mapRend = new OrthogonalTiledMapRenderer(tmap, 1);
		mapRend.setView(cam);
	}

	public void render() {
		mapRend.render();
	}

	@Override
	public void dispose() {
		tmap.dispose();
	}

}
