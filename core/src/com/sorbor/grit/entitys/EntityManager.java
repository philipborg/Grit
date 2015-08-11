package com.sorbor.grit.entitys;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;

public class EntityManager {

	
	private ArrayList<Entity> entities = new ArrayList<Entity>();
	public EntityManager() {
		
	}
	
	public void addEntity(Entity entity){
		entities.add(entity);
	}
	
	public void update(){
		for (Entity entity : entities) {
			entity.update();
		}
	}
	
	public void render(SpriteBatch sb){
		for (Entity entity : entities) {
			entity.render();
		}
	}
	
	public Entity getEntity(int index){
		return entities.get(index);
	}
	
	public int getId(Entity entity){
		for (int i = 0; i < entities.size(); i++) {
			if(entities.get(i)==entity) return i;
		}
		return -1;
	}
	
	public void disposeAllChildren(){
		for (Entity entity : entities) {
			if(entity instanceof Disposable){
				((Disposable) entity).dispose();
			}
		}
	}

}
