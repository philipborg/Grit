package com.sorbor.grit.map;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Disposable;

public class Map implements Disposable {

	
	/**
	 * 
	 * @param map // The filehandle for the zip file. Does not close the stream automatically.
	 * @param quality // 1 is best quality
	 * @throws IOException
	 */
	
	public Map(InputStream map, float quality) throws IOException{
		ZipInputStream zis = new ZipInputStream(map);
		ZipEntry zipEntry;
		while((zipEntry = zis.getNextEntry()) != null){ //Iterates all entries
			switch (zipEntry.getName()) {
			
			case "level.png":
				//Texture data
				
				break;
				
			default:
				System.out.println("Dead file" + zipEntry.getName());
				break;
			}
		}
	}
	
	public Map(FileHandle map, float quality) throws IOException {
		this(map.read(8192), quality);
	}
	
	public Map(File map, float quality) throws IOException {
		this(new FileInputStream(map), quality);
	}

	@Override
	public void dispose() {

	}

}
