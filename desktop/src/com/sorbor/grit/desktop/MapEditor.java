package com.sorbor.grit.desktop;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MapEditor extends Application{

	public MapEditor() {
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane bp = new BorderPane();
		Scene scene = new Scene(bp);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
