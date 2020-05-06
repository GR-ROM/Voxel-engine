package Game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import Block.Block;
import Block.Chunk;
import Block.ChunkMesh;
import Block.Light;
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

public class mainLoop {
	
	private static int getRandomNumberInRange(int min, int max) {

		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}

		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}

	public static void main(String[] args) {
		boolean space_dn;
		DisplayManager.createDisplay();
		Loader loader=new Loader();
		StaticShader shader=new StaticShader();
		Renderer render=new Renderer(shader);
		
		
		
		List <Block> blocks = new ArrayList<Block>();
		List<Entity> enities = new ArrayList<Entity>();
		Map<Vector2f, Chunk> chunks = new HashMap<Vector2f, Chunk>();
		World world=new World(chunks, new ModelTexture(loader.loadTexture("res/dirt")));
		
		
		space_dn=false;
		
		for (int x = 0; x < 32; x++) {
			for (int z = 0; z < 32; z++) { 
			//	int max_y=getRandomNumberInRange(0, 4);
				for (int y = 0; y < 5; y++) { 
					blocks.add(new Block(x, y, z, (short)1));
				}
			}
		}
		
		Light light=new Light(16, 2, 16, (short)0, (short)15);
		List<Light> lights=new ArrayList<Light>();
		lights.add(light);
		
		
		for (int x=0;x!=2;x++) {
			for (int z=0;z!=2;z++) {
				Chunk chunk = new Chunk(blocks, lights, new Vector3f(x*32, 0f, z*32), texture);
				chunks.add(chunk);
			}
		}
		
		Camera camera=new Camera();
		camera.setPosition(18f, 18f, -5f);
		Player player=new Player();

		while (!Display.isCloseRequested()) {
			camera.move();

			if (Keyboard.isKeyDown(Keyboard.KEY_SPACE) && (!space_dn)) {  //player.getBlockLookAt(camera, chunks, 200);
				for (Chunk chunk: chunks) {
					
					chunk.updateMesh();
				}
			} else space_dn=false;
			
			
			render.prepare();
			shader.start();
			shader.loadViewMatrix(camera);
			
			for (Chunk chunk: chunks) {
				
				render.renderChunk(chunk, shader);
			}
			
			
			shader.stop();
		    DisplayManager.updateDisplay();
		}
		shader.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();
	}

}
