package com.sorbor.grit.entitys;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import com.sorbor.grit.entitys.units.Unit;

public class EntityManager {

	private byte layerQt = 5;
	private ArrayList<Entity> entities = new ArrayList<Entity>();
	private ArrayList<Unit>[] units;

	public EntityManager() {

		units = new ArrayList[5];

	}

	public void addEntity(Entity entity) {
		if (entity instanceof Unit) {
			Unit temp = (Unit) entity;
			if (units[temp.getLayer()] == null) {
				units[temp.getLayer()] = new ArrayList<Unit>();
			}
			units[temp.getLayer()].add(temp);
		} else {
			entities.add(entity);
		}
	}

	public void update() {
		for (Entity entity : entities) {
			entity.update();
		}
		for (int i = 0; i < units.length; i++) {
			if (units[i] != null) {

				for (Unit unit : units[i]) {
					unit.update();
				}
			}
		}
	}

	public void render(SpriteBatch sb) {

		for (Entity entity : entities) {
			entity.render();
		}

	}

	public Entity getEntity(int index) {
		return entities.get(index);
	}

	public int getId(Entity entity) {
		for (int i = 0; i < entities.size(); i++) {
			if (entities.get(i) == entity)
				return i;
		}
		return -1;
	}

	public void disposeAllChildren() {
		for (Entity entity : entities) {
			if (entity instanceof Disposable) {
				((Disposable) entity).dispose();
			}
		}
	}

}
