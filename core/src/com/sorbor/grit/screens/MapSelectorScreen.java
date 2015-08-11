package com.sorbor.grit.screens;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.utils.Disposable;
import com.sorbor.grit.Grit;
import com.sorbor.grit.input.InputController;

class ListMap implements Disposable{
	FileHandle map;
	Texture tex;
	public ListMap(FileHandle file){
		map = file;
	}
	@Override
	public void dispose() {
		tex.dispose();
	}
}

public class MapSelectorScreen implements Screen{

	InputController ic;
	Grit game;
	BitmapFont bmpFont;
	ArrayList<ListMap> maps = new ArrayList<ListMap>();
	int index = 0;
	
	public MapSelectorScreen(Grit game, InputController ic) {
		this.game = game;
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Esphimere Thin.otf"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = 40;
		bmpFont = generator.generateFont(parameter);
		generator.dispose();
		
		//Load maps
		FileHandle[] files = Gdx.files.internal("maps").list();
		for (FileHandle fileHandle : files) {
			if(fileHandle.extension().equals("tmx")) maps.add(new ListMap(fileHandle));
		}
	}

	@Override
	public void show() {
		
	}

	@Override
	public void render(float delta) {
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
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
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
