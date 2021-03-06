package Game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import Block.Block;
import Block.Chunk;
import Block.ChunkMesh;
import Block.World;
import Engine.Camera;
import Engine.DisplayManager;
import Engine.Loader;
import Engine.Player;
import Engine.Renderer;
import Entities.Entity;
import Models.CubeModel;
import Models.RawModel;
import Models.TexturedModel;
import Shaders.StaticShader;
import Textures.ModelTexture;
import Toolbox.Vector2i;

public class mainLoop {
	
	private static int getRandomNumberInRange(int min, int max) {

		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}

		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}

	public static void main(String[] args) {
		DisplayManager.createDisplay();
		Loader loader=new Loader();
		StaticShader shader=new StaticShader();
		Renderer render=new Renderer(shader);
		
		Map<Vector2i, Chunk> chunks = new HashMap<Vector2i, Chunk>();
		World world=new World(chunks, new ModelTexture(loader.loadTexture("defaultPack")));
		
		for (int x = 0; x != 64; x++) {
			for (int z = 0; z != 64; z++) { 
			//	int max_y=getRandomNumberInRange(0, 4);
				for (int y = 0; y != 11; y++) {
					if(y<9) {
						if (world.setBlock(x, y, z, new Block(Block.STONE))==null){
							world.spawnNewChunk(x, z);
							world.setBlock(x, y, z, new Block(Block.STONE));
						}						
					} else
					if (y==9) {
						if (world.setBlock(x, y, z, new Block(Block.DIRT))==null){
							world.spawnNewChunk(x, z);
							world.setBlock(x, y, z, new Block(Block.DIRT));
						}						
					}
					if (y==10) {
						if (world.setBlock(x, y, z, new Block(Block.GRASS))==null){
							world.spawnNewChunk(x, z);
							world.setBlock(x, y, z, new Block(Block.GRASS));
						}						
					}
				}
			}
		}
		long timeStart=0;
		long timeStop=0;
		
		timeStart=System.currentTimeMillis();
		
		for (Vector2i vec: world.chunks.keySet()) {
			world.getChunks().get(vec).updateMesh();
		}	
		
		timeStop=System.currentTimeMillis();
		timeStop-=timeStart;
		System.out.printf(world.getChunks().size()+" chunks updated in: "+timeStop+" ms\r\n");
/*		
	    Light light=new Light(16, 2, 16, (short)0, (short)15);
		List<Light> lights=new ArrayList<Light>();
		lights.add(light);
	*/	

		Camera camera=new Camera();
		camera.setPosition(16f, 12f, 16f);
		
		Player player=new Player();

		while (!Display.isCloseRequested()) {
			timeStart=System.currentTimeMillis();
			camera.move();

			if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {  //player.getBlockLookAt(camera, chunks, 200);

			} 
			
			render.prepare();
			shader.start();
			shader.loadViewMatrix(camera);
			
			for (Vector2i vec: world.chunks.keySet()) {
				render.renderChunk(world.getChunks().get(vec), shader);
			}			
			
			shader.stop();		  
			timeStop=System.currentTimeMillis();
			timeStop-=timeStart;
		//	System.out.printf("Rendered in: "+timeStop+" ms\r\n");
		    DisplayManager.updateDisplay();
		}
		shader.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();
	}

}
