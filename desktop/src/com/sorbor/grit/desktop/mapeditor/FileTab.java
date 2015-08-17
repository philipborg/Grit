package com.sorbor.grit.desktop.mapeditor;

import java.util.ArrayList;
import java.util.Optional;

import com.badlogic.gdx.files.FileHandle;
import com.sorbor.grit.map.Map;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Tab;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.FlowPane;
import javafx.stage.FileChooser;

public class FileTab extends Tab {

	public FileTab(MapEditor mapEd) {
		super("File");
		FlowPane fileTabPane = new FlowPane();
		this.setContent(fileTabPane);
		Button createMapButton = new Button("Create Map");
		createMapButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				mapEd.wishToSave();
				FileChooser fc = new FileChooser();
				fc.setTitle("Create map using texture");
				FileHandle fh = new FileHandle(fc.showOpenDialog(mapEd.primStage));
				if (fh.file() != null) {
					try {
						// Environment size selection
						ArrayList<String> choices = new ArrayList<>();
						choices.add("Small (128x128)");
						choices.add("Medium (256x256)");
						choices.add("Large (512x512)");
						ChoiceDialog<String> envChoice = new ChoiceDialog<String>(choices.get(0), choices);
						envChoice.setTitle("Environment resolution");
						envChoice.setHeaderText("Choose environment resolution");
						envChoice.setContentText("It is possible to adjust this later, this impacts performance.");
						Optional<String> result = envChoice.showAndWait();
						Map tmpMap = null;
						if (result.isPresent()) {
							switch (result.get()) {
							case "Small (128x128)":
								tmpMap = new Map(fh, 7);
								break;
							case "Medium (256x256)":
								tmpMap = new Map(fh, 8);
								break;
							case "Large (512x512)":
								tmpMap = new Map(fh, 9);
								break;
							}
						} else {
							tmpMap = new Map(fh, 8);
						}

						mapEd.map = tmpMap;
						mapEd.mapFile = null;
						mapEd.loadMap();
					} catch (Exception e) {
						ExceptionAlert.alertException(e, "Failed loading Image");
					}
				}
			}
		});

		fileTabPane.getChildren().add(createMapButton);

		Button openMapButton = new Button("Open Map");
		openMapButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				mapEd.wishToSave();
				FileChooser fc = new FileChooser();
				fc.setTitle("Open Map");
				FileHandle fh = new FileHandle(fc.showOpenDialog(mapEd.primStage));
				if (fh.file() != null) {
					Alert alert = new Alert(AlertType.CONFIRMATION);
					alert.setTitle("Overwrite");
					alert.setHeaderText(null);
					alert.setContentText("Do you wish to save all changes to the current map file?");
					Optional<ButtonType> result = alert.showAndWait();
					try {
						mapEd.map = new Map(fh.readBytes(), true);
					} catch (Exception e) {
						ExceptionAlert.alertException(e, "Failed to open map");
					}
					if (result.get() == ButtonType.OK)
						mapEd.mapFile = fh;
					else
						mapEd.mapFile = null;
					try {
						mapEd.loadMap();
					} catch (Exception e) {
						ExceptionAlert.alertException(e, "Failed to load map.");
						mapEd.map.dispose();
						mapEd.map = null;
						mapEd.mapFile = null;
					}
				}
			}
		});
		fileTabPane.getChildren().add(openMapButton);

		Button saveMapButton = new Button("Save Map");
		saveMapButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				mapEd.save();
			}
		});
		fileTabPane.getChildren().add(saveMapButton);

		Button saveMapAsButton = new Button("Save Map As...");
		saveMapAsButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				mapEd.saveAs();
			}
		});
		fileTabPane.getChildren().add(saveMapAsButton);
	}

}
