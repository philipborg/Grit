package com.sorbor.grit.map;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.OutputStream;

import org.kamranzafar.jtar.TarEntry;
import org.kamranzafar.jtar.TarInputStream;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;

import net.jpountz.lz4.LZ4Factory;
import net.jpountz.lz4.LZ4SafeDecompressor;

public class Map implements Disposable {

	private static final int chunkRes = 256;
	private final long id = System.currentTimeMillis();
	private TextureRegion[][] regions;
	private Texture tex;

	/**
	 * 
	 * @param map
	 *            // The filehandle for the zip file
	 * @param quality
	 *            // 1 is best quality
	 * @throws Exception
	 */
	public Map(byte[] mapFileData, float quality) throws Exception {
		//Decompress data to memory
		LZ4Factory fac = LZ4Factory.fastestInstance();
		LZ4SafeDecompressor decomp = fac.safeDecompressor();
		byte[] restored = null;
		decomp.decompress(mapFileData, restored);
		
		//Unarchive data to files
		TarInputStream tis = new TarInputStream(new ByteArrayInputStream(restored));
		TarEntry tarEnt;
		while ((tarEnt = tis.getNextEntry()) != null) {
			int count;
			byte[] data = new byte[2048];
			OutputStream fos = Gdx.files.external("Grit/tmp/" + id + "/" + tarEnt.getName()).write(false);
			BufferedOutputStream dest = new BufferedOutputStream(fos);
			while ((count = tis.read(data)) != -1) {
				dest.write(data, 0, count);
			}
			dest.flush();
			dest.close();
			fos.flush();
			fos.close();
		}
		tis.close();

		if (!Gdx.files.external("Grit/tmp/" + id + "/map.png").exists()) {
			throw new Exception("Map image not found, most be named map.png");
		}
		
		
		//TODO Implement downscaling
		
		tex = new Texture(Gdx.files.external("Grit/tmp/" + id + "/map.png"));
		regions = TextureRegion.split(tex, chunkRes, chunkRes);
	}
	
	public Map(FileHandle img){
		tex = new Texture(img);
		regions = TextureRegion.split(tex, chunkRes, chunkRes);
	}
	
	public void saveTo(FileHandle file){
		
	}
	
	public void render(SpriteBatch sb, Vector2 topLeftScreenCornerPos, Vector2 screenSize){
		for (int x = 0; x < regions.length; x++) {
			for (int y = 0; y < regions[0].length; y++) {
				sb.draw(regions[y][x], x*chunkRes, -y*chunkRes);
			}
		}
	}

	public Map(FileHandle fh, float quality) throws Exception {
		this(fh.readBytes(), quality);
	}

	@Override
	public void dispose() {

	}

}
