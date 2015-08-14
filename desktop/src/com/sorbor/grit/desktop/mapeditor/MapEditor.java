package com.sorbor.grit.desktop.mapeditor;

import java.io.IOException;
import java.util.Optional;

import com.badlogic.gdx.files.FileHandle;
import com.sorbor.grit.map.Map;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

class ShowStage implements Runnable{
	@Override
	public void run() {
		
	}
}

public class MapEditor extends Application {

	Map map;
	FileHandle mapFile;
	Stage primStage;
	private boolean savedSinceLastChange = false;
	private boolean tabsLoaded = false;
	private TabPane tabMenu;

	public MapEditor() {

	}

	public void save() {
		if (map != null) {
			if (mapFile == null) {
				saveAs();
			} else {
				try {
					map.saveTo(mapFile);
					savedSinceLastChange = true;
				} catch (IOException e) {
					ExceptionAlert.alertException(e, "Failed to save map");
				}
			}
		} else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("No map");
			alert.setHeaderText("No map found");
			alert.setContentText("Before saving you need to create or load a map");
			alert.showAndWait();
		}
	}

	public void saveAs() {
		if (map != null) {
			FileChooser saveFileChooser = new FileChooser();
			saveFileChooser.setTitle("Save map");
			mapFile = new FileHandle(saveFileChooser.showSaveDialog(primStage));
			save();
		} else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("No map");
			alert.setHeaderText("No map found");
			alert.setContentText("Before saving you need to create or load a map");
			alert.showAndWait();
		}
	}

	public void wishToSave() {
		if (map != null && !savedSinceLastChange) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Save map");
			alert.setHeaderText("Save map?");
			alert.setContentText("Do you wish to save the current map?");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK) {
				save();
			}
		}
	}

	public void loadMap() {
		System.out.println("Loading tabs");
		if (map != null && !tabsLoaded) {
			System.out.println("Tabs loaded");
			tabMenu.getTabs().add(new MapTab(this));
			tabsLoaded = true;
		} else {
			System.out.println("Tabs already loaded");
		}
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primStage = primaryStage;
		BorderPane bp = new BorderPane(); // Top level border pane

		tabMenu = new TabPane();
		tabMenu.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		bp.setTop(tabMenu);

		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {
				wishToSave();
			}
		});

		tabMenu.getTabs().add(new FileTab(this));

		Scene scene = new Scene(bp);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
