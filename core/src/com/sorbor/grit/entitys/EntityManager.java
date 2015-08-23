package com.sorbor.grit.entitys;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import com.sorbor.grit.entitys.units.Unit;

public class EntityManager {

	public static final byte BELOW_WATER = 0;
	public static final byte BELOW_WATER_ABOVE = 1;
	public static final byte ON_WATER = 2;
	public static final byte ON_WATER_ABOVE = 3;
	public static final byte ON_LAND = 4;
	public static final byte ON_LAND_ABOVE = 5;
	public static final byte IN_AIR_LOW_ALTITUDE = 6;
	public static final byte IN_AIR_LOW_ALTITUDE_ABOVE = 7;
	public static final byte IN_AIR_HIGH_ALTITUDE = 8;
	public static final byte IN_AIR_HIGH_ALTITUDE_ABOVE = 9;
	public static final byte ORBITAL = 10;
	public static final byte ORBITAL_ABOVE = 11;

	private ArrayList<Entity> entities = new ArrayList<Entity>();
	private ArrayList<ArrayList<Unit>> units;

	public EntityManager() {

		units = new ArrayList<>(12);
		for (int i = 0; i < 12; i++) {
			units.add(new ArrayList<>());
		}

	}

	public void addEntity(Entity entity) {
		if (entity instanceof Unit) {
			Unit unit = (Unit) entity;
			units.get(unit.getLayer()).add(unit);

		}
		entities.add(entity);

	}

	public void update() {
		for (Entity entity : entities) {
			if (!(entity instanceof Unit)) {
				entity.update();

			}
		}

		// entities.get(0).update();

		for (int i = 0; i < units.size(); i++) {

			for (int u = 0; u < units.get(i).size(); u++) {
				units.get(i).get(u).update();

			}
		}

	}

	public void render(SpriteBatch sb) {

		for (Entity entity : entities) {
			if (!(entity instanceof Unit)) {
				entity.render();

			}

		}
		for (int i = 0; i < units.size(); i++) {

			for (Unit unit : units.get(i)) {
				unit.render();
				unit.renderInfo(unit.getPosition());
			}

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
