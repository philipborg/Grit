package com.sorbor.grit.entitys.units;

import com.badlogic.gdx.utils.Disposable;
import com.sorbor.grit.entitys.Entity;

public interface Unit extends Entity, Disposable {
	public byte getHeightLevel();
}
