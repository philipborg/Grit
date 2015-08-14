package com.sorbor.grit.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.sorbor.grit.Grit;
import com.sorbor.grit.desktop.mapeditor.MapEditor;

public class DesktopLauncher {
	public static void main(String[] arg) {

		boolean normalStart = true;
		if (arg.length > 0) {
			if (arg[0].equals("mapeditor")) {
				normalStart = false;
				MapEditor.launch(MapEditor.class, arg);
			}
		}
		if (normalStart) {
			LwjglApplicationConfiguration config = new LwjglApplicationConfiguration(); // Create
																						// application
			new LwjglApplication(new Grit(), config); // Start application
			System.out.println("TTTTT");
		}
	}
}
