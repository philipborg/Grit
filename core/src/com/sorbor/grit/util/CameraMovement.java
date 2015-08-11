package com.sorbor.grit.util;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class CameraMovement {

	Camera cam;
	Vector3 pos = new Vector3(0, 0, 0);

	public CameraMovement(Camera cam) {
		this.cam = cam;
	}

	public void setPosition(Vector2 pos) {
		this.pos = new Vector3(pos.x,pos.y, cam.position.z);
	}

	public void update() {
		
		cam.translate(new Vector3(pos.sub(cam.position)).scl(0.02f));
	}


}
