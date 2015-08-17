package com.sorbor.grit.entitys.units;

import com.badlogic.gdx.utils.Disposable;
import com.sorbor.grit.entitys.Entity;

public abstract class Unit implements Entity, Disposable {
	
	private byte layer = 0;
	
	public byte getLayer(){
		return layer;
	}
	
	public void setLayer(byte layer){
		this.layer = layer;
	}
	
	
}
