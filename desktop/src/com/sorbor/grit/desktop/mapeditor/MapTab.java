package com.sorbor.grit.desktop.mapeditor;

import java.util.ArrayList;
import java.util.Optional;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.LifecycleListener;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.sorbor.grit.Grit;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Tab;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

class StartTestGame implements Runnable, LifecycleListener {
	Stage stage;
	public StartTestGame(Stage stage){
		this.stage = stage;
	}
	
	@Override
	public void run() {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 500;
		config.height = 500;
		new LwjglApplication(new Grit(), config);
		Gdx.app.addLifecycleListener(this);
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		Gdx.app.removeLifecycleListener(this);
		stage.show();
	}
}

public class MapTab extends Tab {

	public MapTab(MapEditor mapEd) {
		super("Map");
		FlowPane fp = new FlowPane();
		//Test map button
		Button startMapButt = new Button("Test map");
		startMapButt.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				mapEd.wishToSave();
				mapEd.primStage.hide();
				new Thread(new StartTestGame(mapEd.primStage)).start();
			}
		});
		fp.getChildren().add(startMapButt);
		
		// Set environment resolution button
		Button setEnvironmentResBut = new Button("Set Environment resolution");
		setEnvironmentResBut.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				ArrayList<Integer> choices = new ArrayList<>();
				for (int i = 1; i < 13; i++){
					choices.add((int) Math.round(Math.pow(2, i)));
					System.out.println(choices.get(choices.size()-1));
				}
				ChoiceDialog<Integer> dialog = new ChoiceDialog<>(Integer.valueOf(128), choices);
				dialog.setTitle("Environment resolution");
				dialog.setHeaderText("Sets the enviroemnt resolution");
				dialog.setContentText(
						"Warning, will reset all environment."
						+ "\nCancel will do nothing."
						+ "\nSize is measured in length meaning actual number of squares will be squared.");
				Optional<Integer> res = dialog.showAndWait();
				if (res.isPresent()) {
					int value = (int) Math.round(Math.log(res.get()) / Math.log(2));
					mapEd.map.setEnvRes(value);
				}
			}
		});
		fp.getChildren().add(setEnvironmentResBut);
		
		setContent(fp);
	}

}
