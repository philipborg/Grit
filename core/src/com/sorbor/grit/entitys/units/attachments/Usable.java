package com.sorbor.grit.entitys.units.attachments;

import com.badlogic.gdx.math.Vector2;

public interface Usable extends Attachment{
	
	public void use(float unitAngle, float aimAngle, Vector2 pos);
	public void secondUse(float unitAngle, float aimAngle, Vector2 pos);
	public boolean hasAltUse();
	
}
