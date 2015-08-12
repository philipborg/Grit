package com.sorbor.grit.desktop.mapeditor;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class MapEditor extends Application{

	public MapEditor() {
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane bp = new BorderPane(); //Top level border pane
		
		TabPane tabMenu = new TabPane();
		tabMenu.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		bp.setTop(tabMenu);
		
		//File tab
		Tab fileTab = new Tab("File");
		FlowPane fileTabPane = new FlowPane();
		fileTab.setContent(fileTabPane);
		Button createMapButton = new Button("Create Map");
		fileTabPane.getChildren().add(createMapButton);
		Button openMapButton = new Button("Open Map");
		fileTabPane.getChildren().add(openMapButton);
		Button saveMapButton = new Button("Save Map");
		fileTabPane.getChildren().add(saveMapButton);
		
		tabMenu.getTabs().add(fileTab);
		
		Scene scene = new Scene(bp);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
