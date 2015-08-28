package com.sorbor.grit.map;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystemException;
import java.util.HashMap;

import org.kamranzafar.jtar.TarEntry;
import org.kamranzafar.jtar.TarHeader;
import org.kamranzafar.jtar.TarInputStream;
import org.kamranzafar.jtar.TarOutputStream;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.sorbor.grit.util.Serialization;

import ar.com.hjg.pngj.PngReader;
import net.jpountz.lz4.LZ4Compressor;
import net.jpountz.lz4.LZ4Factory;
import net.jpountz.lz4.LZ4SafeDecompressor;

public class Map implements Disposable {

	private static final int chunkRes = 256;
	private TextureRegion[][] regions;
	private Texture tex;
	private byte[] imageTerrain;
	private static final LZ4Factory fac = LZ4Factory.fastestInstance();
	private float[][][] environment;

	/**
	 * 
	 * @param map
	 *            // The filehandle for the zip file
	 * @param quality
	 *            // 1 is best quality
	 * @throws Exception
	 */
	public Map(byte[] mapFileData, int quality, boolean mapEditor) throws Exception {
		System.out.println("Using compression method " + fac.toString());
		// Decompress data to memory
		LZ4SafeDecompressor decomp = fac.safeDecompressor();
		byte[] restored = new byte[134217728];
		decomp.decompress(mapFileData, restored);

		// Unarchive data to files
		TarInputStream tis = new TarInputStream(new ByteArrayInputStream(restored));
		TarEntry tarEnt;
		HashMap<String, byte[]> files = new HashMap<>();
		while ((tarEnt = tis.getNextEntry()) != null) {
			int count;
			byte[] data = new byte[8192];
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			while ((count = tis.read(data)) != -1) {
				baos.write(data, 0, count);
			}
			baos.flush();
			files.put(tarEnt.getName(), baos.toByteArray());
			baos.close();
		}
		tis.close();
		Kryo kryo = new Kryo();
		kryo.register(float[][][].class, Serialization.ENVIRONMENT);

		if (!files.containsKey("environment.bin")) {
			throw new FileNotFoundException("environment.bin not found in map file");
		}

		Input input = new Input(files.get("environment.bin"));
		environment = kryo.readObject(input, float[][][].class);
		input.close();

		imageTerrain = files.get("terrain.png");

		if (imageTerrain == null) {
			throw new FileNotFoundException("terrain.png not found in map file");
		}

		if (!mapEditor) {
			loadForGame(false);
		}
	}

	public int getEnvResolution() {
		return environment.length;
	}

	/**
	 * 
	 * @return Raw file data of a PNG
	 */
	public byte[] getTerrainData() {
		if (imageTerrain == null)
			return null;
		byte[] tmp = new byte[imageTerrain.length];
		System.arraycopy(imageTerrain, 0, tmp, 0, tmp.length);
		return tmp;
	}

	public void loadForGame(boolean keepMapEditor) {
		if (tex != null)
			tex.dispose();
		Pixmap pixMap = new Pixmap(imageTerrain, 0, imageTerrain.length);
		if (!keepMapEditor)
			imageTerrain = null;
		tex = new Texture(pixMap);
		pixMap.dispose();
		regions = TextureRegion.split(tex, chunkRes, chunkRes);
	}

	public Map(byte[] map, boolean mapEditor) throws Exception {
		this(map, 1, mapEditor);
	}

	public Map(FileHandle img, int envRes) throws FileNotFoundException, FileSystemException {
		if (!img.exists())
			throw new FileNotFoundException();
		if (!img.extension().equals("png"))
			throw new FileSystemException("File most be PNG, file found was " + img.extension());
		PngReader pngr = new PngReader(img.file());
		if ((pngr.imgInfo.cols & -pngr.imgInfo.cols) != pngr.imgInfo.cols
				|| (pngr.imgInfo.rows & -pngr.imgInfo.rows) != pngr.imgInfo.rows) {
			throw new FileSystemException("Image most be a power of Two");
		}
		setEnvRes(envRes);
		imageTerrain = img.readBytes();
	}

	public void saveTo(FileHandle file) throws IOException {
		System.out.println("Using compression method " + fac.toString());
		// Tar the file
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		TarOutputStream tos = new TarOutputStream(baos);
		tos.putNextEntry(new TarEntry(TarHeader.createHeader("terrain.png", (long) imageTerrain.length,
				System.currentTimeMillis() / 1000L, false)));
		tos.write(imageTerrain);

		Kryo kryo = new Kryo();
		kryo.register(float[][][].class, Serialization.ENVIRONMENT);

		// Environment export
		Output out = new Output(new ByteArrayOutputStream());
		kryo.writeObject(out, environment);
		out.flush();
		byte[] env = ((ByteArrayOutputStream) out.getOutputStream()).toByteArray();
		out.close();
		tos.putNextEntry(new TarEntry(
				TarHeader.createHeader("environment.bin", env.length, System.currentTimeMillis() / 1000L, false)));
		tos.write(env);

		tos.flush();
		tos.close();
		baos.flush();

		LZ4Compressor compressor = fac.highCompressor(17);
		byte[] input = baos.toByteArray();
		byte[] compressedData = new byte[compressor.maxCompressedLength(input.length)];
		baos.close();
		if (true) {
			FileHandle fh = new FileHandle(new File("C:/gritdebug/map.tar"));
			fh.writeBytes(input, false);
		}
		int compLength = compressor.compress(input, compressedData);
		System.out.println("Compression decreased data with " + (compressedData.length - compLength) / 1000f + "KB"
				+ " from " + compressedData.length / 1000f + "KB to " + compLength / 1000f + "KB");
		byte[] output = new byte[compLength];
		System.arraycopy(compressedData, 0, output, 0, compLength);
		file.writeBytes(output, false);
	}

	public void render(SpriteBatch sb, Vector2 topLeftScreenCornerPos, Vector2 screenSize) {
		for (int x = 0; x < regions.length; x++) {
			for (int y = 0; y < regions[0].length; y++) {
				sb.draw(regions[y][x], x * chunkRes, -y * chunkRes);
			}
		}
	}

	public Map(FileHandle fh, int quality, boolean mapEditor) throws Exception {
		this(fh.readBytes(), quality, mapEditor);
	}

	@Override
	public void dispose() {
		tex.dispose();
	}

	public void setEnvRes(int envExponent) {
		System.out.println("Sets environment to " + ((int) Math.pow(2, envExponent)) + "^2");
		environment = new float[(int) Math.pow(2, envExponent)][(int) Math.pow(2, envExponent)][2];
	}
	
	public float[][][] getEnvironment(){
		return environment;
	}
	
	public float getXEnvFactorOffset(int x, int y, int layer){
		if(environment[x][y][layer]>=1){
			return 1;
		} else if(environment[x][y][layer]>=0){
			return environment[x][y][layer];
		} else if(environment[x][y][layer]<=-1){
			return (environment[x][y][layer]+1)*-1;
		} else {
			return 0;
		}
	}
	
	public float getYEnvFactorOffset(int x, int y, int layer){
		if(environment[x][y][layer]>1){
			return (environment[x][y][layer]-1);
		} else if(environment[x][y][layer]>=0){
			return 0;
		} else if(environment[x][y][layer]<=-1){
			return 1;
		} else {
			return environment[x][y][layer]*-1;
		}
	}
}
